<%@ page language="java" import="com.blog.*,java.util.*,com.blog.entity.*,com.blog.bean.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject)session.getAttribute("UserBean");
	
	HashMap<String, ArrayList<Article>> hotArticleHashMap = new HashMap<String, ArrayList<Article>>();
	
	hotArticleHashMap = (HashMap<String, ArrayList<Article>>)request.getAttribute("hotArticleHashMap");
	
	HashMap<String,Long> articleCount = (HashMap<String,Long>)request.getAttribute("articleCount");
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
<script type="text/javascript" src="js/log.js"></script>
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
							<!-- <li class="current"><a href="index.html">SiteMap</a></li> -->
						</ul>
					</nav>
				</div>

			</header>

			<div class="categories">
				<div class="category1 tab1">
					<div class="photo">
						<img src="images/yidongkaifa.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=1">移动开发</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% ArrayList<Article> articles = hotArticleHashMap.get("移动开发"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("移动开发") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=1" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>

				<div class="category1 tab2">
					<div class="photo">
						<img src="images/webqianduan.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=2">Web前端</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("Web前端"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("Web前端") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=2" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>


				<div class="category1 tab3">
					<div class="photo">
						<img src="images/jiagousheji.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=3">架构设计</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("架构设计"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("架构设计") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=3" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>


				<div class="category1 tab4">
					<div class="photo">
						<img src="images/bianchengyuyan.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=4">编程语言</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("编程语言"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("编程语言") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=4" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>


				<div class="category1 tab5">
					<div class="photo">
						<img src="images/hulianwang.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=5">互联网</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("互联网"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("互联网") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=5" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>


				<div class="category1 tab6">
					<div class="photo">
						<img src="images/shujuku.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=6">数据库</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("数据库"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("数据库") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=6" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>

				<div class="category1 tab7">
					<div class="photo">
						<img src="images/xitongyunwei.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=7">系统运维</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("系统运维"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("系统运维") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=7" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>

				<div class="category1 tab8">
					<div class="photo">
						<img src="images/yunjisuan.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=8">云计算</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("云计算"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("云计算") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=8" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>
				
				<div class="category1 tab2">
					<div class="photo">
						<img src="images/yanfaguanli.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=9">研发管理</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("研发管理"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("研发管理") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=9" class="viewall1">查看全部文章</a>
						</h4>
					</div>
				</div>
				
				<div class="category1 tab5">
					<div class="photo">
						<img src="images/Java.jpg">
					</div>
					<h2 class="ctgtitle">
						<a href="blogAction!showCategoryArticle.action?categoryId=10">综合</a>
					</h2>
					<div class="info">
						<h4>最热门的文章</h4>
						<ul>
						<% articles = hotArticleHashMap.get("综合"); 
						   for(Article article : articles){%>
							<li><a href="articleAction!viewArticle.action?id=<%= article.getArticleId()%>" target="_blank"><%= article.getTitle() %></a></li>
							<%} %>
						</ul>
						<h4 class="sumblog">
							共计<%=articleCount.get("综合") %>篇<br> <a href="blogAction!showCategoryArticle.action?categoryId=10" class="viewall1">查看全部文章</a>
						</h4>
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
	</div>
</body>
</html>