package com.blog.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.blog.bean.QueryResult;

public interface DAO<T> {
	/**
	 * ��ȡ�ܵļ�¼��
	 * @return
	 */
	public long getCount();
	/**
	 * ���һ�����������
	 */
	public void clear();
	/**
	 * ����ʵ��
	 * @param entity  ʵ��id
	 */
	public void save(T entity);
	/**
	 * ����ʵ��
	 * @param entity  ʵ��id
	 */
	public void update(T entity);
	/**
	 * ɾ��ʵ��
	 * @param entityids
	 */
	public void delete(Serializable... entityids);
	/**
	 * ��ȡʵ��
	 * @param entityId  ʵ��id
	 * @return
	 */
	public T find(Serializable entityId);
	
	/**
	 * ��ȡ��ҳ����
	 * @param firstindex ��ʼ����
	 * @param maxresult ��Ҫ��ȡ�ļ�¼��
	 * @param wherejpql ������䣬����where�ؼ��֣��������ֻ��ʹ��λ�ò�����λ�ò�����������1��ʼ�����磺o.username=?1 and o.password=?2 
	 * @param queryParams  ���������ֵ�λ�ò���ֵ���������ֵΪ-1������ȡ��ȫ��������
	 * @param orderby   ����keyΪ�������ԣ�valueΪ������߽���
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex,int maxresult,String wherejpql,Object[] queryParams,LinkedHashMap<String,String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult,String wherejpql,Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult,LinkedHashMap<String,String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex,int maxresult);
	
	public QueryResult<T> getScrollData();
}
