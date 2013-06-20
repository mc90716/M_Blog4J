package com.blog.action;

import java.util.Date;

import javax.annotation.Resource;

import com.blog.BlogSecurityManager;
import com.blog.SessionUserObject;
import com.blog.entity.Article;
import com.blog.entity.BlogColumn;
import com.blog.entity.Category;
import com.blog.entity.User;
import com.blog.enumeration.ArticleType;
import com.blog.service.ArticleService;
import com.blog.service.BlogColumnService;

public class BlogColumnAction extends BaseAction{
	
	private BlogColumnService blogColumnServiceBean;
	private BlogColumn blogColumn;
	private Article article;
	private ArticleService articleServiceBean;
	

	
	public String addBlogColumn(){
		SessionUserObject suo = getLoginUser(request, response);
		User u = new User();
		u.setUserId(suo.getUserId());
		String categoryId = request.getParameter("categoryId");
		Category c = new Category();
		c.setCategoryId(Integer.parseInt(categoryId)+1);
		blogColumn.setUser(u);
		blogColumn.setCategory(c);
		blogColumn.setCreateTime(new Date());
		blogColumn.setHot(0);
		String title = BlogSecurityManager.autoGlossaryFiltrate(blogColumn.getColumnName());
		blogColumn.setColumnName(title);
		String intro = BlogSecurityManager.autoGlossaryFiltrate(blogColumn.getColumnIntro());
		blogColumn.setColumnIntro(intro);
		blogColumnServiceBean.save(blogColumn);
		
		return "addBlogColumn";
	}
	
	public String writeArticleToColumn(){
		String columnId = request.getParameter("columnId");
		request.setAttribute("columnId", columnId);
		return "writeArticleToColumn";
	}
	
	public String addArticleToCloumn(){
		article.setBlogColumn(blogColumn);
		BlogColumn bc = new BlogColumn();
		int columnId = Integer.parseInt(request.getParameter("columnId"));
		bc = blogColumnServiceBean.find(columnId);
		article.setBlogColumn(bc);
		article.setCategory(bc.getCategory());
		article.setCreateTime(new Date());
		SessionUserObject suo = getLoginUser(request, response);
		User u = new User();
		u.setUserId(suo.getUserId());
		article.setUser(u);
		article.setVisitorsCount(0);
		article.setArticleType(ArticleType.original);
		article.setDelFlag(1);
		articleServiceBean.save(article);
		return "addArticleToCloumn";
	}
	
	public String deleteArticleOfColumn(){
		int columnId = Integer.parseInt(request.getParameter("columnId"));
		blogColumnServiceBean.delete(columnId);
		return "deleteArticleOfColumn";
	}
	
	
	
	public BlogColumnService getBlogColumnServiceBean() {
		return blogColumnServiceBean;
	}
	@Resource
	public void setBlogColumnServiceBean(BlogColumnService blogColumnServiceBean) {
		this.blogColumnServiceBean = blogColumnServiceBean;
	}


	public BlogColumn getBlogColumn() {
		return blogColumn;
	}

	public void setBlogColumn(BlogColumn blogColumn) {
		this.blogColumn = blogColumn;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}
	
}
