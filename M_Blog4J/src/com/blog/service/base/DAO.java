package com.blog.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;

public interface DAO<T> {
	/**
	 * 获取总的记录数
	 * @return
	 */
	public long getCount();
	/**
	 * 清除一级缓存的数据
	 */
	public void clear();
	/**
	 * 保存实体
	 * @param entity  实体id
	 */
	public void save(T entity);
	/**
	 * 更新实体
	 * @param entity  实体id
	 */
	public void update(T entity);
	/**
	 * 删除实体
	 * @param entityids
	 */
	public void delete(Serializable... entityids);
	/**
	 * 获取实体
	 * @param entityId  实体id
	 * @return
	 */
	public T find(Serializable entityId);
	
	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 需要获取的记录数
	 * @param wherejpql 条件语句，不带where关键字，条件语句只能使用位置参数，位置参数的索引以1开始，例如：o.username=?1 and o.password=?2 
	 * @param queryParams  条件语句出现的位置参数值，如果输入值为-1，就是取出全部的数据
	 * @param orderby   排序，key为排序属性，value为升序或者降序
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex,int maxresult,String wherejpql,Object[] queryParams,LinkedHashMap<String,String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult,String wherejpql,Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult,LinkedHashMap<String,String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult);
	
	public QueryResult<T> getScrollData();
}
