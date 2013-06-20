package com.blog.service;

import com.blog.Dao.DAO;
import com.blog.entity.User;

public interface UserService extends DAO<User>{
	
	public boolean checkUserIsExists(String userName);
	
	public int getUserId(String userName,String passwd);
	
	public void logoutUser(String userName);
	
	public void loginUser(String userName);
	
	public boolean adminLog(String userName,String passwd);
	
	public void lockUserByUserId(int userId);
	
	public void unLockUserByUserId(int userId);
}
