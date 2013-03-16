package com.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.entity.User;

public class UserLogManager {
	private final static String SESSION_USER_KEY = "UserBean";
	
	/**
	 * 用户登录，首先判断Session中是否已经存在，存在的话，直接返回。如果不存在
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
