package com.blog.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.utils.RequestUtils;

/**
 * Servlet Filter implementation class GZIPFilter
 */
public class GZIPFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		if(RequestUtils.isGZIPSupported(req)){  //Ö§³ÖGZIPÑ¹Ëõ
			GZipResponse responseWrapper = new GZipResponse(res);
			chain.doFilter(request, responseWrapper);
			responseWrapper.flush();
		}
		else*/
			chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
