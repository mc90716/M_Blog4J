package com.blog;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.blog.formbean.UserForm;

public class InitVisitor extends SiteVisit implements Runnable{
	
	Thread demo;
	boolean stop = false;
	protected List<VisitRecord> list = new Vector<InitVisitor.VisitRecord>();
	
	public void init(ServletContext context){
		super.init(context);
		demo = new Thread(this);
		demo.start();
	}
	
	@Override
	public void run() {
		while(!stop){
			if(list.size() == 0){
				try {
					Thread.sleep(500);
					continue;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public int getDataByDate(UserForm userForm) {
		return 0;
	}

	@Override
	public int getDataByMonth(UserForm userForm) {
		return 0;
	}

	@Override
	public int getDataByYear(UserForm userForm) {
		return 0;
	}
	

	@Override
	public void visit(HttpServletRequest request) {
		VisitRecord vr = new VisitRecord(request);
		list.add(vr);
	}
	
	
	/**
	 * ÄÚ²¿Àà
	 */
	class VisitRecord{
		UserForm userForm;
		String remoteAddr;
		String requestURL;
		boolean newSession;
		String refererURL;
		String userAgent;
		Date visitTime;
		
		public VisitRecord(HttpServletRequest req){
			remoteAddr = req.getRemoteAddr();
			StringBuffer str = req.getRequestURL();
			if(req.getQueryString() != null){
				str.append("?");
				str.append(req.getQueryString());
			}
			requestURL = str.toString();
			refererURL = req.getHeader("referer");
			userAgent = req.getHeader("user-agent");
			HttpSession ssn = req.getSession();
			newSession = (ssn==null || ssn.isNew());
			visitTime = new Date();
		}
	}
}
