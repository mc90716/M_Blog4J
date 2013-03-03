package com.blog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="blog_photo")
public class Photo {
	private int photoId;
	private String photoName;
	private String photoDesc;
	private Date addTime;
	private int isPortrait;
	private int isCover;
	private Album album;
	
	@ManyToOne
	@JoinColumn(name="albumId")
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	@Id @GeneratedValue
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoDesc() {
		return photoDesc;
	}
	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
	}
	public Date getAddTime() {
		return addTime;
	}
	public int getIsPortrait() {
		return isPortrait;
	}
	public void setIsPortrait(int isPortrait) {
		this.isPortrait = isPortrait;
	}
	public int getIsCover() {
		return isCover;
	}
	public void setIsCover(int isCover) {
		this.isCover = isCover;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
