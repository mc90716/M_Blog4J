package com.blog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import com.blog.utils.StringUtil;

public class BlogSecurityManager {
	
	private final static String file_glossary = "/WEB-INF/config/illegal_glossary.dat";
	
	private static  List<String> glossary = new ArrayList<String>();
	
	public static void init(ServletContext sc){
		if(sc != null){
			loadGlossary(sc);
		}
	}
	
	/**
	 * ¼ÓÔØÃô¸Ð´Ê»ã
	 */
	private static void loadGlossary(ServletContext sc){
		InputStream is = sc.getResourceAsStream(file_glossary);
		BufferedReader bufferReader = null;
		try{
			bufferReader = new BufferedReader(new InputStreamReader(is));
			do{
				String line = bufferReader.readLine();
				if(line == null){
					break;
				}
				glossary.add(line.trim());
			}while(true);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ÅÐ¶ÏÊÇ·ñ´æÔÚÃô¸Ð´Ê»ã
	 */
	public static boolean existIllegalWord(String content){
		if(StringUtil.isEmpty(content)){
			return false;
		}
		for(String word : glossary){
			if(content.indexOf(word) >= 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ×Ô¶¯½«Ãô¸Ð´Ê»ãÌæ»»³ÉXX
	 */
	public static String autoGlossaryFiltrate(String content){
		if(StringUtil.isEmpty(content)){
			return content;
		}
		for(String word : glossary){
			content = StringUtils.replace(content, word, StringUtils
					.repeat("X", word.length()));
		}
		return content;
	}
	
	/**
	 * É¾³ýÃô¸Ð´Ê»ã
	 */
	public static String deleteIlegalWord(String content){
		if(StringUtil.isEmpty(content)){
			return content;
		}
		for(String word : glossary){
			content = StringUtil.remove(content, word);
		}
		return content;
	}
	
	public static void destroy(){
		if(glossary != null){
			glossary.clear();
		}
	}
}
