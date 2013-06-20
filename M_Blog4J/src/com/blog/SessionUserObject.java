package com.blog;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.blog.base._UserBeanBase;
import com.blog.entity.User;

/**
 * Session�д�ŵĶ���
 * @author ��
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
	 * �û�ע��ʱ��Object�Ƴ���ͬʱִ���������
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
