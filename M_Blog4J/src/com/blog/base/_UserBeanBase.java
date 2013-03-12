package com.blog.base;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户资料类的基类
 * @author 马超
 *
 */
public abstract class _UserBeanBase {
	public final static int ROLE_COMMON = 0x00;  //普通用户
	public final static int ROLE_VISITOR = 0x01;  //游客
	public final static int ROLE_ADMINISTRATOR = 0x02;  //管理员
	
	public final static int SEX_MALE = 0x01;
	public final static int SEX_FEMALE = 0x02;
	
	private int age = -1;
	
	private String name;  //用户名，即userName
	private String nickName;  //昵称，即playName 
	private Date brith;
	private int sex;
	
	private String portrait;	//头像
	private int role = ROLE_COMMON;	 //角色
	
	private Timestamp regTime;	//帐号注册时间
	private Timestamp lastTime;	//最后一次登录的时间
	private String lastAddr;	//第一次注册的IP地址或者是最后一次登录的IP地址
	
	private int status;		//帐号状态
	private int keepDays;		//登录资料的有效时间,单位:天
	
	private ContactInfo contactInfo;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBrith() {
		return brith;
	}

	public void setBrith(Date brith) {
		this.brith = brith;
		if(this.brith != null){
			Calendar cal = Calendar.getInstance();
			int cur_year = cal.get(Calendar.YEAR);
			cal.setTime(brith);
			int the_year = cal.get(Calendar.YEAR);
			age = cur_year - the_year;
		}
		else{
			age = -1;
		}
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastAddr() {
		return lastAddr;
	}

	public void setLastAddr(String lastAddr) {
		this.lastAddr = lastAddr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getKeepDays() {
		return keepDays;
	}

	public void setKeepDays(int keepDays) {
		this.keepDays = keepDays;
	}
	
	public String getQq(){
		return contactInfo.getQq();
	}
	
	public void setQq(String qq){
		contactInfo.setQq(qq);
	}
	
	public String getEmail(){
		return contactInfo.getEmail();
	}
	
	public void setEmail(String email){
		contactInfo.setEmail(email);
	}
	
	public String getMobile(){
		return contactInfo.getMobile();
	}
	
	public void setMobile(String mobile){
		contactInfo.setMobile(mobile);
	}
	
	public String getHomePage(){
		return contactInfo.getHomePage();
	}
	
	public void setHomePage(String homePage){
		contactInfo.setHomePage(homePage);
	}
}
