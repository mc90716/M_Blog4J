package com.blog.service.impl;

import java.util.ArrayList;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.ArticleComment;
import com.blog.service.ArticleCommentService;

@Service
@Transactional
public class ArticleCommentServiceBean extends DaoSupport<ArticleComment> implements ArticleCommentService{

	@Override
	public ArrayList<ArticleComment> getCommentByArticle(int articleId) {
		Query query = em.createQuery("select o from blog_articlecomment o where o.article.articleId = " + articleId + " order by o.commentTime desc");
		ArrayList<ArticleComment> list = new ArrayList<ArticleComment>();
		list = (ArrayList<ArticleComment>) query.getResultList();
		//em.find(ArticleComment, arg1)
		return list;
	}

	@Override
	public ArrayList<ArticleComment> getNewCommentByUserId(int userId) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1 order by o.commentTime desc").setParameter(1, userId);
		ArrayList<ArticleComment> comments = (ArrayList<ArticleComment>) query.setFirstResult(0).setMaxResults(5).getResultList();
		return comments;
	}

	@Override
	public void deleteCommnet(int commentId) {
		em.createQuery("delete from " + getEntityName(this.entityClass) + " o where o.commentId = ?1").setParameter(1, commentId).executeUpdate();
	}

}
