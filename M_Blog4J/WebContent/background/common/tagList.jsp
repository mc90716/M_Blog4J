<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.blog.bean.*,com.blog.entity.*"
	pageEncoding="UTF-8"%>

<%
	QueryResult<TagCloud> tagClouds = (QueryResult<TagCloud>) request
			.getAttribute("tagClouds");
	List<TagCloud> tags = tagClouds.getResultList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#main div {
	position: absolute;
	width: 80px;
	height: 40px;
	border: 1px solid #999;
	text-align: center;
	padding-top: 10px;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/background/js/lovemv.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/background/js/love.js"></script>
</head>
<body>
	<div id="main">
		<form action="tagCloudAction!addTag.action" method="post">
			<input type="text" name="tagCloud.tagName">
			<input type="submit" value="添加">
		</form>
		<%
			for (int i = 0; i < tags.size(); i++) {
		%>
		<div style="left:<%=i * 100%>px;top:50px;background:#fc9;"
			onmousedown="move(this,event)"><%=tags.get(i).getTagName()%></div>
		<%
			}
		%>
	</div>
</body>
</html>