<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8"
	src="ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/editor_all.js"></script>
<link rel="stylesheet" type="text/css"
	href="ueditor/themes/default/css/ueditor.css" />
<link rel="stylesheet" type="text/css" href="css/blog.css" />
</head>
<form action="articleAction!publishArticle.action?currentPage=1" method="post">
	<div id="sel_div" class="h_status">
		<span style="float: left; margin-top: 5px; margin-right: 10px;">类型</span>
		<select id="selType" name="article.articleType">
			<option selected="selected" value="original">原创</option>
			<option value="repost">转载</option>
			<option value="translated">翻译</option>
		</select>&nbsp;

		<h3 class="blog_head">文章标题</h3>

		<input type="text" name="article.title" class="input_title">
	</div>
     <div style="width: 300px;">
		<textarea name="article.content" id="myEditor" style="width: 565px;">这里写你的初始化内容</textarea>
	</div>
	<script type="text/javascript">
		UE.getEditor('myEditor');
	</script>

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
	</div>


	<div class="btn_area_1">
		<input id="btnPublish" class="input_btn_1" value="发表文章" title="保存并跳转"
			type="submit"> <input id="btnCancel" class="input_btn_1"
			value="舍弃" type="button"> <span id="sp_note" class="savenote"
			style="display: none;"></span>
	</div>

</form>

