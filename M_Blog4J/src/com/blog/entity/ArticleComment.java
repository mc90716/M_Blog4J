package com.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="blog_articlecomment")
public class ArticleComment {
	private int commentId;
	private String comment;
	private User user;
	private Article article;
	private Date commentTime;
	private ArticleComment parentId;
	private Set<ArticleComment> children = new HashSet<ArticleComment>();
	
	@Id
	@GeneratedValue
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="articleId")
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@ManyToOne
	@JoinColumn(name="parentId")
	public ArticleComment getParentId() {
		return parentId;
	}
	public void setParentId(ArticleComment parentId) {
		this.parentId = parentId;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy="parentId")
	public Set<ArticleComment> getChildren() {
		return children;
	}
	public void setChildren(Set<ArticleComment> children) {
		this.children = children;
	}
	
}
