package com.blog;

import java.util.ResourceBundle;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String getMessage(String key){
		ResourceBundle localResourceBundle = ResourceBundle.getBundle("html");
		return localResourceBundle.getString(key);
	}
}
