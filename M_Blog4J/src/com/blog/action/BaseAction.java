package com.blog.action;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.blog.utils.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String msg = null;
	protected String url = null;

	/**
	 * 读取配置文件，根据key值获得对应的字符串
	 * @param key
	 * @return
	 */
	protected String getMessage(String key){
		ResourceBundle localResourceBundle = ResourceBundle.getBundle("html");
		return localResourceBundle.getString(key);
	}

	protected String msgbox(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!StringUtil.isEmpty(msg)){
			request.setAttribute("msg", msg);
		}
		if(!StringUtil.isEmpty(url)){
			request.setAttribute("url", url);
		}
		return "msg";
	}
}
