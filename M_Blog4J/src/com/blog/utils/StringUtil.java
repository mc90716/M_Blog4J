package com.blog.utils;

public class StringUtil extends org.apache.commons.lang.StringUtils{
	/**
	 * 判断邮件地址格式是否合法
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email==null)
			return false;
		email = email.trim();
		if(email.indexOf(' ')!=-1)
			return false;
		
		int idx = email.indexOf('@');
		if(idx==-1 || idx==0 || (idx+1)==email.length())
			return false;
		if(email.indexOf('@', idx+1)!=-1)
			return false;
        if(email.indexOf('.')==-1)
            return false;
		return true;
	}
}
