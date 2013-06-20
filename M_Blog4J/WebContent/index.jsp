<%@ page language="java" import="com.blog.*,com.blog.bean.*,java.util.*,com.blog.entity.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject)session.getAttribute("UserBean");
	PageView pageView_articleList = (PageView)request.getAttribute("pageView_articleList");
	List<Article> articles = pageView_articleList.getRecords();
	
	PageView pageView_articleTitle = (PageView)request.getAttribute("pageView_articleTitle");
	List<Article> articleTitle = pageView_articleTitle.getRecords();
	
	PageView pageView_articleComment = (PageView)request.getAttribute("pageView_articleComment");
	List<ArticleComment> articleComments = pageView_articleComment.getRecords();
	
	ArrayList<BlogColumn> blogColumns = new ArrayList<BlogColumn>();
	blogColumns = (ArrayList<BlogColumn>)request.getAttribute("blogColumns");
	
	QueryResult<TagCloud> tagClouds = (QueryResult<TagCloud>) request
			.getAttribute("tagClouds");
	List<TagCloud> tags = tagClouds.getResultList();
%>
<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="css/miaov_style.css" type="text/css" />


<link rel="stylesheet" href="css/fenye.css" type="text/css" media="all">


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
	
	function submitSearch(){
		var value = document.getElementById("searchKeyWord");
		if(value.value == ""){
			alert("请输入关键字");
			return false;
		}else{
			document.getElementById('search-form').submit();
		}
	}
</script>

<script type="text/javascript" src="js/maxheight.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/miaov.js"></script>
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
							<!-- <li class="current"><a href="columnAction.action?currentPage=1">博客专家</a></li> -->
							<li class="current"><a href="columnAction.action?currentPage=1">博客专栏</a></li>
						</ul>
					</nav>
				</div>
				<h1>
					<a class="log" href="index.html">logo</a>
				</h1>
				<form action="blogAction!searchResult.action" id="search-form">
					<fieldset>
						<input type="text" id="searchKeyWord" name="keyWord"> 
						<a href="javascript:void(0);"	onclick="submitSearch();"><img
							src="images/button-search.gif"></a>
					</fieldset>
				</form>
			</header>
			<div class="wrapper indent">
				<!-- content -->
				<section id="content">
					<div id="slogan">

						<div class="side_nav">
							<ul>
								<li class="select"><a href="blogAction.action?currentPage=1" name="all">全部分类</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=1" name="mobile">移动开发</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=2" name="web">Web前端</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=3" name="enterprise">架构设计</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=4" name="code">编程语言</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=5" name="www">互联网</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=6" name="database">数据库</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=7" name="system">系统运维</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=8" name="cloud">云计算</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=9" name="software">研发管理</a></li>
								<li><a href="blogAction!showCategoryArticle.action?categoryId=10" name="other">综合</a></li>
							</ul>
						</div>
						<%-- <div class="blog_cloumn">
							<h1 align="center">博客专栏</h1>
							<% for(BlogColumn bc : blogColumns) {%>
							<div class="series">
								<div class="blog_column"><img src="images/columnimg.jpg" class="seires_img"></div>
								<a href="columnAction!viewColumnBlog.action?columnId=<%= bc.getColumnId()%>"><div class="top1"><%= bc.getColumnName() %></div></a>
								<div class="end1">作者:<%= bc.getUser().getUserName() %></div>
							</div>
							<%} %>
						</div> --%>

					<!-- 	<div class="blog_cloumn">
							<h1 align="center">推荐博主</h1>
							
							
							<div class="series">
								<div class="left_author">
									<img src="images/lion.jpg" class="seires_img">
									<div class="author_name" align="center">博主名</div>
								</div>

								<div class="right_author">
									<img src="images/lion.jpg" class="seires_img">
									<div class="author_name" align="center">博主名</div>
								</div>

							</div>
							
							
						</div> -->
					</div>

				</section>
				<!-- bloglist -->
				<div class="blogcenter">
					<!-- <div class="topblog" align="center">图片</div> -->
					<div>
						<h3 class="bloglisthead">博客列表</h3>
					</div>


					<%for(Article article : articles) {%>
					<div class="bloglist">
						<h1>
							<a class="category">【<%=article.getCategory().getCategoryName() %>】</a><a class="blogtitle"
								target="_blank" href="articleAction!viewArticle.action?id=<%=article.getArticleId() %>"><%=article.getTitle() %></a>
						</h1>
						<dl>  
							<dt>
								<a href="#"><img src="images/myphoto.jpg"></a>
							</dt>
							<%if(article.getContent().length() > 120) {%>
							<dd><%=article.getContent().substring(0,120) + "..." %></dd>
							<%}else{ %>
							<dd><%=article.getContent()%></dd>
							<%} %>
							
						</dl>
						<div class="aboutinfo">
							<span class="span_left">
							 <a href="userAction!viewOtherSpace.action?userId=<%=article.getUser().getUserId() %>&currentPage=1" target="_blank" class="username"><%=article.getUser().getUserName() %></a> <span><%=article.getCreateTime() %></span> 
								<a href='articleAction!viewArticle.action?id=<%=article.getArticleId() %>' target='_blank' class="view">阅读(<%=article.getVisitorsCount() %>)</a>
								 <a href="articleAction!viewArticle.action?id=<%=article.getArticleId() %>" target='_blank' class="comment">评论(<%=article.getArticleComments().size() %>)</a>
							</span> <span class="span_right"> 
							<!-- <a href="#" class="up">顶<span>(12)</span></a> <a href="#" class="trample">踩<span>(0)</span></a> -->
							</span>
						</div>
					</div>

					<%} %>
					
					
					
					
					
