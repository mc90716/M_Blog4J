package com.blog.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.BlogSecurityManager;
import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.UserLogManager;
import com.blog.utils.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String msg = null;
	protected String url = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public BaseAction(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	}
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
		if(!StringUtils.isEmpty(msg)){
			request.setAttribute("msg", msg);
		}
		if(!StringUtils.isEmpty(url)){
			request.setAttribute("url", url);
		}
		return "msg";
	}

	/**
	 * 获取当前登录的User
	 */
	protected SessionUserObject getLoginUser(HttpServletRequest request,
			HttpServletResponse response){
		HttpSession session = request.getSession();
		SessionUserObject suo = (SessionUserObject)session.getAttribute(Globals.SESSION_USER_KEY);
		return suo;
	}
	
	protected static boolean validateClientId(HttpServletRequest request,String clientId){
		boolean flag = UserLogManager.validateClientId(request, clientId);
		return flag;
	}
	
	/**
	 * 自动判断是否对内容进行敏感字过滤
	 * @param site
	 * @param content
	 * @return
	 */
	protected static String autoFiltrate(String content) {
		if(StringUtils.isEmpty(content))
			return null;
		if (!BlogSecurityManager.existIllegalWord(content))
			return content;
		else
			return BlogSecurityManager.autoGlossaryFiltrate(content);
	}
	
}
