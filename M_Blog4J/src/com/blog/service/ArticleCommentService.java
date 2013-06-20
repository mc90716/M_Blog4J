package com.blog.service;

import java.util.ArrayList;

import com.blog.Dao.DAO;
import com.blog.entity.ArticleComment;

public interface ArticleCommentService extends DAO<ArticleComment>{
	/**
	 * ����ArticleId��ø�ƪ�������е�����
	 * @param articleId
	 * @return
	 */
	public ArrayList<ArticleComment> getCommentByArticle(int articleId);
	
	/**
	 * �����û���������µ����ۣ���������
	 */
	public ArrayList<ArticleComment> getNewCommentByUserId(int userId);
	
	public void deleteCommnet(int commentId);
}
