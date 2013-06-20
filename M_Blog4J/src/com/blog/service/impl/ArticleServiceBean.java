package com.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.Article;
import com.blog.entity.User;
import com.blog.service.ArticleService;

@Service
@Transactional
public class ArticleServiceBean extends DaoSupport<Article> implements ArticleService{

	/**
	 * �����û�����ø��û���������
	 */
	@Override
	public long getCountById(int id) {
		// TODO Auto-generated method stub
		return (Long)em.createQuery("select count("+ getCountField(this.entityClass) +") from "+ getEntityName(this.entityClass)+ " o where o.user.userId = " + id + " and o.delFlag = 1").getSingleResult();
	}

	/**
	 * �����û�����ø��û�ɾ����������
	 */
	@Override
	public HashMap<Integer, Article> getRecycleById(int id) {
		HashMap<Integer, Article> recycleMap = new HashMap<Integer, Article>();
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass)+ " o where o.user.userId = " + id + " and o.delFlag = 0");
		List<Article> list = query.getResultList();
		for(Article article : list){
			recycleMap.put(article.getArticleId(), article);
		}
		return recycleMap;
	}

	/**
	 * ��ò�ͬ��������������ŵ�ǰʮ��
	 */
	@Override
	public HashMap<String, ArrayList<Article>> getHotArticleByCategory() {
		HashMap<String, ArrayList<Article>> hotArticleHashMap = new HashMap<String, ArrayList<Article>>();
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 1 order by o.visitorsCount desc");
		List<Article> list = query.getResultList();
		ArrayList<Article> arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�ƶ�����", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 2 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("Webǰ��", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 3 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�ܹ����", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 4 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�������", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 5 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("������", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 6 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("���ݿ�", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 7 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("ϵͳ��ά", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 8 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�Ƽ���", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 9 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�з�����", arrayList);

		query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.category.categoryId = 10 order by o.visitorsCount desc");
		list = query.getResultList();
		arrayList = new ArrayList<Article>();
		for(int i=0; i<list.size()&&i<1; i++){
			arrayList.add(list.get(i));
		}
		hotArticleHashMap.put("�ۺ�", arrayList);


		return hotArticleHashMap;
	}

	@Override
	public ArrayList<Article> getColumnBlogByColumnId(int columnId) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = 1 and o.blogColumn.columnId = ?1");
		query.setParameter(1, columnId);
		ArrayList<Article> list = new ArrayList<Article>();
		list = (ArrayList<Article>) query.getResultList();
		return list;
	}

	@Override
	public HashMap<String, String> getArticleMessageByUserId(int userId) {
		HashMap<String,String> articleMessage = new HashMap<String, String>();
		Long count = (Long) em.createQuery("select count(o) from " + getEntityName(this.entityClass) + " o where o.articleType = 'original' and o.user.userId = ?1").setParameter(1, userId).getSingleResult();
		articleMessage.put("original", count.toString());
		count = (Long) em.createQuery("select count(o) from " + getEntityName(this.entityClass) + " o where o.articleType = 'repost' and o.user.userId = ?1").setParameter(1, userId).getSingleResult();
		articleMessage.put("repost", count.toString());
		count = (Long) em.createQuery("select count(o) from " + getEntityName(this.entityClass) + " o where o.articleType = 'translated' and o.user.userId = ?1").setParameter(1, userId).getSingleResult();
		articleMessage.put("translated", count.toString());

		List<Article> list = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1 order by o.visitorsCount desc").setParameter(1, userId).setFirstResult(0).setMaxResults(2).getResultList();
		if(list.size() >1){
			articleMessage.put("article1", list.get(0).getTitle() + "@" + list.get(0).getVisitorsCount() + "@" + list.get(0).getArticleId());
			articleMessage.put("article2", list.get(1).getTitle() + "@" + list.get(1).getVisitorsCount() + "@" + list.get(1).getArticleId());
		}
		else{
			articleMessage.put("article1", "û������@0@1");
			articleMessage.put("article2", "û������@0@1");
		}
		return articleMessage;
	}

	@Override
	public ArrayList<Article> getNewArticleByUserId(int userId) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.user.userId = ?1  order by o.createTime desc").setParameter(1, userId);
		ArrayList<Article> articles = (ArrayList<Article>) query.setFirstResult(0).setMaxResults(5).getResultList();
		return articles;
	}

	@Override
	public User getUserIdByColumnId(int columnId) {
		Query query = em.createQuery("select o.user from blog_column o where o.columnId = ?1").setParameter(1, columnId);
		List<User> users = query.getResultList();
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public void recomArticle(int articleId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.delFlag = -1 where o.articleId = ?1").setParameter(1, articleId);
		query.executeUpdate();
	}

	@Override
	public void cancelRecomArticle(int articleId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.delFlag = 1 where o.articleId = ?1").setParameter(1, articleId);
		query.executeUpdate();
	}

	@Override
	public List<Article> getHotArticle() {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.delFlag = -1");
		List<Article> list = query.getResultList();
		return list;
	}

	@Override
	public List<Article> searchArticleByKeyWord(String keyWord) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.content like '%"+ keyWord +"%'" + " order by o.createTime desc");
		List<Article> list = query.getResultList();
		return list;
	}

	@Override
	public HashMap<String, Long> getCountOfCategory() {
		HashMap<String,Long> hash = new HashMap<String, Long>();
		Long count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 1 and o.delFlag = 1").getSingleResult();
		hash.put("�ƶ�����", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 2 and o.delFlag = 1").getSingleResult();
		hash.put("Webǰ��", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 3 and o.delFlag = 1").getSingleResult();
		hash.put("�ܹ����", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 4 and o.delFlag = 1").getSingleResult();
		hash.put("�������", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 5 and o.delFlag = 1").getSingleResult();
		hash.put("������", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 6 and o.delFlag = 1").getSingleResult();
		hash.put("���ݿ�", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 7 and o.delFlag = 1").getSingleResult();
		hash.put("ϵͳ��ά", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 8 and o.delFlag = 1").getSingleResult();
		hash.put("�Ƽ���", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 9 and o.delFlag = 1").getSingleResult();
		hash.put("�з�����", count);
		count = (Long)em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o where o.category.id = 10 and o.delFlag = 1").getSingleResult();
		hash.put("�ۺ�", count);
		return hash;
	}
}
