package com.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="blog_say")
public class Say {
	private int sayId;
	private String content;
	private Date sayTime;
	private User user;
	private Set<SayComment> sayCommments = new HashSet<SayComment>();
	
	@OneToMany(mappedBy="say")
	public Set<SayComment> getSayCommments() {
		return sayCommments;
	}
	public void setSayCommments(Set<SayComment> sayCommments) {
		this.sayCommments = sayCommments;
	}
	@Id @GeneratedValue
	public int getSayId() {
		return sayId;
	}
	public void setSayId(int sayId) {
		this.sayId = sayId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSayTime() {
		return sayTime;
	}
	public void setSayTime(Date sayTime) {
		this.sayTime = sayTime;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
