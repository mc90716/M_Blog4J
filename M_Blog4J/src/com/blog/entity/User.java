package com.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.blog.enumeration.UserState;


@Entity(name="blog_user")
public class User implements Serializable{
	private int userId;
	private String userName;
	private String passWd;
	private String displayName;
	private String email;
	private String HomePage;
	private String Portrait;
	private Date regTime;
	private int userRole;  //1==网站管理员、0==普通用户,-1用户被锁定
	private String signature;
	private UserState userState; //0==在线、1隐身、2==离线
	//private Date birthday;
	private int keepDays;  //密码保存时间
	private String lastAddr; //最后登录IP地址
	private Set<Favourite> favourites = new HashSet<Favourite>();
	private Set<SayComment> sayComments = new HashSet<SayComment>();
	private Set<Say> says = new HashSet<Say>();
	private Set<Album> albums = new HashSet<Album>();
	private Set<Message> fromMessage = new HashSet<Message>();
	private Set<Message> toMessage = new HashSet<Message>();
	private Set<Article> articles = new HashSet<Article>();
	private Set<ArticleComment> articleComments = new HashSet<ArticleComment>();
	private Set<Friend> my = new HashSet<Friend>();
	private Set<Friend> you = new HashSet<Friend>();
	private Set<PhotoComment> photoComments = new HashSet<PhotoComment>();
	private Set<BlogColumn> blogColumns = new HashSet<BlogColumn>();
	
	@OneToMany(mappedBy="user")
	public Set<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	@OneToMany(mappedBy="user")
	public Set<SayComment> getSayComments() {
		return sayComments;
	}
	public void setSayComments(Set<SayComment> sayComments) {
		this.sayComments = sayComments;
	}
	@OneToMany(mappedBy="user")
	public Set<Say> getSays() {
		return says;
	}
	public void setSays(Set<Say> says) {
		this.says = says;
	}
	@Id @GeneratedValue
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(length=40)
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	@Column(length=40)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(length=30)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePage() {
		return HomePage;
	}
	public void setHomePage(String homePage) {
		HomePage = homePage;
	}
	public String getPortrait() {
		return Portrait;
	}
	public void setPortrait(String portrait) {
		Portrait = portrait;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@OneToMany(mappedBy="fromUser")
	public Set<Message> getFromMessage() {
		return fromMessage;
	}
	public void setFromMessage(Set<Message> fromMessage) {
		this.fromMessage = fromMessage;
	}
	@OneToMany(mappedBy="toUser")
	public Set<Message> getToMessage() {
		return toMessage;
	}
	public void setToMessage(Set<Message> toMessage) {
		this.toMessage = toMessage;
	}
	@OneToMany(mappedBy="user")
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	@OneToMany(mappedBy="user")
	public Set<ArticleComment> getArticleComments() {
		return articleComments;
	}
	public void setArticleComments(Set<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}
	
	@OneToMany(mappedBy="my")
	public Set<Friend> getMy() {
		return my;
	}
	public void setMy(Set<Friend> my) {
		this.my = my;
	}
	@OneToMany(mappedBy="you")
	public Set<Friend> getYou() {
		return you;
	}
	public void setYou(Set<Friend> you) {
		this.you = you;
	}
	@OneToMany(mappedBy="user")
	public Set<PhotoComment> getPhotoComments() {
		return photoComments;
	}
	public void setPhotoComments(Set<PhotoComment> photoComments) {
		this.photoComments = photoComments;
	}
	public int getKeepDays() {
		return keepDays;
	}
	public void setKeepDays(int keepDays) {
		this.keepDays = keepDays;
	}
	public String getLastAddr() {
		return lastAddr;
	}
	public void setLastAddr(String lastAddr) {
		this.lastAddr = lastAddr;
	}
	@Enumerated(value=EnumType.STRING)
	public UserState getUserState() {
		return userState;
	}
	public void setUserState(UserState userState) {
		this.userState = userState;
	}
	@OneToMany(mappedBy="user")
	public Set<BlogColumn> getBlogColumns() {
		return blogColumns;
	}
	public void setBlogColumns(Set<BlogColumn> blogColumns) {
		this.blogColumns = blogColumns;
	}
	@OneToMany(mappedBy="user")
	public Set<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(Set<Favourite> favourites) {
		this.favourites = favourites;
	}
}
