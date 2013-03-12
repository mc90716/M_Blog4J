package com.blog.action;

import javax.annotation.Resource;

import com.blog.formbean.RegUserForm;
import com.blog.service.impl.UserServiceBean;
import com.blog.utils.StringUtil;

public class UserAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RegUserForm user;
	private UserServiceBean userServiceBean;
	
	public String regUser(){
		String str = super.getMessage("error.message");
		System.out.println("str===" + str);
		return SUCCESS;
	}

	public RegUserForm getUser() {
		return user;
	}

	public void setUser(RegUserForm user) {
		this.user = user;
	}

	@Override
	public void validate() {
		if(StringUtil.isEmpty(user.getUserName())){
			//TODO 异常处理
		}else if(StringUtil.isEmail(user.getEmail())){
			//TODO 异常处理
		}else if(StringUtil.isEmpty(user.getDisplayName())){
			//TODO 异常处理
		}else if(StringUtil.isEmpty(user.getSignature())){
			//TODO 异常处理
		}
		super.validate();
	}

	public UserServiceBean getUserServiceBean() {
		return userServiceBean;
	}
	@Resource
	public void setUserServiceBean(UserServiceBean userServiceBean) {
		//System.out.println("setUserServiceBean");
		this.userServiceBean = userServiceBean;
	}
}
