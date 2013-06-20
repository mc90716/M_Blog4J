package com.blog.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.User;
import com.blog.service.UserService;

@Service
@Transactional
public class UserServiceBean extends DaoSupport<User> implements UserService{
	/**
	 * 查询用户是否存在，true存在，false不存在
	 */
	public boolean checkUserIsExists(String userName){
		Query query = em.createQuery("select userName from blog_user u where u.userName = ?1 and u.userRole = 0");
		query.setParameter(1, userName);
		List list = query.getResultList();
		if(list.size() > 0)
			return true;
		return false;
	}
	
	/**
	 * 根据用户名和密码，返回id
	 */
	@Override
	public int getUserId(String userName, String passwd) {
		Query query = em.createQuery("select userId from blog_user u where u.userName = ?1 and u.passWd = ?2 and u.userRole = 0");
		query.setParameter(1, userName);
		query.setParameter(2, passwd);
		List list = query.getResultList();
		if(list.size() == 0){
			return -1;
		}else{
			return (Integer)list.get(0);
		}
	}

	@Override
	public void logoutUser(String userName) {
		Query query = em.createQuery("update blog_user u set u.userState = 'outline' where u.userName = ?1");
		query.setParameter(1, userName);
		query.executeUpdate();
	}

	@Override
	public void loginUser(String userName) {
		Query query = em.createQuery("update blog_user u set u.userState = 'inline' where u.userName = ?1");
		query.setParameter(1, userName);
		query.executeUpdate();
	}

	@Override
	public boolean adminLog(String userName, String passwd) {
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.userName = ?1 and o.passWd = ?2 and o.userRole = 1").setParameter(1, userName).setParameter(2, passwd);
		List<User> list = query.getResultList();
		if(list.size() > 0)
			return true;
		return false;
	}

	@Override
	public void lockUserByUserId(int userId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.userRole = -1 where o.userId = ?1").setParameter(1, userId);
		query.executeUpdate();
	}

	@Override
	public void unLockUserByUserId(int userId) {
		Query query = em.createQuery("update " + getEntityName(this.entityClass) + " o set o.userRole = 0 where o.userId = ?1").setParameter(1, userId);
		query.executeUpdate();
	}
}
