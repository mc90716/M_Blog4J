<%@ page language="java"
	import="com.blog.*,com.blog.bean.*,com.blog.entity.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUserObject suo = (SessionUserObject) session
			.getAttribute("UserBean");
	
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
<link rel="stylesheet" href="css/miaov_style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/blog.css" />

<script type="text/javascript" charset="utf-8"
	src="ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/editor_all.js"></script>
<link rel="stylesheet" type="text/css"
	href="ueditor/themes/default/css/ueditor.css" />


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
<body id="page1" onload="javascript:init();">

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
								<a href="javascript:void(0);" target="_blank"><img
									src="images/photo.jpg"
									style="border: 1px solid #DDDDDD; margin: 10px;"></a><br>
								<span><%=suo.getUserName()%></span>
								<div style="border: 1px dashed #CCCCCC; margin-top: 10px;"></div>
								<ul class="blog_statistics">
									<li>原创:<span>2篇</span></li>
									<li>转载:<span>0篇</span></li>
									<li>译文:<span>0篇</span></li>
									<li>评论:<span>0篇</span></li>
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
				<!-- 	<div class="panel_list">
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
								<li><a href="homeFunction/article.jsp">文章标题</a><span>(5)</span></li>
								<li><a href="homeFunction/article.jsp">文章标题</a><span>(5)</span></li>
							</div>
						</ul>
					</div>
					<div class="panel_support">推荐文章</div>
				</div>
				<div class="blogmain">

					<div class="tabs_header">
						<ul id="ul_tab" class="tabs">
							<li class="active" id="li_articleManage"><a href="javascript:void(0);"><span>添加专栏文章</span></a></li>
						</ul>
					</div>


					<div style="display: block;" id="publishManage">
						<form action="blogColumnAction!addArticleToCloumn.action?columnId=<%=request.getAttribute("columnId") %>"
							method="post">
							<div id="sel_div" class="h_status">
								<!-- <span style="float: left; margin-top: 5px; margin-right: 10px;">类型</span>
								<select id="selType" name="article.articleType">
									<option value="original">原创</option>
									<option value="repost">转载</option>
									<option value="translated">翻译</option> -->
								</select>&nbsp;

								<h3 class="blog_head">文章标题</h3>

								<input type="text" name="article.title" class="input_title" value="">
								
							</div>
							<div style="width: 300px;">
								<textarea name="article.content" id="myEditor"
									style="width: 565px;">输入文章内容</textarea>
							</div>
							<script type="text/javascript">
								UE.getEditor('myEditor');
							</script>
					<!--
							<div>
								<p class="p_category">文章分类（分类到首页）</p>
							</div>

							 <div class="radio_category">
								<input class="radCh11" type="radio" value="1" name="category"><label>移动开发</label>
								<input class="radCh11" type="radio" value="2" name="category"><label>Web前端</label>
								<input class="radCh11" type="radio" value="3" name="category"><label>架构设计</label>
								<input class="radCh11" type="radio" value="4" name="category"><label>编程语言</label>
								<input class="radCh11" type="radio" value="5" name="category"><label>互联网</label>
								<input class="radCh11" type="radio" value="6" name="category"><label>数据库</label>
								<input class="radCh11" type="radio" value="7" name="category"><label>系统运维</label>
								<input class="radCh11" type="radio" value="8" name="category"><label>云计算</label>
								<input class="radCh11" type="radio" value="9" name="category"><label>研发管理</label>
								<input class="radCh11" type="radio" value="10" name="category"><label>综合</label>
							</div> -->


							<div class="btn_area_1">
								<input id="btnPublish" class="input_btn_1" value="发表文章"
									title="保存并跳转" type="submit"> <input id="btnCancel"
									class="input_btn_1" value="舍弃" type="button"> <span
									id="sp_note" class="savenote" style="display: none;"></span>
							</div>

						</form>
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

	</div>
</body>
</html>