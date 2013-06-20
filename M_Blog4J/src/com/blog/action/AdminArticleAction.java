package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.bean.PageView;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.service.ArticleCommentService;
import com.blog.service.ArticleService;
import com.googlecode.jsonplugin.annotations.JSON;

public class AdminArticleAction extends BaseAction{

	private ArticleService articleServiceBean;
	private ArticleCommentService articleCommentServiceBean;
	private String articleId;
	private String recommendResult;

	public String showAllArticleByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		orderby.put("visitorsCount", "desc");
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,orderby));
		request.setAttribute("allArticleByPage", pageView);
		return "showAllArticleByPage";
	}
	
	public String showOneArticle(){
		int id = Integer.parseInt(request.getParameter("articleId"));
		Article article = articleServiceBean.find(id);
		article.setVisitorsCount(article.getVisitorsCount() + 1);
		articleServiceBean.save(article);
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getCommentByArticle(id);
		request.setAttribute("article", article);
		request.setAttribute("comments", comments);
		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(article.getUser().getUserId());
		request.setAttribute("articleMessage", articleMessage);
		return "showOneArticle";
	}
	

	public String recommendArticle(){
		articleServiceBean.recomArticle(Integer.parseInt(articleId));
		recommendResult = "recommendResult";
		return "recommendArticle";
	}

	public String cancelRecommendArticle(){
		String articleId = request.getParameter("articleId");
		articleServiceBean.cancelRecomArticle(Integer.parseInt(articleId));
		return "cancelRecommendArticle";
	}

	public String showALlHotArticleBtPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		Object[] queryParams = new Object[]{-1};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.delFlag = ?1", queryParams, orderby));
		request.setAttribute("hotArticleList", pageView);
		return "showALlHotArticleBtPage";
	}

	public String showAllCommentByPage(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageView<ArticleComment> pageView = new PageView<ArticleComment>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("commentTime", "desc");
		pageView.setQueryResult(articleCommentServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, orderby));
		request.setAttribute("articleCommentList", pageView);
		return "showAllCommentByPage";
	}

	public String deleteComment(){
		String commentId = request.getParameter("commentId");
		articleCommentServiceBean.deleteCommnet(Integer.parseInt(commentId));
		return "deleteComment";
	}


	public String deleteArticle(){
		String articleId = request.getParameter("articleId");
		articleServiceBean.delete(Integer.parseInt(articleId));
		return "deleteArticle";
	}

	public String searchArticle(){

		String keyWord_category = request.getParameter("keyWord_category");
		String keyWord_user = request.getParameter("keyWord_user");
		String keyWord_content = request.getParameter("keyWord_content");
		if(keyWord_content != ""){
			String wherejpql = "o.content like ?1";
			PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
			keyWord_content = "%" + keyWord_content + "%";
			String[] queryParams = {keyWord_content}; 
			pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(articleServiceBean.getCount());
			request.setAttribute("allArticleByPage", pageView);
		}
		if(keyWord_user != ""){
			String wherejpql = "o.user.userName like ?1";
			PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
			keyWord_user = "%" + keyWord_user + "%";
			String[] queryParams = {keyWord_user}; 
			pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(articleServiceBean.getCount());
			request.setAttribute("allArticleByPage", pageView);
		}
		if(keyWord_category != ""){
			String wherejpql = "o.category.categoryName like ?1";
			PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
			keyWord_category = "%" + keyWord_category + "%";
			String[] queryParams = {keyWord_category}; 
			pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
			pageView.setTotalrecord(articleServiceBean.getCount());
			request.setAttribute("allArticleByPage", pageView);
		}
		if((keyWord_content == "")&&(keyWord_user == "")&&(keyWord_category == "")){
			return "searchNull";
		}
		return "searchArticle";
	}

	public String searchCommentByComment(){
		String keyWord_content = request.getParameter("keyWord_content");
		String wherejpql = "o.comment like ?1";
		PageView<ArticleComment> pageView = new PageView<ArticleComment>(Globals.MAXRESULT, 1);
		keyWord_content = "%" + keyWord_content + "%";
		String[] queryParams = {keyWord_content}; 
		pageView.setQueryResult(articleCommentServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams));
		pageView.setTotalrecord(articleCommentServiceBean.getCount());
		request.setAttribute("articleCommentList", pageView);
		return "searchCommentByComment";
	}


	@JSON(serialize=false)
	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getRecommendResult() {
		return recommendResult;
	}
	@JSON(serialize=false)
	public ArticleCommentService getArticleCommentServiceBean() {
		return articleCommentServiceBean;
	}
	@Resource
	public void setArticleCommentServiceBean(
			ArticleCommentService articleCommentServiceBean) {
		this.articleCommentServiceBean = articleCommentServiceBean;
	}
}
