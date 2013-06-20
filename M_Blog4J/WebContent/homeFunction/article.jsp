<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="com.blog.*,com.blog.entity.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject)session.getAttribute("UserBean");
	Article article = (Article)request.getAttribute("article");
	ArrayList<ArticleComment> articleComments = (ArrayList<ArticleComment>)request.getAttribute("comments");
	
	HashMap<String,String> articleMessage = (HashMap<String,String>)request.getAttribute("articleMessage");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>M_Blog4J</title>
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" type="text/css" href="miaov_style.css" />

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
	<div class="tail-bottom">
		<div id="main">
			<!-- header -->
			<header>
				<div class="nav-box">
					<nav>
						<ul class="fright">
							<li class="current"><a href="blogAction!showHomeCenter.action"><img
									src="images/pic-home-act.gif" title="空间">我的空间</a></li>
							<li class="current">
							<a href="javascript:void(0);"><%= suo.getUserName() %></a></li>
							<li class="current">
							<a href="userAction!logoutUser.action">注销</a></li>
						</ul>
						<ul>
							<li class="current"><a href="blogAction.action?currentPage=1">首页</a></li>
							<li class="current"><a href="hotArticleAction!showHotArticle.action">热门文章</a></li>
							<!-- <li class="current"><a href="index.jsp">博客专家</a></li> -->
							<li class="current"><a href="columnAction.action?currentPage=1">博客专栏</a></li>
						</ul>
					</nav>
				</div>
			</header>


			<div class="body">
				<div class="blogside">
					<div class="panel_profile">
						<ul class="panel_head">
							<span>个人资料</span>
						</ul>
						<ul>
							<div class="blog_userface">
								<a href="#" target="_blank"><img src="images/photo.jpg"
									style="border: 1px solid #DDDDDD; margin: 10px;"></a><br>
							
								<input type="hidden" class="currentUserName" value="<%=article.getUser().getUserName() %>">
								<span><%=article.getUser().getUserName()  %></span><br>
								<input type="hidden" class="focusId" value="<%=article.getUser().getUserId()%>">
								
								<%if((suo != null)&&(suo.getUserId() != article.getUser().getUserId())) {%>
								<a href="javascript:void(0);" onclick="focusUser();">关注</a>
								<a href="javascript:void(0);" onclick="leaveMessage();">发私信</a>
								<%} %>
								<div style="border: 1px dashed #CCCCCC; margin-top: 10px;"></div>
								<ul class="blog_statistics">
									<li>原创:<span><%= articleMessage.get("original") %>篇</span></li>
									<li>转载:<span><%= articleMessage.get("repost") %>篇</span></li>
									<li>译文:<span><%= articleMessage.get("translated") %>篇</span></li>
									<!-- <li>评论:<span>0篇</span></li> -->
								</ul>
							</div>
						</ul>
					</div>
					<div class="panel_search">
						<ul class="panel_head">
							<span>文章搜索</span>
						</ul>
						<ul class="panel_body">
							<form action="#">
								<span> <input class="inputSearch" type="text"
									title="请输入关键字" name="searchWord">
								</span> <input class="btnSubmit" type="submit" title="搜索" value="">
							</form>
						</ul>
					</div>
					<!-- <div class="panel_list">
						<ul class="panel_head">
							<span>文章存档</span>
						</ul>
						<ul class="panel_body">
							<div class="article_list">
								<li><a href="homeFunction/viewCenter.jsp">2013年05月</a><span>(5)</span></li>
							</div>
						</ul>
					</div> -->
					<div class="panel_comment">
						<ul class="panel_head">
							<span>阅读排行</span>
						</ul>
						<ul class="panel_body">
							<div class="article_list">
							<% String article1 = articleMessage.get("article1");
							   String article2 = articleMessage.get("article2");
							%>
								<li><a href="articleAction!viewArticle.action?id=<%=article1.split("@")[2] %>"><%=article1.split("@")[0] %></a><span>(<%=article1.split("@")[1] %>)</span></li>
								<li><a href="articleAction!viewArticle.action?id=<%=article2.split("@")[2] %>"><%=article2.split("@")[0] %></a><span>(<%=article2.split("@")[1] %>)</span></li>
							</div>
						</ul>
					</div>
					<div class="panel_support">推荐文章</div>
				</div>
				<div class="blogcontentmain">
					<!-- <div class="blog_top"></div> -->
					<div class="blog_list">



						<div class="blog_content">
							<div class="blog_title">
								<img src="images/ico_Original.gif" class="blog_icon">
								<h3 class="blogtitle_h3">
									<a href="#"><%=article.getTitle() %> </a>
								</h3>
							</div>
							<div class="blog_manage">
								<span class="blogmanage_time">2013-05-09 22:33</span> <span
									class="blogmanage_read"><img src="images/ico_view.png"
									style="margin-top: 8px; margin-right: 3px;"><a
									href="javascript:void(0);">阅读</a><%=article.getVisitorsCount() %></span> <span
									class="blogmanage_comment"><img
									src="images/ico_comm.png"
									style="margin-top: 8px; margin-right: 3px;"><a
									href="javascript:void(0;)">评论</a><%=article.getArticleComments().size()%></span>
									<% if(suo != null) {%>
									 <span class="blogmanage_edit">
									<input type="hidden" class="articleId" value=<%=article.getArticleId() %>>
									 <a href="javascript:favourite();">收藏</a></span>
									 <%} %>
									  <span class="blogmanage_del">
									<!--<a href="javascript:alert('delete the article');">删除</a> -->
									</span>
							</div>
							<div class="blog_content"><%=article.getContent() %></div>


					<!-- 		<div class="article_next_prev">
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
							
								

							

							<div class="comment_form">
								<form action="articleCommentAction!addArticleComment.action?id=<%=article.getArticleId()%>" method="post" name="commentForm">
								<input type="hidden" name="commentId" class="replayCommentId" value="-1">
									<div class="commentform">
										<div class="comment_title">发表评论</div>
										<ul>
											<li class="left">用户名:</li>
											<li class="right"><%=suo.getUserName() %></li>
										</ul>
										<ul>
											<li class="left">评论内容:</li>
											<li class="right">
											<textarea style="width: 300px; height: 150px;" id="comment_content"
													name="articleComment.comment"></textarea></li>
										</ul>
										<ul>
											<input class="comment_btn" type="submit" value="提交">
										</ul>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
		
		<!-- footer -->
			 <footer>
				<div class="inside">
				<a rel="nofollow" href="#" class="new_window">邮箱</a>mc90716@163.com<br /> <a
					href="#" class="new_window">联系方式</a>15169170743
			</div>
			</footer> 
</body>
</html>