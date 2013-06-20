package com.blog.action;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.blog.SessionUserObject;
import com.blog.entity.User;
import com.blog.service.FriendService;

public class RelationshipAction extends BaseAction {
	
	private FriendService friendServiceBean;
	
	
	@Override
	public String execute() throws Exception {
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<User> friends =  friendServiceBean.getUserByMyId(suo.getUserId());
		request.setAttribute("friends", friends);
		return super.execute();
	}
	
	public String cancelAttention(){
		int userId = Integer.parseInt(request.getParameter("userId"));
		SessionUserObject suo = getLoginUser(request, response);
		
		friendServiceBean.cancelAttention(suo.getUserId(), userId);
		return "cancelAttention";
	}


	public FriendService getFriendServiceBean() {
		return friendServiceBean;
	}
	@Resource
	public void setFriendServiceBean(FriendService friendServiceBean) {
		this.friendServiceBean = friendServiceBean;
	}
	
}
