package com.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.entity.User;

public class UserLogManager {
	private final static String SESSION_USER_KEY = "UserBean";
	
	/**
	 * �û���¼�������ж�Session���Ƿ��Ѿ����ڣ����ڵĻ���ֱ�ӷ��ء����������
	 * @param request
	 * @param response
	 * @param user
	 * @param keepDays
	 * @return
	 */
	public static SessionUserObject logUser(HttpServletRequest request,
			HttpServletResponse response,User user,int keepDays){
		HttpSession session = request.getSession(false);
		if(session != null){
			SessionUserObject sub = (SessionUserObject) session.getAttribute(SESSION_USER_KEY);
			if((sub != null)&&(sub.getId() == user.getUserId())){
				return sub;
			}
		}
		SessionUserObject sessionUserObject = new SessionUserObject();
		sessionUserObject.setKeepDays(keepDays);
		sessionUserObject.setId(user.getUserId());
		sessionUserObject.setLastAddr(request.getRemoteAddr());
		sessionUserObject.setStatus(1);
		return null;
	}
}
