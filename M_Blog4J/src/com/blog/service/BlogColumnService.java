package com.blog.service;


import java.util.ArrayList;
import java.util.List;

import com.blog.Dao.DAO;
import com.blog.entity.BlogColumn;


public interface BlogColumnService extends DAO<BlogColumn>{
	public ArrayList<BlogColumn> getBlogColumnContent(int userId);
	public ArrayList<BlogColumn> getHotBlogColumn();
	
	/**
	 * �����û�ID������µ�ר������������
	 */
	public ArrayList<BlogColumn> getNewColumnByUserId(int userId);
	
	/**
	 * ��ר����Ϊ�Ƽ�
	 */
	public void commendColumn(int columnId);
	
	/**
	 * ȡ���Ƽ�
	 */
	public void cancelCommend(int columnId);
	
}
