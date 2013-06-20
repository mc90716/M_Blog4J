package com.blog.service.impl;

import java.util.ArrayList;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.Friend;
import com.blog.entity.User;
import com.blog.service.FriendService;

@Transactional
@Service
public class FriendServiceBean extends DaoSupport<Friend> implements FriendService{

	@Override
	public boolean isRelationShipExist(int myid, int youid) {
		Query query = em.createQuery("select count("+ getCountField(this.entityClass) +") from " + getEntityName(this.entityClass) + " o where o.my.userId = ?1 and o.you.userId = ?2").setParameter(1, myid).setParameter(2, youid);
		Long count = (Long) query.getSingleResult();
		if(count == 1){
			return true;
		}else
			return false;
	}

	@Override
	public ArrayList<User> getUserByMyId(int myId) {
		Query query = em.createQuery("select o.you from " + getEntityName(this.entityClass) + " o where o.my.userId = ?1 order by o.time desc").setParameter(1, myId);
		ArrayList<User> friends = (ArrayList<User>) query.getResultList();
		return friends;
	}

	@Override
	public void cancelAttention(int myId, int youId) {
		Query query = em.createQuery("delete from " + getEntityName(this.entityClass) + " o where o.my.userId = ?1 and o.you.userId = ?2").setParameter(1, myId).setParameter(2, youId);
		query.executeUpdate();
	}

}
