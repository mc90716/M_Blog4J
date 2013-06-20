package com.blog.service;

import java.util.ArrayList;

import com.blog.Dao.DAO;
import com.blog.entity.Message;

public interface MessageService extends DAO<Message>{
	public ArrayList<Message> getAllMessageByUserId(int userId);
}
