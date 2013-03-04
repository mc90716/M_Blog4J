package com.blog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="blog_friend")
public class Friend {
	private int friendId;
	private User my;
	private User you;
	private Date time;
	
	@Id
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@ManyToOne
	@JoinColumn(name="myId")
	public User getMy() {
		return my;
	}
	public void setMy(User my) {
		this.my = my;
	}
	@ManyToOne
	@JoinColumn(name="youId")
	public User getYou() {
		return you;
	}
	public void setYou(User you) {
		this.you = you;
	}
	
	
}
