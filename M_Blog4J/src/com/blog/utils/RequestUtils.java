package com.blog.utils;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.service.UserService;

public class RequestUtils {
	/**
	 * ���Header��Ϣ�����ִ�Сд�޹�
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getHeader(HttpServletRequest req, String name){
		String value = req.getHeader(name);
		if(value != null)
			return value;
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()){
			String n = (String)names.nextElement();
			if(n.equalsIgnoreCase(name)){
				return req.getHeader(n);
			}
		}
		return null;
	}
	/**
	 * �ж�������Ƿ�֧��GZIPѹ��
	 * @param req
	 * @return
	 */
	public static boolean isGZIPSupported(HttpServletRequest req){
		String browserEncodings = req.getHeader("accept-encoding");
		return (browserEncodings != null)&&(browserEncodings.indexOf("gzip") != -1);
	}

	/**
	 * ����Ϣд��Cookies
	 */
	public static void setCookies(HttpServletRequest request,HttpServletResponse response,String key,String value,int maxAge){
		Cookie cookie  = new Cookie(key,value);
		cookie.setMaxAge(maxAge);
		String serverName = request.getServerName();
		String domain = getDomainOfServerName(serverName);
		if((domain!=null)&&(domain.indexOf('.')!=-1)){
			cookie.setDomain('.' + domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	/**
	 * �����ַ�����ȡCookies
	 * @param request
	 * @param str
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request,String str){
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;
		for(Cookie ck : cookies){
			if(ck.getName().equals(str))
			{
				return ck;
			}
		}
		return null;
	}

	/**
	 * ��ȡ�û�����URL�еĸ�����
	 * ����: www.dlog.cn -> dlog.cn
	 * @param req
	 * @return
	 */
	public static String getDomainOfServerName(String host){
		String[] names = StringUtils.split(host, '.');
		int len = names.length;
		if(len >= 2){
			return names[len-2] + '.' + names[len-1];
		}
		return host;
	}
}
