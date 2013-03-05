package com.blog;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello");
		return super.execute();
	}
}
