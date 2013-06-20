package com.blog.service;


import java.util.ArrayList;
import java.util.List;

import com.blog.Dao.DAO;
import com.blog.entity.BlogColumn;


public interface BlogColumnService extends DAO<BlogColumn>{
	public ArrayList<BlogColumn> getBlogColumnContent(int userId);
	public ArrayList<BlogColumn> getHotBlogColumn();
	
	/**
	 * 根据用户ID获得最新的专栏，降序排列
	 */
	public ArrayList<BlogColumn> getNewColumnByUserId(int userId);
	
	/**
	 * 讲专栏置为推荐
	 */
	public void commendColumn(int columnId);
	
	/**
	 * 取消推荐
	 */
	public void cancelCommend(int columnId);
	
}
