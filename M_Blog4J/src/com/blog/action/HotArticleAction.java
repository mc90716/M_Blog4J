package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import com.blog.entity.Article;
import com.blog.service.ArticleService;

public class HotArticleAction extends BaseAction {
	
	private ArticleService articleServiceBean;

	public String showHotArticle(){
		HashMap<String, ArrayList<Article>> hotArticleHashMap = new HashMap<String, ArrayList<Article>>();
		HashMap<String,Long> articleCount = new HashMap<String, Long>();
		articleCount = articleServiceBean.getCountOfCategory();
		hotArticleHashMap = articleServiceBean.getHotArticleByCategory();
		request.setAttribute("hotArticleHashMap", hotArticleHashMap);
		request.setAttribute("articleCount", articleCount);
		return "showHotArticle";
	}
	
	
	
	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}
	
}
