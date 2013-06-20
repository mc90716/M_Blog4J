package com.blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.blog.Dao.DAO;
import com.blog.entity.Article;
import com.blog.entity.User;

public interface ArticleService extends DAO<Article>{
	public long getCountById(int id);
	public HashMap<Integer,Article> getRecycleById(int id);
	public HashMap<String,ArrayList<Article>> getHotArticleByCategory();
	public ArrayList<Article> getColumnBlogByColumnId(int columnId);
	/**
	 * 得到我的空间文章里面左侧的所有信息
	 * @param userId
	 * @return
	 */
	public HashMap<String,String> getArticleMessageByUserId(int userId);
	
	/**
	 * 根据用户ID显示文章信息，降序排列
	 */
	public ArrayList<Article> getNewArticleByUserId(int userId);
	
	public User getUserIdByColumnId(int columnId);
	
	public void recomArticle(int articleId);
	
	public void cancelRecomArticle(int articleId);
	
	public List<Article> getHotArticle();
	
	public List<Article> searchArticleByKeyWord(String keyWord);
	
	public HashMap<String,Long> getCountOfCategory();
	
}
