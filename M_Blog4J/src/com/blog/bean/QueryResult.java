package com.blog.bean;

import java.util.List;

/**
 * 记录集
 * @author 马超
 *
 */
public class QueryResult<T> {
	private List<T> resultList;  //结果集的集合
	private long totalRecord;    //总记录数
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	
}
