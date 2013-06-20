<%@ page language="java" import="java.util.*,com.blog.entity.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	HashMap<Integer,Article> recycleMap = new HashMap<Integer,Article>();
	recycleMap = (HashMap<Integer,Article>)request.getAttribute("recycleMap");
%>

<div style="display: none;" id="sel_div" class="h_status">
	类别：<select id="selCat"><option value="0">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
		<option value="1421866">aaa (0)</option></select> 类型：<select id="selType"><option
			value="all">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
		<option value="original">原创</option>
		<option value="repost">转载</option>
		<option value="translated">翻译</option></select>&nbsp;
</div>
<table id="lstBox" cellspacing="0">
	<tbody>
		<tr class="">
			<th class="tdleft">标题</th>
			<th style="width: 50px;">阅读</th>
			<th style="width: 50px;">评论</th>
			<th style="width: 70px;"></th>
			<th style="width: 140px;">操作</th>
		</tr>
		
		<% for(Integer key : recycleMap.keySet()) {%>
		<tr class="">
			<td class="tdleft"><a
				href="articleAction!viewArticle.action?id=<%=recycleMap.get(key).getArticleId() %>"
				target="_blank"><%= recycleMap.get(key).getTitle() %></a><span
				class="gray">（<%= recycleMap.get(key).getCreateTime() %>）</span></td>
			<td><%= recycleMap.get(key).getVisitorsCount() %></td>
			<td><%= recycleMap.get(key).getArticleComments().size() %></td>
			<td></td>
			<td><a href="articleAction!shiftDelArticle.action?articleId=<%=key %>" name="del">【删除】</a></td>
			<td><a href="articleAction!cancelDelArticle.action?articleId=<%=key %>" name="del">【恢复】</a></td>
		</tr>
		
		<%} %>
		
	</tbody>
</table>
<div class="page_nav">
	
</div>