<div class="page_nav">
<span> <%=pageView_articleList.getTotalrecord() %>条数据  共<%=pageView_articleList.getTotalpage() %>页</span>
<a href="blogAction.action">首页</a> 
<% if(pageView_articleList.getCurrentpage() != 1) {%>
<a href="blogAction.action?currentPage=<%=pageView_articleList.getCurrentpage()-1%>">上一页</a>
<%} %>
<% for(int i=pageView_articleList.getCurrentpage(); i<=pageView_articleList.getTotalpage();i++) {%>
<a href="blogAction.action?currentPage=<%=i%>"><%=i %></a> 
<%} %>
<a href="blogAction.action?currentPage=<%=pageView_articleList.getCurrentpage()+1%>">下一页</a> 
<a href="blogAction.action?currentPage=<%=pageView_articleList.getTotalpage()%>">尾页</a> 
</div>					
					
					
					
					
					</div>


				<!-- aside -->
				<aside>
					<div id="lable">
						<div id="div1">
						     <% for(int i=0; i<tags.size(); i++) {%>
							   <a href="blogAction!searchResult.action?keyWord=<%=tags.get(i).getTagName()%>" class="red"><%= tags.get(i).getTagName() %></a>
							 <%} %>
							<!--  <a href="#" class="red">教程</a> 
							 <a href="#" class="green">试听</a> 
							 <a href="#" class="green">精品</a> 
							 <a href="#" class="blue">妙味课堂</a>
							 <a href="#" class="green">SEO</a>
							 <a href="#" class="red">特效</a>
							 <a href="#" class="blue">miaov</a>
							 <a href="#" class="red">CSS</a> 
							 <a href="#" class="green">求职</a> -->
						</div>
						<div align="center" style="">
							<h3>标签云</h3>
						</div>
					</div>
					<div class="hot_article">

						<h2>热门文章推荐</h2>
						<ul class="article_services">
						<%for(Article a : articleTitle) {%>
							<li><a href='articleAction!viewArticle.action?id=<%=a.getArticleId() %>' target='_blank'><%=a.getTitle() %></a></li>
							<%} %>
						</ul>
					</div>


					 <div class="new_comment">

						<h2>最新评论</h2>
						<ul class="comment_services">
						<%for(ArticleComment comment : articleComments) {%>
							<li><a href='articleAction!viewArticle.action?id=<%=comment.getArticle().getArticleId() %>' target='_blank' class="comment_articletitle"><%=comment.getArticle().getTitle() %></a>
								<p class="comment_p">
									<span><a href='articleAction!viewArticle.action?id=<%=comment.getArticle().getArticleId() %>' target='_blank' class="comment_person"><%=comment.getUser().getUserName() %></a><font
										color="#000000" size="+5">： </font></span><%=comment.getComment() %>
								</p></li>
							<%} %>
						</ul>
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