package com.blog.service;

import java.util.ArrayList;

import com.blog.Dao.DAO;
import com.blog.entity.Friend;
import com.blog.entity.User;

public interface FriendService extends DAO<Friend>{
	
	public boolean isRelationShipExist(int myid,int youid);
	
	public ArrayList<User> getUserByMyId(int myId); 
	
	public void cancelAttention(int myId,int youId);
}
