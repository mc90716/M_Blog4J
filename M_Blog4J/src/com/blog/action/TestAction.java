package com.blog.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	private String currentpage;
	private String result;
	public String getResult() {
		return result;
	}
	public String getCurrentpage() {
		return currentpage;
	}
	
	public String test(){
		System.out.println("Ö´ÐÐ´ÎÊý");
		return "abc";
	}
	
}
