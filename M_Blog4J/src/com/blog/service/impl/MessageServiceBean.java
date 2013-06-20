package com.blog.service.impl;

import java.util.ArrayList;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.Message;
import com.blog.service.MessageService;

@Service
@Transactional
public class MessageServiceBean extends DaoSupport<Message> implements MessageService {

	@Override
	public ArrayList<Message> getAllMessageByUserId(int userId) {
		System.out.println("userId == " + userId);
		Query query = em.createQuery("select o from " + getEntityName(this.entityClass) + " o where o.toUser.userId = ?1 or o.fromUser.userId = ?2").setParameter(1, userId).setParameter(2, userId);
		ArrayList<Message> messages = (ArrayList<Message>) query.getResultList();
		return messages;
	}

}
