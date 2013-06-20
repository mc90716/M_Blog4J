package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.entity.BlogColumn;
import com.blog.entity.TagCloud;
import com.blog.entity.User;
import com.blog.service.ArticleCommentService;
import com.blog.service.ArticleService;
import com.blog.service.BlogColumnService;
import com.blog.service.TagCloudService;

public class ColumnAction extends BaseAction {
	
	private ArticleService articleServiceBean;
	private ArticleCommentService articleCommentServiceBean;
	private BlogColumnService blogColumnServiceBean;
	private TagCloudService tagCloudServiceBean;
	
	
	@Override
	public String execute() throws Exception {
		String currentPage = request.getParameter("currentPage");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		if(currentPage == null)
			currentPage = "1";
		PageView<Article> pageView_articleList = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		pageView_articleList.setQueryResult(articleServiceBean.getScrollData(pageView_articleList.getFirstResult(),20,orderby));
		request.setAttribute("pageView_articleList", pageView_articleList);
		
		orderby.clear();
		orderby.put("visitorsCount", "desc");
		PageView<Article> pageView_articleTitle = new PageView<Article>(15, 1);
		QueryResult<Article> queryResult = new QueryResult<Article>();
		queryResult.setResultList(articleServiceBean.getHotArticle());
		pageView_articleTitle.setQueryResult(queryResult);
		request.setAttribute("pageView_articleTitle", pageView_articleTitle);
		
		orderby.clear();
		orderby.put("commentTime", "desc");
		PageView<ArticleComment> pageView_articleComment = new PageView<ArticleComment>(8, 1);
		pageView_articleComment.setQueryResult(articleCommentServiceBean.getScrollData(pageView_articleComment.getFirstResult(),Globals.MAXRESULT,orderby));
		request.setAttribute("pageView_articleComment", pageView_articleComment);
		
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		
		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		blogColumns = blogColumnServiceBean.getHotBlogColumn();
		request.setAttribute("blogColumns", blogColumns);
		
		return super.execute();
	}
	
	
	public String showCategoryArticle(){
		String categoryId = request.getParameter("categoryId");
		String currentPage = request.getParameter("currentPage");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		if(currentPage == null)
			currentPage = "1";
		Integer[] queryParams = {Integer.parseInt(categoryId)};
		PageView<Article> pageView_articleList = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		pageView_articleList.setQueryResult(articleServiceBean.getScrollData(pageView_articleList.getFirstResult(), Globals.MAXRESULT," o.category.categoryId = ?1",queryParams,orderby));
		request.setAttribute("pageView_articleList", pageView_articleList);
		
		orderby.clear();
		orderby.put("visitorsCount", "desc");
		PageView<Article> pageView_articleTitle = new PageView<Article>(15, 1);
		QueryResult<Article> queryResult = new QueryResult<Article>();
		queryResult.setResultList(articleServiceBean.getHotArticle());
		pageView_articleTitle.setQueryResult(queryResult);
		request.setAttribute("pageView_articleTitle", pageView_articleTitle);
		
		orderby.clear();
		orderby.put("commentTime", "desc");
		PageView<ArticleComment> pageView_articleComment = new PageView<ArticleComment>(8, 1);
		pageView_articleComment.setQueryResult(articleCommentServiceBean.getScrollData(pageView_articleComment.getFirstResult(), Globals.MAXRESULT,orderby));
		request.setAttribute("pageView_articleComment", pageView_articleComment);
		
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		
		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		blogColumns = blogColumnServiceBean.getHotBlogColumn();
		request.setAttribute("blogColumns", blogColumns);
		
		return "showCategoryArticle";
	}
	
	public String viewColumnBlog(){
		String columnId = request.getParameter("columnId");
		ArrayList<Article> list = new ArrayList<Article>();
		list = articleServiceBean.getColumnBlogByColumnId(Integer.parseInt(columnId));
		request.setAttribute("columnBlogList", list);
		
		SessionUserObject suo = getLoginUser(request, response);
		
		HashMap<String, String> articleMessage = new HashMap<String, String>();
		BlogColumn bc = blogColumnServiceBean.find(Integer.parseInt(columnId));
		articleMessage = articleServiceBean.getArticleMessageByUserId(bc.getUser().getUserId());
		request.setAttribute("articleMessage", articleMessage);
		
		User user = articleServiceBean.getUserIdByColumnId(Integer.parseInt(columnId));
		request.setAttribute("user", user);
		
		return "viewColumnBlog";
	}
	

	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}

	public ArticleCommentService getArticleCommentServiceBean() {
		return articleCommentServiceBean;
	}
	@Resource
	public void setArticleCommentServiceBean(
			ArticleCommentService articleCommentServiceBean) {
		this.articleCommentServiceBean = articleCommentServiceBean;
	}


	public BlogColumnService getBlogColumnServiceBean() {
		return blogColumnServiceBean;
	}
	@Resource
	public void setBlogColumnServiceBean(BlogColumnService blogColumnServiceBean) {
		this.blogColumnServiceBean = blogColumnServiceBean;
	}


	public TagCloudService getTagCloudServiceBean() {
		return tagCloudServiceBean;
	}
	@Resource
	public void setTagCloudServiceBean(TagCloudService tagCloudServiceBean) {
		this.tagCloudServiceBean = tagCloudServiceBean;
	}
	
}
