package com.blog.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.UserLogManager;
import com.blog.bean.PageView;
import com.blog.entity.Article;
import com.blog.entity.Friend;
import com.blog.entity.User;
import com.blog.enumeration.UserState;
import com.blog.service.ArticleService;
import com.blog.service.FriendService;
import com.blog.service.UserService;
import com.blog.utils.StringUtils;
import com.googlecode.jsonplugin.annotations.JSON;

public class UserAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(UserAction.class);

	private User user;
	private UserService userServiceBean;
	private UserLogManager userLogManager = new UserLogManager();
	private ArticleService articleServiceBean;
	private FriendService friendServiceBean;

	private String name;
	private String result;  //注册时用户名是否存在
	private String uName;
	private String uPasswd;
	private String logResult;  //登录是否成功
	private String userId;
	private String focusResult;

	public String regUser(){
		String vcode_input = request.getParameter("vcode");
		String vcode = request.getSession().getAttribute("vcode").toString();
		if(!vcode_input.equals(vcode)){
			return "register";
		}
		user.setPassWd(StringUtils.encrypt(user.getPassWd(), Globals.PASSSWORD_KEY));
		user.setRegTime(new Date());
		user.setLastAddr(request.getLocalAddr());
		user.setUserState(UserState.inline);
		user.setUserRole(0);
		userServiceBean.save(user);
		int id = userServiceBean.getUserId(user.getUserName(), user.getPassWd());
		user.setUserId(id);
		userLogManager.loginUser(request, response, user, 20);
		return "home";
	}

	public String createRandomImg(){
		return "randomImg";
	}

	public String checkUserExist(){
		System.out.println("checkUserExist");
		if(userServiceBean.checkUserIsExists(name)){
			result = "yes";
		}
		else{
			result = "no";
		}
		return "checkUserResult";
	}

	public String loginUser(){
		String passWord = StringUtils.encrypt(uPasswd, Globals.PASSSWORD_KEY);
		int id = userServiceBean.getUserId(uName, passWord);//根据用户名和密码返回用户的id
		if(id == -1){
			logResult = "false";
		}else{
			logResult = "true";
			User u = userServiceBean.find(id);
			userLogManager.loginUser(request, response, u, 20);
			userServiceBean.loginUser(uName);
		}
		return "logResult";
	}

	public String logoutUser(){
		String userName = userLogManager.logoutUser(request, response);
		userServiceBean.logoutUser(userName);
		return "logout";
	}


	@SuppressWarnings("unchecked")
	public String viewOtherSpace() throws Exception{
		String userId = request.getParameter("userId");
		String currentPage = request.getParameter("currentPage");
		if(userId == null){
			SessionUserObject suo = getLoginUser(request, response);
			userId = suo.getUserId() + "";
			currentPage = "1";
		}
		PageView pageView = new PageView(Globals.MAXRESULT, Integer.parseInt(currentPage));
		Integer[] queryParams = {Integer.parseInt(userId)};
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,"o.user.userId=?1 and o.delFlag = 1",queryParams));
		ArrayList<Article> columnBlogList = new ArrayList<Article>();
		columnBlogList = (ArrayList<Article>) pageView.getRecords();
		
		List<Article> articles = columnBlogList;
		List<Article> articleList = new ArrayList<Article>();
		for(Article article : articles){
			StringUtils.extractText(article.getContent());
			articleList.add(article);
		}
		//pageView_articleList.setRecords(articleList);
		
		request.setAttribute("columnBlogList", articleList);
		
		
		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(Integer.parseInt(userId));
		request.setAttribute("articleMessage", articleMessage);
		User user = userServiceBean.find(Integer.parseInt(userId));
		request.setAttribute("user", user);
		return "viewOtherSpace";
	}

	public String payAttention(){
		SessionUserObject suo = getLoginUser(request, response);

		boolean flag = friendServiceBean.isRelationShipExist(suo.getUserId(), Integer.parseInt(userId));
		if(flag == false){
			Friend friend = new Friend();
			friend.setTime(new Date());
			User my = userServiceBean.find(suo.getUserId());
			User you = userServiceBean.find(Integer.parseInt(userId));
			friend.setMy(my);
			friend.setYou(you);
			friendServiceBean.save(friend);
			focusResult = "true";
		}else{
			focusResult = "false";
		}
		return "payAttention";
	}

	@JSON(serialize=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	@JSON(serialize=false) 
	public UserService getUserServiceBean() {
		return userServiceBean;
	}
	@Resource
	public void setUserServiceBean(UserService userServiceBean) {
		this.userServiceBean = userServiceBean;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setuPasswd(String uPasswd) {
		this.uPasswd = uPasswd;
	}

	public String getLogResult() {
		return logResult;
	}
	@JSON(serialize=false) 
	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}

	public String getFocusResult() {
		return focusResult;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
