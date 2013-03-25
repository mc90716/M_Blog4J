package com.blog;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class VisitFilter implements Filter {
	
	protected ServletContext context;
	private InitVisitor initVisitor;
	
	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		initVisitorData(fConfig);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);
	}

	public void destroy() {

	}
	
	private void initVisitorData(FilterConfig fConfig){
		String initVisitor = fConfig.getInitParameter("initVisitor");
		
	}
}
