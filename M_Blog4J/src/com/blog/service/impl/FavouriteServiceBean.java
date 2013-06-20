package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.Article;
import com.blog.entity.Favourite;
import com.blog.service.FavouriteService;

@Service
@Transactional
public class FavouriteServiceBean extends DaoSupport<Favourite> implements FavouriteService {

	@Override
	public ArrayList<Article> getArticleByUserId(int userId) {
		Query query = em.createQuery("select o.articles from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1").setParameter(1, userId);
		ArrayList<Article> articles = (ArrayList<Article>)query.getResultList();
		return articles;
	}

	@Override
	public Favourite getFavouriteByArticleIdAndUserId(int userId, int articleId) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1 and o.");
		return null;
	}

	@Override
	public void delFavourite(int articleId, int userId) {
		Query query = em.createNativeQuery("select favouriteId from blog_favouritearticle where articleId = ?1").setParameter(1, articleId);
		List<Integer> favouriteIdList =query.getResultList();
		for(int id : favouriteIdList){
			Query qu = em.createNativeQuery("delete from blog_favouritearticle where articleId = ?1 and favouriteId = ?2").setParameter(1, articleId).setParameter(2, id);
			qu.executeUpdate();
			Query q = em.createNativeQuery("delete from blog_favourite where favouriteId = ?1 and userId = ?2").setParameter(1, id).setParameter(2, userId);
			q.executeUpdate();
		}
	}
	
}
