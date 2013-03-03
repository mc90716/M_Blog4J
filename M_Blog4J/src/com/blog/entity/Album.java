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

@Entity(name="blog_album")
public class Album {
	private int albumId;
	private User user;
	private String albumName;
	private String albumDesc;
	private int isLock;
	private String lockPasswd;
	private int pictureCount;
	private Date createTime;
	private Set<Photo> photos = new HashSet<Photo>();
	
	@OneToMany(mappedBy="album")
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id @GeneratedValue
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumDesc() {
		return albumDesc;
	}
	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}
	
	public String getLockPasswd() {
		return lockPasswd;
	}
	public void setLockPasswd(String lockPasswd) {
		this.lockPasswd = lockPasswd;
	}
	public int getPictureCount() {
		return pictureCount;
	}
	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsLock() {
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}
}
