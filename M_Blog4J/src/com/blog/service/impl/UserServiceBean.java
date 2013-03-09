package com.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Dao.DaoSupport;
import com.blog.entity.User;
import com.blog.service.UserService;

@Service
public class UserServiceBean extends DaoSupport<User> implements UserService{
	
}
