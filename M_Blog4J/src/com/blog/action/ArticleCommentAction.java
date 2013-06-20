package com.blog.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.blog.BlogSecurityManager;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.entity.User;
import com.blog.service.ArticleCommentService;
import com.blog.service.ArticleService;

public class ArticleCommentAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArticleService articleServiceBean;
	private ArticleCommentService articleCommentServiceBean;
	private ArticleComment articleComment;
	private String commentId;


	public String addArticleComment(){
		int articleId = Integer.parseInt(request.getParameter("id"));  //id为文章的id
		Article article = articleServiceBean.find(articleId);
		articleComment.setCommentTime(new Date());
		User user = getLoginUser(request, response);
		articleComment.setUser(user);
		articleComment.setArticle(article);
		String content = BlogSecurityManager.autoGlossaryFiltrate(articleComment.getComment());
		articleComment.setComment(content);
		int parentId = Integer.parseInt(commentId);  //如果parentid为-1表示是文章评论，如果不是-1则代表是评论的回复
		if(parentId == -1){
			articleCommentServiceBean.save(articleComment);
		}else{
			ArticleComment ac = articleCommentServiceBean.find(parentId);
			ac.getChildren().add(articleComment);
			articleComment.setParentId(ac);
			articleCommentServiceBean.update(ac);
		}

		ArrayList<ArticleComment> comments = articleCommentServiceBean.getCommentByArticle(articleId);

		printComment(comments);


		request.setAttribute("article", article);
		request.setAttribute("comments", comments);
		
		HashMap<String, String> articleMessage = new HashMap<String, String>();
		articleMessage = articleServiceBean.getArticleMessageByUserId(article.getUser().getUserId());
		request.setAttribute("articleMessage", articleMessage);
		
		return "addArticleComment";
	}

	/**
	 * 以时间顺序组合评论，时间最晚的在最前面，顶层评论下面有回复
	 * @param articleId
	 * @return
	 */
	/*private ArrayList<ArrayList<ArticleComment>> getCommentByArticle(int articleId){

	}*/

	private void printComment(Collection<ArticleComment> comments){
		for(ArticleComment ac : comments){
			if(ac.getChildren().size() == 0)
				System.out.println(ac.getComment() + "===");
			else{
				System.out.println(ac.getComment());
				printComment(ac.getChildren());
			}
		}
	}
	
	public String deleteComment(){
		String commentId = request.getParameter("commentId");
		articleCommentServiceBean.deleteCommnet(Integer.parseInt(commentId));
		return "deleteComment";
	}

	public ArticleService getArticleServiceBean() {
		return articleServiceBean;
	}
	@Resource
	public void setArticleServiceBean(ArticleService articleServiceBean) {
		this.articleServiceBean = articleServiceBean;
	}

	public ArticleComment getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(ArticleComment articleComment) {
		this.articleComment = articleComment;
	}



	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public ArticleCommentService getArticleCommentServiceBean() {
		return articleCommentServiceBean;
	}
	@Resource
	public void setArticleCommentServiceBean(
			ArticleCommentService articleCommentServiceBean) {
		this.articleCommentServiceBean = articleCommentServiceBean;
	}
}
