package com.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.BlogSecurityManager;

/**
 * Servlet implementation class Blog_ActionServlet
 */
public class Blog_ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		BlogSecurityManager.init(sc);      //安全控制，初始化敏感信息的列表
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void destroy() {
		BlogSecurityManager.destroy();
		super.destroy();
	}
}
