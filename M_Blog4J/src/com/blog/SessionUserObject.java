package com.blog;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.blog.base._UserBeanBase;
import com.blog.entity.User;

/**
 * Session中存放的对象
 * @author 马超
 *
 */
public class SessionUserObject extends User implements HttpSessionBindingListener {

	private String sessionId;
	
	public static SessionUserObject copyFrom(User user){
		return null;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent e) {
		sessionId = e.getSession().getId();
	}

	/**
	 * 用户注销时讲Object移除，同时执行这个函数
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
