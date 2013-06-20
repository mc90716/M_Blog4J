<%@page import="java.util.List"%>
<%@page import="com.blog.bean.*,com.blog.entity.*"%>
<%
	
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	PageView<Article> pageView = (PageView<Article>)request.getAttribute("pageView");
	List<Article> records = pageView.getRecords();
%>

<!-- <div id="sel_div" class="h_status">
	类别：<select id="selCat"><option selected="selected" value="0">全部&nbsp;&nbsp;&nbsp;&nbsp;</option></select>
	类型：<select id="selType"><option selected="selected"
			value="all">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
		<option value="original">原创</option>
		<option value="repost">转载</option>
		<option value="translated">翻译</option></select>&nbsp;
</div> -->
<div>

<table id="lstBox" cellspacing="0">
	<tbody class="showArticleList">
		

		
	</tbody>

</table>

<div id="Pagination" class="pagination"></div>

</div>
