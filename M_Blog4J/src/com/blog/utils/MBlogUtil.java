package com.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MBlogUtil {
	public static void dateFormat(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		df.format(date);
	}
}
