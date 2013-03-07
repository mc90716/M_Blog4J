package com.blog.action;

import com.blog.BaseAction;
import com.blog.formbean.RegUserForm;
import com.blog.utils.StringUtil;

public class UserAction extends BaseAction{
	
	private RegUserForm user;
	
	public String regUser(){
		System.out.println(user.getDisplayName());
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
}
