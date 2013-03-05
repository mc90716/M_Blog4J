package com.blog.service.base;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.utils.GenericsUtils;

public abstract class DaoSupport<T> implements DAO<T>{
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	
	
	//该注解的作用是专门注入EntityManager对象的，这是JPA的实现方式。EntityManager对象相当于HibernateTempalte，完成的作用是对实体的CRUD
	@PersistenceContext
	protected EntityManager em;  
	
	public void clear(){
		em.clear();
	}

	public void delete(Serializable ... entityids) {   //...的意思是可变参数
		for(Object id : entityids){
			em.remove(em.getReference(this.entityClass, id));
		}
	}
	
	//find方法不会更改数据，因此不用开启事物，因此使用propagation=Propagation.NOT_SUPPORTED注解
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		return em.find(this.entityClass, entityId);
	}

	public void save(T entity) {
		em.persist(entity);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long)em.createQuery("select count("+ getCountField(this.entityClass) +") from "+ getEntityName(this.entityClass)+ " o").getSingleResult();
	}
	
	public void update(T entity) {
		em.merge(entity);
	}
	
	//有order by条件，没有where语句条件的分页
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	
	//有where条件，没有order by条件下的分页
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	
	//没有任何约束条件下的分页
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex,maxresult,null,null,null);
	}
	
	//只获得所有的记录而不需要分页
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}

	//有where条件语句并且还有order by语句的分页
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult
			, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);
		Query query = em.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if(firstindex!=-1 && maxresult!=-1) 
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setResultList(query.getResultList());
		query = em.createQuery("select count("+ getCountField(this.entityClass)+ ") from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalRecord((Long)query.getSingleResult());
		return qr;
	}
	
	protected static void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i+1, queryParams[i]);   //JPA是从1开始的，Hibernate是从0开始的
			}
		}
	}
	/**
	 * 当某个方法用不到该类的成员变量的时候一般讲该方法声明为静态方法
	 * key为属性，value为asc或者desc
	 * 组装order by语句
	 * @param orderby
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取实体的名称
	 * @param <E>
	 * @param clazz 实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz){
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,hibernate对此jpql解析后的sql为select count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	protected static <E> String getCountField(Class<E> clazz){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
}