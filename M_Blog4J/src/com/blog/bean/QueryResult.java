package com.blog.bean;

import java.util.List;

/**
 * ��¼��
 * @author ��
 *
 */
public class QueryResult<T> {
	private List<T> resultList;  //������ļ���
	private long totalRecord;    //�ܼ�¼��
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
