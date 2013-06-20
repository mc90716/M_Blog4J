package com.blog.service;

import java.util.ArrayList;

import com.blog.Dao.DAO;
import com.blog.entity.Article;
import com.blog.entity.Favourite;

public interface FavouriteService extends DAO<Favourite>{
	/**
	 * ���ղؼб��и���UserId�������
	 * @return
	 */
	public ArrayList<Article> getArticleByUserId(int userId);
	
	public Favourite getFavouriteByArticleIdAndUserId(int userId,int articleId);
	
	public void delFavourite(int articleId,int userId);
}
