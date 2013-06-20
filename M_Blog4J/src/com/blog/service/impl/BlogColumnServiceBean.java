package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.BlogColumn;
import com.blog.service.BlogColumnService;

@Service
@Transactional
public class BlogColumnServiceBean extends DaoSupport<BlogColumn> implements BlogColumnService{

	@Override
	public ArrayList<BlogColumn> getBlogColumnContent(int userId) {
		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass)+ " o where o.user.userId = " + userId);
		List<BlogColumn> list = query.getResultList();
		for(int i=0;i<list.size();i++){
			blogColumns.add(list.get(i));
		}
		return blogColumns;
	}

	@Override
	public ArrayList<BlogColumn> getHotBlogColumn() {
		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.hot = 1");
		List<BlogColumn> list = (ArrayList<BlogColumn>) query.getResultList();
		for(int i=0; i<list.size();i++){
			blogColumns.add(list.get(i));
		}
		return blogColumns;
	}

	@Override
	public ArrayList<BlogColumn> getNewColumnByUserId(int userId) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1 order by o.createTime desc").setParameter(1, userId);
		ArrayList<BlogColumn> columns = (ArrayList<BlogColumn>) query.setFirstResult(0).setMaxResults(5).getResultList();
		return columns;
	}

	@Override
	public void commendColumn(int columnId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.hot = 1 where o.columnId = ?1").setParameter(1, columnId);
		query.executeUpdate();
	}

	@Override
	public void cancelCommend(int columnId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.hot = 0 where o.columnId = ?1").setParameter(1, columnId);
		query.executeUpdate();
	}
}
