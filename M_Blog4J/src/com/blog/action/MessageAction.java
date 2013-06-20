package com.blog.action;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import com.blog.SessionUserObject;
import com.blog.entity.Message;
import com.blog.entity.User;
import com.blog.service.FriendService;
import com.blog.service.MessageService;
import com.blog.service.UserService;
import com.googlecode.jsonplugin.annotations.JSON;

public class MessageAction extends BaseAction {
	
	private MessageService messageServiceBean;
	private FriendService friendServiceBean;
	private String userId;
	private String result;
	private Message message;
	private UserService userServiceBean;
	
	
	public String leaveMessage(){
		Integer youid = Integer.parseInt(userId);
		SessionUserObject suo = getLoginUser(request, response);
		boolean flag = friendServiceBean.isRelationShipExist(suo.getUserId(), youid);
		if(flag == true){
			result = "true";
		}else{
			result = "false";
		}
		request.setAttribute("youId", youid);
		return "leaveMessage";
	}
	
	public String saveMessage(){
		SessionUserObject suo = getLoginUser(request, response);
		User fromUser = userServiceBean.find(suo.getUserId());
		User toUser = userServiceBean.find(message.getToUser().getUserId());
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		message.setSendTime(new Date());
		messageServiceBean.save(message);
		request.setAttribute("toUserId", message.getToUser().getUserId());
		return "saveMessage";
	}
	
	public String viewAllMessage(){
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<Message> messages = messageServiceBean.getAllMessageByUserId(suo.getUserId());
		request.setAttribute("messages", messages);
		return "viewAllMessage";
	}

	public String deleteMessage(){
		String messageId = request.getParameter("messageId");
		messageServiceBean.delete(Integer.parseInt(messageId));
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<Message> messages = messageServiceBean.getAllMessageByUserId(suo.getUserId());
		request.setAttribute("messages", messages);
		return "deleteMessage";
	}
	
	public String replyMessageView(){
		String messageId = request.getParameter("messageId");
		Message msg = messageServiceBean.find(Integer.parseInt(messageId));
		request.setAttribute("msg", msg);
		return "replyMessageView";
	}
	
	public String replyMessage(){
		String toUserId = request.getParameter("toUserId");
		SessionUserObject suo = getLoginUser(request, response);
		User fromUser = userServiceBean.find(suo.getUserId());
		User toUser = userServiceBean.find(Integer.parseInt(toUserId));
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		message.setSendTime(new Date());
		messageServiceBean.save(message);
		return "replyMessage";
	}
	
	
	@JSON(serialize=false)
	public MessageService getMessageServiceBean() {
		return messageServiceBean;
	}
	@Resource
	public void setMessageServiceBean(MessageService messageServiceBean) {
		this.messageServiceBean = messageServiceBean;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResult() {
		return result;
	}
	@JSON(serialize=false)
	public FriendService getFriendServiceBean() {
		return friendServiceBean;
	}
	@Resource
	public void setFriendServiceBean(FriendService friendServiceBean) {
		this.friendServiceBean = friendServiceBean;
	}
	@JSON(serialize=false)
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public UserService getUserServiceBean() {
		return userServiceBean;
	}
	@Resource
	public void setUserServiceBean(UserService userServiceBean) {
		this.userServiceBean = userServiceBean;
	}
}
