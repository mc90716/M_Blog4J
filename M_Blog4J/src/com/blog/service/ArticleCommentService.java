package com.blog.service;

import java.util.ArrayList;

import com.blog.Dao.DAO;
import com.blog.entity.ArticleComment;

public interface ArticleCommentService extends DAO<ArticleComment>{
	/**
	 * 根据ArticleId获得该篇文章所有的评论
	 * @param articleId
	 * @return
	 */
	public ArrayList<ArticleComment> getCommentByArticle(int articleId);
	
	/**
	 * 根据用户名获得最新的评论，降序排列
	 */
	public ArrayList<ArticleComment> getNewCommentByUserId(int userId);
	
	public void deleteCommnet(int commentId);
}
