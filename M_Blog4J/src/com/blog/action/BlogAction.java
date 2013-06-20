package com.blog.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

import com.blog.Globals;
import com.blog.SessionUserObject;
import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.entity.BlogColumn;
import com.blog.entity.TagCloud;
import com.blog.entity.User;
import com.blog.formbean.FriendNewState;
import com.blog.service.ArticleCommentService;
import com.blog.service.ArticleService;
import com.blog.service.BlogColumnService;
import com.blog.service.FriendService;
import com.blog.service.TagCloudService;
import com.blog.utils.StringUtils;

public class BlogAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticleService articleServiceBean;
	private ArticleCommentService articleCommentServiceBean;
	private BlogColumnService blogColumnServiceBean;
	private FriendService friendServiceBean;
	private TagCloudService tagCloudServiceBean;
	
	
	@Override
	public String execute() throws Exception {
		String currentPage = request.getParameter("currentPage");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		if(currentPage == null)
			currentPage = "1";
		String wherejpql = "o.delFlag = ?1";
		Integer[] queryParams = {1};
		PageView<Article> pageView_articleList = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		pageView_articleList.setQueryResult(articleServiceBean.getScrollData(pageView_articleList.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams, orderby));
		pageView_articleList.setTotalrecord(articleServiceBean.getCount());
		
		List<Article> articles = pageView_articleList.getRecords();
		List<Article> articleList = new ArrayList<Article>();
		for(Article article : articles){
			StringUtils.extractText(article.getContent());
			articleList.add(article);
		}
		pageView_articleList.setRecords(articleList);
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
		pageView_articleComment.setQueryResult(articleCommentServiceBean.getScrollData(pageView_articleComment.getFirstResult(), Globals.MAXRESULT-4,orderby));
		request.setAttribute("pageView_articleComment", pageView_articleComment);

		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		blogColumns = blogColumnServiceBean.getHotBlogColumn();
		request.setAttribute("blogColumns", blogColumns);
		
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		
		return super.execute();
	}

	public String showCategoryArticle() throws Exception{
		String categoryId = request.getParameter("categoryId");
		String currentPage = request.getParameter("currentPage");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		if(currentPage == null)
			currentPage = "1";
		Integer[] queryParams = {Integer.parseInt(categoryId)};
		PageView<Article> pageView_articleList = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		pageView_articleList.setQueryResult(articleServiceBean.getScrollData(pageView_articleList.getFirstResult(), Globals.MAXRESULT," o.category.categoryId = ?1",queryParams,orderby));
		
		
		List<Article> articles = pageView_articleList.getRecords();
		List<Article> articleList = new ArrayList<Article>();
		for(Article article : articles){
			article.setContent(StringUtils.extractText(article.getContent()));
			System.out.println(article.getContent());
			articleList.add(article);
		}
		pageView_articleList.setRecords(articleList);
		
		
		
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

		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		blogColumns = blogColumnServiceBean.getHotBlogColumn();
		request.setAttribute("blogColumns", blogColumns);
		
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		return "showCategoryArticle";
	}

	public String showHomeCenter(){
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null)
			currentPage = "1";
		SessionUserObject suo = getLoginUser(request, response);
		ArrayList<User> friends = friendServiceBean.getUserByMyId(suo.getUserId());
		ArrayList<FriendNewState> friendNewStates = new ArrayList<FriendNewState>();
		for(User u : friends){
			ArrayList<Article> articles = articleServiceBean.getNewArticleByUserId(u.getUserId());
			for(Article article : articles){
				FriendNewState friendNewState = new FriendNewState();
				friendNewState.setUserId(article.getUser().getUserId());
				friendNewState.setUserName(article.getUser().getUserName());
				friendNewState.setType("article");
				friendNewState.setNewState(article.getTitle());
				friendNewState.setDate(article.getCreateTime());
				friendNewState.setStateId(article.getArticleId());
				friendNewStates.add(friendNewState);
			}
			ArrayList<ArticleComment> articleComments = articleCommentServiceBean.getNewCommentByUserId(u.getUserId());
			for(ArticleComment ac : articleComments){
				FriendNewState friendNewState = new FriendNewState();
				friendNewState.setUserId(ac.getUser().getUserId());
				friendNewState.setUserName(ac.getUser().getUserName());
				friendNewState.setType("comment");
				friendNewState.setNewState(ac.getComment());
				friendNewState.setDate(ac.getCommentTime());
				friendNewState.setStateId(ac.getArticle().getArticleId());
				friendNewStates.add(friendNewState);
			}
			ArrayList<BlogColumn> blogColumns = blogColumnServiceBean.getNewColumnByUserId(u.getUserId());
			for(BlogColumn bc : blogColumns){
				FriendNewState friendNewState = new FriendNewState();
				friendNewState.setUserId(bc.getUser().getUserId());
				friendNewState.setUserName(bc.getUser().getUserName());
				friendNewState.setType("column");
				friendNewState.setNewState(bc.getColumnName());
				friendNewState.setStateId(bc.getColumnId());
				friendNewStates.add(friendNewState);
			}
		}
		ArrayList<User> myFreinds = friendServiceBean.getUserByMyId(suo.getUserId());
		request.setAttribute("friendsNewState", friendNewStates);
		request.setAttribute("myFriends", myFreinds);
		return "showHomeCenter";
	}
	
	public String searchResult() throws Exception{
		String keyWord = request.getParameter("keyWord");
		List<Article> searchResult = articleServiceBean.searchArticleByKeyWord(keyWord);
		
		
		String currentPage = request.getParameter("currentPage");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		if(currentPage == null)
			currentPage = "1";
		String wherejpql = "o.delFlag = ?1";
		Integer[] queryParams = {1};
		PageView<Article> pageView_articleList = new PageView<Article>(Globals.MAXRESULT, Integer.parseInt(currentPage));
		/*pageView_articleList.setQueryResult(articleServiceBean.getScrollData(pageView_articleList.getFirstResult(), Globals.MAXRESULT,wherejpql,queryParams, orderby));
		pageView_articleList.setTotalrecord(articleServiceBean.getCount());*/
		
		pageView_articleList.setRecords(searchResult);
		pageView_articleList.setTotalrecord(searchResult.size());
		
		List<Article> articles = pageView_articleList.getRecords();
		List<Article> articleList = new ArrayList<Article>();
		for(Article article : articles){
			article.setContent(StringUtils.extractText(article.getContent()));
			System.out.println(article.getContent());
			articleList.add(article);
		}
		pageView_articleList.setRecords(articleList);
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
		pageView_articleComment.setQueryResult(articleCommentServiceBean.getScrollData(pageView_articleComment.getFirstResult(), Globals.MAXRESULT-4,orderby));
		request.setAttribute("pageView_articleComment", pageView_articleComment);

		ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
		blogColumns = blogColumnServiceBean.getHotBlogColumn();
		request.setAttribute("blogColumns", blogColumns);
		
		QueryResult<TagCloud> tagClouds = tagCloudServiceBean.getScrollData();
		request.setAttribute("tagClouds", tagClouds);
		return "searchResult";
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

	public FriendService getFriendServiceBean() {
		return friendServiceBean;
	}
	@Resource
	public void setFriendServiceBean(FriendService friendServiceBean) {
		this.friendServiceBean = friendServiceBean;
	}

	public TagCloudService getTagCloudServiceBean() {
		return tagCloudServiceBean;
	}
	@Resource
	public void setTagCloudServiceBean(TagCloudService tagCloudServiceBean) {
		this.tagCloudServiceBean = tagCloudServiceBean;
	}

}
