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

<script language="javascript" type="text/javascript">
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("login").style.display = "block";
	}
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("login").style.display = 'none';
	}
</script>

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

	<div id="login">
		<div class="close">
			<img src="images/close.jpg" onclick="hidediv();" />
		</div>
		<form name="logform" action="#">
			<table cellspacing="8">
				<tbody>
					<tr>
						<td width="70"><font style="font-weight: bold;">帐 号：</font></td>
						<td><input id="u" name="u" class="inputbox" maxlength="50"
							placeholder="邮箱/用户名" type="text"></td>
					</tr>
					<tr>
						<td><font style="font-weight: bold;">密 &nbsp&nbsp 码：</font></td>
						<td><input id="p" class="inputbox" maxlength="50"
							type="password"></td>
					</tr>
					<tr>
						<td></td>
						<!-- <td><input id="chkRemember" name="chkRemember"
							style="vertical-align: middle" class="checkbox" type="checkbox"><label
							for="chkRemember" class="font_red">记住我一周</label></td> -->
					</tr>
					<tr>
						<td></td>
						<td><a id="aLogin" class="btn_login"
							href="javascript:void(0);" onclick="login();"><span>登 录</span></a><a
							class="register_a" href="register.jsp"><span class="register">立即注册</span></a>
							<!-- <a href="https://passport.csdn.net/account/forgotpassword" class="font_gray" target="_top">忘记密码</a>
		    <input type="button" onclick="hidediv();" value="关闭"/>
		     --></td>
					</tr>
					<tr>
						<td></td>
						<td><span id="sp_err" style="color: #ff0000;">&nbsp;</span></td>
					</tr>
					<tr id="tr_third" style="display: none;">
						<td><b style="font-size: 12px; color: #777;">第三方登录</b></td>
						<td id="td_third"></td>
					</tr>
				</tbody>
			</table>
		</form>

	</div>


	<div class="tail-bottom">
		<div id="main">
			<!-- header -->
			<header>
				<div class="nav-box">
					<nav>
						<ul class="fright">
							<%if(suo == null) {%>
							<li class="current"><a href="javascript:void(0);" onclick="showdiv();"><img
									src="images/pic-home-act.gif" title="空间">我的空间</a></li>
							<li class="current"><a href="javascript:void(0);"
								onClick="showdiv();"><img src="images/pic-mail.gif">登录</a></li>
							<li class="current"><a href="register.jsp"> <img
									src="images/pic-sitemap.gif" title="注册">注册</a></li>
							<%}  else{%>
							<li class="current"><a href="blogAction!showHomeCenter.action"><img
									src="images/pic-home-act.gif" title="空间">我的空间</a></li>
							<li class="current">
							<a href="javascript:void(0);"><%=suo.getUserName() %></a></li>
							
							<li class="current">
							<a href="userAction!logoutUser.action">注销</a></li>
							<%} %>
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
				</div>
				<div class="blogcontentmain">
					<!-- <div class="blog_top"></div> -->
					<div class="blog_list">



						<div class="blog_content">
							<div class="blog_title">
								<img src="images/ico_Original.gif" class="blog_icon">
								<h3 class="blogtitle_h3">
									<a href="articleAction!viewArticle.action?id=<%=article.getArticleId() %>"><%=article.getTitle() %> </a>
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
									<% if((suo != null)&&(suo.getUserId() != article.getUser().getUserId())) {%>
									 <span class="blogmanage_edit">
									<input type="hidden" class="articleId" value=<%=article.getArticleId() %>>
									 <a href="javascript:favourite();">收藏</a></span>
									 <%} %>
									<span class="blogmanage_edit">
									<!-- <a href="javascript:alert('edit the article');">编辑</a></span> <span
									class="blogmanage_del"><a
									href="javascript:alert('delete the article');">删除</a> -->
									</span>
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
							
								

							<%if(suo != null){%>

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
							<%} %>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- footer -->
			
		</div>
		 <footer>
				<div class="inside">
				<a rel="nofollow" href="#" class="new_window">邮箱</a>mc90716@163.com<br /> <a
					href="#" class="new_window">联系方式</a>15169170743
			</div>
			</footer> 
</body>
</html>