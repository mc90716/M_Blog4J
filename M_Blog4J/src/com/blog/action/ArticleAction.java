package com.blog.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONException;

import com.blog.BlogSecurityManager;
import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.entity.BlogColumn;
import com.blog.entity.Category;
import com.blog.entity.Favourite;
import com.blog.entity.User;
import com.blog.service.ArticleCommentService;
import com.blog.service.ArticleService;
import com.blog.service.BlogColumnService;
import com.blog.service.FavouriteService;
import com.blog.service.UserService;
import com.googlecode.jsonplugin.annotations.JSON;

public class ArticleAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ArticleService articleServiceBean;
	private ArticleCommentService articleCommentServiceBean;
	private BlogColumnService blogColumnServiceBean;
	private Article article;
	private Category category;
	private String currentpage;
	Map<String, Object> message = new HashMap<String, Object>();
	private String pageCount;
	private FavouriteService favouriteServiceBean;
	private String articleId;
	private UserService userServiceBean;
	private String favouriteFlag;

	public String publishArticle(){
		article.setCategory(category);
		article.setCreateTime(new Date());
		article.setVisitorsCount(0);
		String title = BlogSecurityManager.autoGlossaryFiltrate(article.getTitle());
		article.setTitle(title);
		String content = BlogSecurityManager.autoGlossaryFiltrate(article.getContent());
		article.setContent(content);
		SessionUserObject suo = getLoginUser(request, response);
		User u = userServiceBean.find(suo.getUserId());
		article.setUser(u);
		article.setDelFlag(1);
		articleServiceBean.save(article);

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, currentPage);
		suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1", queryParams));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);

		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);
		
		
		
		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);

		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);

		return "manageAllArticle";
	}



	public String updateArticle(){
		String id = request.getParameter("articleId");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		SessionUserObject suo = getLoginUser(request, response);
		Article art = articleServiceBean.find(Integer.parseInt(id));
		art.setCategory(category);
		art.setContent(article.getContent());
		art.setTitle(article.getTitle());
		art.setArticleType(article.getArticleType());
		articleServiceBean.update(art);

		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, currentPage);
		suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1", queryParams));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);

		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);
		
		
		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);

		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);
		return "updateArticle";
	}

	public String viewAllArticle(){
		System.out.println("viewAllArticle");
		int currentPage;
		if(request.getParameter("currentPage") == null){
			currentPage = 1;
		}else
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, currentPage);
		SessionUserObject suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		Integer[] params = {1};
		pageView.setQueryResult(articleServiceBean.getScrollData(0, 10," o.delFlag = ?1",params));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);
		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);

		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);

		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);
		
		return "manageAllArticle";
	}

	public String viewArticle(){ 
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = articleServiceBean.find(id);
		article.setVisitorsCount(article.getVisitorsCount() + 1);
		articleServiceBean.save(article);
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getCommentByArticle(id);
		request.setAttribute("article", article);
		request.setAttribute("comments", comments);
		SessionUserObject suo = getLoginUser(request, response);
		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(article.getUser().getUserId());
		request.setAttribute("articleMessage", articleMessage);
		if((suo != null)&&(suo.getUserId() == article.getUser().getUserId()))
			return "viewMyArticle";
		else
			return "viewArticle";
	}

	public String editArticle(){
		String articleid = request.getParameter("id");
		Article article = articleServiceBean.find(Integer.parseInt(articleid));
		request.setAttribute("article", article);
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);
		
		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article a : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(a.getArticleId());
			myArticleComments.put(a.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);
		
		
		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);
		return "editArticle";
	}

	public String deleteArticle(){
		String articleid = request.getParameter("id");
		Article a = articleServiceBean.find(Integer.parseInt(articleid));
		if(a != null)
			a.setDelFlag(0);
		articleServiceBean.update(a);
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
		SessionUserObject suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1 ", queryParams));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);
		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);

		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);

		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);
		return "deleteArticle";
	}

	
	public String cancelDelArticle(){
		String articleId = request.getParameter("articleId");
		Article a = articleServiceBean.find(Integer.parseInt(articleId));
		if(a != null)
			a.setDelFlag(1);
		articleServiceBean.update(a);
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
		SessionUserObject suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1 ", queryParams));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);
		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);

		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);

		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);
		return "cancelDelArticle";
	}
	
	
	public String shiftDelArticle(){
		String articleId = request.getParameter("articleId");
		SessionUserObject suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
		pageView.setQueryResult(articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1", queryParams));
		pageView.setTotalpage(articleServiceBean.getCountById(suo.getUserId()));
		request.setAttribute("pageView", pageView);
		
		
		ArrayList<ArticleComment> comments = articleCommentServiceBean.getNewCommentByUserId(suo.getUserId());
		request.setAttribute("comments", comments);
		
		ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(suo.getUserId());
		HashMap<String,ArrayList<ArticleComment>> myArticleComments = new HashMap<String, ArrayList<ArticleComment>>();
		for(Article article : articles){
			ArrayList<ArticleComment> cts = articleCommentServiceBean.getCommentByArticle(article.getArticleId());
			myArticleComments.put(article.getTitle(), cts);
		}
		request.setAttribute("myArticleComments", myArticleComments);
		
		articleServiceBean.delete(Integer.parseInt(articleId));
		ArrayList<BlogColumn> blogColumn = blogColumnServiceBean.getBlogColumnContent(suo.getUserId());
		request.setAttribute("blogColumn", blogColumn);
		HashMap<Integer,Article> recycleMap = articleServiceBean.getRecycleById(suo.getUserId());
		request.setAttribute("recycleMap", recycleMap);

		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(suo.getUserId());
		request.setAttribute("articleMessage", articleMessage);
		return "shiftDelArticle";
	}


	/**
	 * 分页浏览文章
	 * @return
	 * @throws JSONException 
	 */
	@JSON(serialize=false)
	public String getArticleByPage() throws JSONException{
		System.out.println("getArticleByPage");
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentpage)+1);
		SessionUserObject suo = getLoginUser(request, response);
		Integer[] queryParams = {suo.getUserId()};
		QueryResult<Article> queryResult = articleServiceBean.getScrollData(pageView.getFirstResult(), Globals.MAXRESULT, "o.user.userId=?1 and o.delFlag = 1", queryParams);		
		for(Article article : queryResult.getResultList()){
			message.put(article.getArticleId()+"", article.getTitle() + "@" + article.getCreateTime() + "@" + article.getVisitorsCount() + "@" + article.getArticleComments().size());
		}
		return "getArticleByPage";
	}

	@JSON(serialize=false)
	public String getTotalPage(){
		SessionUserObject suo = getLoginUser(request, response);
		PageView<Article> pageView = new PageView<Article>(Globals.MAXRESULT, 1);
		pageView.setTotalrecord(articleServiceBean.getCountById(suo.getUserId()));
		pageCount = pageView.getTotalpage() + "";

		return "getTotalPage";
	}

	public String collectArticle(){
		SessionUserObject suo = getLoginUser(request, response);

		ArrayList<Article> art = favouriteServiceBean.getArticleByUserId(suo.getUserId());
		boolean flag = false;
		for(Article article : art){
			if(article.getArticleId() == Integer.parseInt(articleId)){   //已经收藏
				flag = true;
			}
		}
		if(flag == false){
			Favourite favourite = new Favourite();
			favourite.setTime(new Date());
			Set<Article> articles = new HashSet<Article>();
			Article article = articleServiceBean.find(Integer.parseInt(articleId));
			articles.add(article);
			favourite.setArticles(articles);
			favourite.setUser(userServiceBean.find(suo.getUserId()));
			favouriteServiceBean.save(favourite);
			Set<Favourite> favourites = new HashSet<Favourite>();
			favourites.add(favourite);
			article.setFavourites(favourites);
			articleServiceBean.update(article);
			favouriteFlag = "true";
		}else{
			favouriteFlag = "false";
		}
		return "collectArticle";
	}


	@JSON(serialize=false)
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	@JSON(serialize=false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	@JSON(serialize=false)
	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public Map<String, Object> getMessage() {
		return message;
	}

	public String getPageCount() {
		return pageCount;
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
	@JSON(serialize=false)
	public BlogColumnService getBlogColumnServiceBean() {
		return blogColumnServiceBean;
	}
	@Resource
	public void setBlogColumnServiceBean(BlogColumnService blogColumnServiceBean) {
		this.blogColumnServiceBean = blogColumnServiceBean;
	}

	@JSON(serialize=false)
	public FavouriteService getFavouriteServiceBean() {
		return favouriteServiceBean;
	}
	@Resource
	public void setFavouriteServiceBean(FavouriteService favouriteServiceBean) {
		this.favouriteServiceBean = favouriteServiceBean;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@JSON(serialize=false)
	public UserService getUserServiceBean() {
		return userServiceBean;
	}
	@Resource
	public void setUserServiceBean(UserService userServiceBean) {
		this.userServiceBean = userServiceBean;
	}



	public String getFavouriteFlag() {
		return favouriteFlag;
	}
}
