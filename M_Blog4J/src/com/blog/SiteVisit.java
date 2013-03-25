package com.blog;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.formbean.UserForm;

/**
 * 
 * @author Âí³¬
 *
 */
public abstract class SiteVisit {
	
	private ServletContext context;
	private static SiteVisit siteVisit;
	
	public void init(ServletContext context){
		this.context = context;
		this.siteVisit = this;
	}
	
	public final static SiteVisit getInstance() {
		return siteVisit;
	}
	
	public abstract void visit(HttpServletRequest request);
	
	public abstract int getDataByDate(UserForm userForm);
	
	public abstract int getDataByMonth(UserForm userForm);
	
	public abstract int getDataByYear(UserForm userForm);
}
