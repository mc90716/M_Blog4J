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
	
	
	//��ע���������ר��ע��EntityManager����ģ�����JPA��ʵ�ַ�ʽ��EntityManager�����൱��HibernateTempalte����ɵ������Ƕ�ʵ���CRUD
	@PersistenceContext
	protected EntityManager em;  
	
	public void clear(){
		em.clear();
	}

	public void delete(Serializable ... entityids) {   //...����˼�ǿɱ����
		for(Object id : entityids){
			em.remove(em.getReference(this.entityClass, id));
		}
	}
	
	//find��������������ݣ���˲��ÿ���������ʹ��propagation=Propagation.NOT_SUPPORTEDע��
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":�����ʵ��id����Ϊ��");
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
	
	//��order by������û��where��������ķ�ҳ
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	
	//��where������û��order by�����µķ�ҳ
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	
	//û���κ�Լ�������µķ�ҳ
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex,maxresult,null,null,null);
	}
	
	//ֻ������еļ�¼������Ҫ��ҳ
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}

	//��where������䲢�һ���order by���ķ�ҳ
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
				query.setParameter(i+1, queryParams[i]);   //JPA�Ǵ�1��ʼ�ģ�Hibernate�Ǵ�0��ʼ��
			}
		}
	}
	/**
	 * ��ĳ�������ò�������ĳ�Ա������ʱ��һ�㽲�÷�������Ϊ��̬����
	 * keyΪ���ԣ�valueΪasc����desc
	 * ��װorder by���
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
	 * ��ȡʵ�������
	 * @param <E>
	 * @param clazz ʵ����
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
	 * ��ȡͳ������,�÷�����Ϊ�˽��hibernate������������select count(o) from Xxx o���BUG������,hibernate�Դ�jpql�������sqlΪselect count(field1,field2,...),��ʾʹ��count()ͳ�ƶ���ֶ��Ǵ����
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