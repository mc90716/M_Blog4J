<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="com.blog.*,com.blog.entity.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Article article = (Article)request.getAttribute("article");
	ArrayList<ArticleComment> articleComments = (ArrayList<ArticleComment>)request.getAttribute("comments");
	
	HashMap<String,String> articleMessage = (HashMap<String,String>)request.getAttribute("articleMessage");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>Home - Home Page | BizSolutions - Free Website Template
	from Templates.com</title>
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" type="text/css" href="miaov_style.css" />
<link rel="stylesheet" href="css/log.css" type="text/css" media="all">
<script type="text/javascript" src="js/maxheight.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="miaov.js"></script>
<script type="text/javascript" src="js/article.js"></script>
<script type="text/javascript" src="js/log.js"></script>
<!--[if lt IE 7]>
		<link rel="stylesheet" href="css/ie6.css" type="text/css" media="screen">
		<script type="text/javascript" src="js/ie_png.js"></script>
		<script type="text/javascript">
			ie_png.fix('.png');
		</script>
	<![endif]-->
<!--[if lt IE 9]>
		<script type="text/javascript" src="js/html5.js"></script>
	<![endif]-->
</head>
<body id="page1">

<div id="bg"></div>

	<div>
		<div id="main">
			<div class="body">
				<div class="blogcontentmain">
					<div class="blog_list">
						<div class="blog_content">
							<div class="blog_title">
								<img src="images/ico_Original.gif" class="blog_icon">
								<h3 class="blogtitle_h3">
									<a href="adminArticleAction!showOneArticle.action?articleId=<%=article.getArticleId() %>"><%=article.getTitle() %> </a>
								</h3>
							</div>
							
							<div class="blog_content"><%=article.getContent() %></div>


							<!-- <div class="article_next_prev">
								<li class="article_pre"><span>上一篇：</span><a href="#">文章标题</a></li>
								<li class="article_pre"><span>下一篇：</span><a href="#">文章标题</a></li>
							</div> -->

							<div class="comment_title">查看评论</div>

							
							<div class="comment_list">
							<%for(ArticleComment ac : articleComments) {
							    String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ac.getCommentTime());
							   if(ac.getParentId() == null){
							%>
								<dl class="comment_topic">
									<dt class="comment_head">
										<span class="user"> <a class="comment_username"
											target="_blank" href="#"><%=ac.getUser().getUserName() %></a>
											<input type="hidden" class="commentId" value="<%= ac.getCommentId()%>">
											 <span class="comment_time"><%=date %></span> 
											 <a class="cmt_btn_reply" title="<%=ac.getCommentId() %>" href="javascript:void(0);" onclick="replayComment(this);" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</span>
									</dt>
									<dd class="comment_userface">
										<a target="_blank" href="#"><img width="40" height="40"
											src="images/smallphoto.jpg"></a>
									</dd>
									<dd class="comment_body"><%=ac.getComment() %></dd>
								</dl>
								<%}%>
								<%if(ac.getChildren().size() != 0) {
									Set<ArticleComment> s = ac.getChildren();
									for(ArticleComment a : s){
								%>
								
								 <dl class="comment_topic_reply">
									<dt class="comment_head">
										<span class="user"> <a class="comment_username"
											target="_blank" href="#"><%=a.getUser().getUserName() %></a>
											 <span class="comment_time"><%=date %></span> 
											  <a class="cmt_btn_reply" title="<%=ac.getCommentId() %>" href="javascript:void(0);" onclick="replayComment(this);" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</span>
									</dt>
									<dd class="comment_userface">
										<a target="_blank" href="#"><img width="40" height="40"
											src="images/smallphoto.jpg"></a>
									</dd>
									<dd class="comment_body"><%=a.getComment() %></dd>
								</dl>  
								<%}%>
							<%}%>
							<%}%>
							</div>

						</div>
					</div>
				</div>
			</div>
			
			
			<!-- footer -->
			
		</div>
		 
</body>
</html>