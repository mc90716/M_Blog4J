<%@ page language="java"
	import="com.blog.*,java.util.*,com.blog.formbean.*,com.blog.entity.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject) session
			.getAttribute("UserBean");

	ArrayList<FriendNewState> friendNewStates = new ArrayList<FriendNewState>();
	friendNewStates = (ArrayList<FriendNewState>) request.getAttribute("friendsNewState");
	ArrayList<User> myFriends = (ArrayList<User>)request.getAttribute("myFriends");
	
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
<link rel="stylesheet" type="text/css" href="css/miaov_style.css" />

<script type="text/javascript" src="js/maxheight.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/miaov.js"></script>
<script type="text/javascript" src="pagination/jquery.pagination.js"></script>

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
							<li class="current"><a href="javascript:void(0);"><%=suo.getUserName()%></a></li>
							<li class="current"><a href="userAction!logoutUser.action">注销</a></li>
						</ul>
						<ul>
							<li class="current"><a
								href="blogAction.action?currentPage=1">首页</a></li>
							<li class="current"><a
								href="hotArticleAction!showHotArticle.action">热门文章</a></li>
							<!-- <li class="current"><a href="index.jsp">博客专家</a></li> -->
							<li class="current"><a
								href="columnAction.action?currentPage=1">博客专栏</a></li>
						</ul>
					</nav>
				</div>
				<h1>
					<a class="log" href="index.html">logo</a>
				</h1>
				<form action="" id="search-form">
					<fieldset>
						<input type="text"> <a href="#"
							onclick="document.getElementById('search-form').submit()"><img
							src="images/button-search.gif"></a>
					</fieldset>
				</form>
			</header>
			<div class="wrapper indent">
				<!-- content -->
				<div class="side">
					<div class="head_id">
						<dl>
							<dt>
								<a href="javascript:void(0);"><img
									src="images/photo.jpg" height="100px"></a>
							</dt>
							<dd>
								<a href="javascript:void(0);" class="u_name"><%=suo.getUserName()%></a>
							</dd>
						</dl>
					</div>
					<div class="menu">
						<ul>
							<li><a
								href="articleAction!viewAllArticle.action?currentPage=1"
								class="article">文章</a></li>
							<!-- <li><a href="homeFunction/albumCenter.jsp" class="album">相册</a></li> -->
							<li><a
								href="favouriteAction!showAllFavourites.action"
								class="favourite">收藏</a></li>
							<li><a href="relationshipAction" class="relationship">关系</a></li>
							<li><a href="messageAction!viewAllMessage.action"
								class="leaveword">留言</a></li>
						</ul>
					</div>
				</div>


				
				<div class="homecenter">
					<div class="hometop"></div>
					<div class="hometab">
						<ul>
							<li class="select"><span>最新动态</span></li>
						</ul>
					</div>

				<% for(FriendNewState fns : friendNewStates) {
				%>
					<div class="dynamic">
						<dl class="trend">
						<%if(fns.getType() == "article") {%>
							<dt>
								<a href="userAction!viewOtherSpace.action?userId=<%=fns.getUserId() %>&currentPage=1" target="_blank"><%= fns.getUserName() %></a>于<%=fns.getDate() %>发表了文章:
							</dt>
							<dd><a href='articleAction!viewArticle.action?id=<%=fns.getStateId() %>' target='_blank'><%=fns.getNewState() %></a></dd>
							<%} %>
							<%if(fns.getType() == "comment") {%>
							<dt>
								<a href="userAction!viewOtherSpace.action?userId=<%=fns.getUserId() %>&currentPage=1" target="_blank"><%= fns.getUserName() %></a>于<%=fns.getDate() %>发表了评论:
							</dt>
							<dd><a href='articleAction!viewArticle.action?id=<%=fns.getStateId() %>' target='_blank'><%=fns.getNewState() %></a></dd>
							<%} %>
							<%if(fns.getType() == "column") {%>
							<dt>
								<a href="userAction!viewOtherSpace.action?userId=<%=fns.getUserId() %>&currentPage=1" target="_blank"><%= fns.getUserName() %></a>于<%=fns.getDate() %>开辟的专栏:
							</dt>
							<dd><a href="columnAction!viewColumnBlog.action?columnId=<%= fns.getStateId()%>" target='_blank'><%=fns.getNewState() %></a></dd>
							<%} %>
						</dl>
					</div>
				<%} %>
					

					
				</div>
				
				
					<aside>
						<div class="homeright">
							<h3>
								我的关注<a href="#" class="nextfocus"></a>
							</h3>

							<div class="myfocus">
								<ul>
								<% for(User user : myFriends) {%>
									<li><a href="userAction!viewOtherSpace.action?userId=<%=user.getUserId() %>&currentPage=1"><img src="images/lion.jpg"></a><span><a
											href="userAction!viewOtherSpace.action?userId=<%=user.getUserId() %>&currentPage=1"><%=user.getUserName() %></a></span></li>
								<%} %>
								</ul>
							</div>

							<div class="interestedexpert">
								<ul>
								
								</ul>
							</div>

						</div>
					</aside>

				</div>
				<!-- footer -->
				<footer>
					<div class="inside">
				<a rel="nofollow" href="#" class="new_window">邮箱</a>mc90716@163.com<br /> <a
					href="#" class="new_window">联系方式</a>15169170743
			</div>
				</footer>
			</div>
		</div>
</body>
</html>