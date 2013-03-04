package com.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="blog_article")
public class Article {
	private int articleId;
	private String title;
	private String content;
	private User user;
	private Category category;
	private int visitorsCount;
	private Date createTime;
	private Set<ArticleComment> articleComments = new HashSet<ArticleComment>();
	private Set<Favourite> favourites = new HashSet<Favourite>();
	
	
	@Id
	@GeneratedValue
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne
	@JoinColumn(name="authorId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getVisitorsCount() {
		return visitorsCount;
	}
	public void setVisitorsCount(int visitorsCount) {
		this.visitorsCount = visitorsCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@OneToMany(mappedBy="article")
	public Set<ArticleComment> getArticleComments() {
		return articleComments;
	}
	public void setArticleComments(Set<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}
	@ManyToOne
	@JoinColumn(name="categoryId")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@ManyToMany
	@JoinTable(name="blog_favouritearticle",
	         joinColumns={@JoinColumn(name="articleId")},
	         inverseJoinColumns={@JoinColumn(name="favouriteId")})
	public Set<Favourite> getFavourites() {
		return favourites;
	}
	public void setFavourites(Set<Favourite> favourites) {
		this.favourites = favourites;
	}
}
