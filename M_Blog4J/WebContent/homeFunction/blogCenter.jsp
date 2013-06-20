<%@ page language="java" import="com.blog.*,com.blog.bean.*,com.blog.entity.*,java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject) session
			.getAttribute("UserBean");
	
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
<link rel="stylesheet" href="css/main.css" type="text/css" media="all">
<link rel="stylesheet"  href="css/miaov_style.css" type="text/css" />

<script type="text/javascript" charset="utf-8" src="ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8"src="ueditor/editor_all.js"></script>
<link rel="stylesheet" type="text/css" href="ueditor/themes/default/css/ueditor.css" />
	

<script type="text/javascript" src="ueditor/_src/plugins/image.js"></script>
<script type="text/javascript" src="js/maxheight.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/miaov.js"></script>
<script type="text/javascript" src="js/blog.js"></script>


<link rel="stylesheet" href="pagination/pagination.css" />
<script type="text/javascript" src="pagination/jquery.min.js"></script>
<script type="text/javascript" src="pagination/jquery.pagination.js"></script>


        <!-- Load data to paginate -->
        <script type="text/javascript" src="pagination/members.js"></script>

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
								<a href="javascript:void(0);" target="_blank"><img src="images/photo.jpg"
									style="border: 1px solid #DDDDDD; margin: 10px;"></a><br>
								<span><%=suo.getUserName() %></span>
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
				<div class="blogmain">

					<div class="tabs_header">
						<ul id="ul_tab" class="tabs">
							<li class="active" onclick="tabClick(this);"
								id="li_articleManage"><a href="javascript:void(0);"><span>文章管理</span></a></li>
							<li onclick="tabClick(this);" id="li_publishManage"><a
								href="javascript:void(0);"><span>发表文章</span></a></li>
							<li onclick="tabClick(this);" id="li_columnManage"><a
								href="javascript:void(0);"><span>开辟专栏</span></a></li>
							<li onclick="tabClick(this);" id="li_commentManage"><a
								href="javascript:void(0);"><span>评论管理</span></a></li>
							<li onclick="tabClick(this);" id="li_recycleBin"><a
								href="javascript:void(0);"><span>回收站</span></a></li>
						</ul>
					</div>

					<div style="display: block" id="articleManage"><jsp:include
							page="blog/articleManage.jsp"></jsp:include></div>
					<div style="display: none;" id="publishManage"><jsp:include
							page="blog/publishManage.jsp"></jsp:include></div>
					<div style="display: none;" id="columnManage"><jsp:include
							page="blog/columnManage.jsp"></jsp:include></div>
					<div style="display: none;" id="commentManage"><jsp:include
							page="blog/commentManage.jsp"></jsp:include></div>
					<div style="display: none;" id="recycleBin"><jsp:include
							page="blog/recycleBin.jsp"></jsp:include></div>

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

	</div>
</body>
</html>