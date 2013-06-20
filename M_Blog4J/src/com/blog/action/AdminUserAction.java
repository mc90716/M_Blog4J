package com.blog.action;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.blog.Globals;
import com.blog.bean.PageView;
import com.blog.entity.ArticleComment;
import com.blog.entity.Favourite;
import com.blog.entity.Friend;
import com.blog.entity.Message;
import com.blog.entity.User;
import com.blog.service.FavouriteService;
import com.blog.service.FriendService;
import com.blog.service.MessageService;
import com.blog.service.UserService;
import com.googlecode.jsonplugin.annotations.JSON;


public class AdminUserAction extends BaseAction {

	private UserService userServiceBean;
	private MessageService messageServiceBean;
	private FavouriteService favouriteServiceBean;
	private FriendService friendServiceBean;
	private User user;
	private String userId;
	private String ajaxResult;

	public String adminLogin(){
		boolean flag = userServiceBean.adminLog(user.getUserName(), user.getPassWd());
		if(flag == true){
			HttpSession session = request.getSession();
			session.setAttribute("admin", userServiceBean.getUserId(user.getUserName(), user.getPassWd()));
		}else{
			return "adminLog";
		}
		return "adminLogin";
	}

	public String showAllFriendsByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<Friend> pageView = new PageView<Friend>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("time", "desc");
		pageView.setQueryResult(friendServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("allFriends", pageView);
		return "showAllFriendsByPage";
	}

	public String delFriend(){
		String friendId = request.getParameter("friendId");
		friendServiceBean.delete(Integer.parseInt(friendId));
		return "delFriend";
	}

	public String showAllUserByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<User> pageView = new PageView<User>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("regTime", "desc");
		pageView.setQueryResult(userServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("allUsers", pageView);
		return "showAllUserByPage";
	}

	public String showAllMessageByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<Message> pageView = new PageView<Message>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sendTime", "desc");
		pageView.setQueryResult(messageServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("allMessages", pageView);
		return "showAllMessageByPage";
	}

	public String showAllFavouriteByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<Favourite> pageView = new PageView<Favourite>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("time", "desc");
		pageView.setQueryResult(favouriteServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("allFavourite", pageView);
		return "showAllFavouriteByPage";
	}

	public String delFavourite(){
		String favouriteId = request.getParameter("favouriteId");
		favouriteServiceBean.delete(Integer.parseInt(favouriteId));
		return "delFavourite";
	}

	public String delMessage(){
		String msgId = request.getParameter("messageId");
		messageServiceBean.delete(Integer.parseInt(msgId));
		return "delMessage";
	}

	public String searchUser(){
		String keyWord = request.getParameter("keyWord");
		String wherejpql = "o.userName like ?1";
		PageView<User> pageView = new PageView<User>(Globals.MAXRESULT, 1);
		keyWord = "%" + keyWord + "%";
		String[] queryParams = {keyWord}; 
		pageView.setQueryResult(userServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
		pageView.setTotalrecord(userServiceBean.getCount());
		request.setAttribute("allUsers", pageView);
		return "searchUser";
	}

	public String searchMessage(){
		String keyWord_send = request.getParameter("keyWord_send");
		String keyWord_accept = request.getParameter("keyWord_accept");
		String keyWord_content = request.getParameter("keyWord_content");
		if(keyWord_content != ""){
			String wherejpql = "o.content like ?1";
			PageView<Message> pageView = new PageView<Message>(Globals.MAXRESULT, 1);
			keyWord_content = "%" + keyWord_content + "%";
			String[] queryParams = {keyWord_content}; 
			pageView.setQueryResult(messageServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(messageServiceBean.getCount());
			request.setAttribute("allMessages", pageView);
		}
		if(keyWord_send != ""){
			String wherejpql = "o.fromUser.userName like ?1";
			PageView<Message> pageView = new PageView<Message>(Globals.MAXRESULT, 1);
			keyWord_send = "%" + keyWord_send + "%";
			String[] queryParams = {keyWord_send}; 
			pageView.setQueryResult(messageServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(messageServiceBean.getCount());
			request.setAttribute("allMessages", pageView);
		}
		if(keyWord_accept != ""){
			String wherejpql = "o.toUser.userName like ?1";
			PageView<Message> pageView = new PageView<Message>(Globals.MAXRESULT, 1);
			keyWord_accept = "%" + keyWord_accept + "%";
			String[] queryParams = {keyWord_accept}; 
			pageView.setQueryResult(messageServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(messageServiceBean.getCount());
			request.setAttribute("allMessages", pageView);
		}
		if((keyWord_content == "")&&(keyWord_accept == "")&&(keyWord_send == "")){
			return "delMessage";
		}
		return "searchMessage";
	}
	
	public String searchRelationship(){
		String keyWord = request.getParameter("keyWord");
		String wherejpql = "o.my.userName like ?1";
		PageView<Friend> pageView = new PageView<Friend>(Globals.MAXRESULT, 1);
		keyWord = "%" + keyWord + "%";
		String[] queryParams = {keyWord}; 
		pageView.setQueryResult(friendServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
		pageView.setTotalrecord(friendServiceBean.getCount());
		request.setAttribute("allFriends", pageView);
		return "searchRelationship";
	}
	
	public String lockUser(){
		String userId = request.getParameter("userId");
		userServiceBean.lockUserByUserId(Integer.parseInt(userId));
		return "lockUser";
	}

	public String unLockUser(){
		String userId = request.getParameter("userId");
		userServiceBean.unLockUserByUserId(Integer.parseInt(userId));
		return "unLockUser";
	}

	@JSON(serialize=false)
	public UserService getUserServiceBean() {
		return userServiceBean;
	}
	@Resource
	public void setUserServiceBean(UserService userServiceBean) {
		this.userServiceBean = userServiceBean;
	}
	@JSON(serialize=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	@JSON(serialize=false)
	public MessageService getMessageServiceBean() {
		return messageServiceBean;
	}

	@Resource
	public void setMessageServiceBean(MessageService messageServiceBean) {
		this.messageServiceBean = messageServiceBean;
	}

	@JSON(serialize=false)
	public FavouriteService getFavouriteServiceBean() {
		return favouriteServiceBean;
	}
	@Resource
	public void setFavouriteServiceBean(FavouriteService favouriteServiceBean) {
		this.favouriteServiceBean = favouriteServiceBean;
	}
	@JSON(serialize=false)
	public FriendService getFriendServiceBean() {
		return friendServiceBean;
	}
	@Resource
	public void setFriendServiceBean(FriendService friendServiceBean) {
		this.friendServiceBean = friendServiceBean;
	}
}
