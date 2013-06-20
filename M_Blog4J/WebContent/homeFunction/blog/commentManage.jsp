<%@ page language="java" import="java.util.*,com.blog.entity.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
ArrayList<ArticleComment> comments = (ArrayList<ArticleComment>)request.getAttribute("comments");
HashMap<String,ArrayList<ArticleComment>> myArticleComments = (HashMap<String,ArrayList<ArticleComment>>)request.getAttribute("myArticleComments");
%>

<div class="h_status">
<a class="active" href="/feedback/out">评论信息</a>
</div>
<script type="text/javascript">setSubTab();</script>
<table id="lstBox" cellspacing="0">
<thead>
<tr><th class="tdleft" style="width:426px;">标题</th><th style="width:140px;">作者</th><th style="width:140px;">时间</th><th style="width:100px;">操作</th></tr>
</thead>
<tbody>
<% for(ArticleComment ac : comments) {%>
<tr class="altitem"><td class="tdleft">RE: <a href="articleAction!viewArticle.action?id=<%=ac.getArticle().getArticleId() %>" target="_blank"><%= ac.getArticle().getTitle() %> </a></td><td><a href="userAction!viewOtherSpace.action?userId=<%=ac.getUser().getUserId() %>&currentPage=1" class="user_name" target="_blank"><%= ac.getUser().getUserName() %></a></td><td><%= ac.getCommentTime() %></td><td><a class="del" href="articleCommentAction!deleteComment.action?commentId=<%=ac.getCommentId()%>">删除</a></td></tr><tr><td colspan="4"><div class="recon"><%= ac.getComment() %></div></td></tr>
<%} %>

<% for(String key : myArticleComments.keySet()) {
  ArrayList<ArticleComment> acList = myArticleComments.get(key);
    for(ArticleComment ac : acList){%>
    	<tr class="altitem"><td class="tdleft">RE: <a href="articleAction!viewArticle.action?id=<%=ac.getArticle().getArticleId() %>" target="_blank"><%= ac.getArticle().getTitle() %> </a></td><td><a href="userAction!viewOtherSpace.action?userId=<%=ac.getUser().getUserId() %>&currentPage=1" class="user_name" target="_blank"><%= ac.getUser().getUserName() %></a></td><td><%= ac.getCommentTime() %></td><td><a class="del" href="articleCommentAction!deleteComment.action?commentId=<%=ac.getCommentId()%>">删除</a></td></tr><tr><td colspan="4"><div class="recon"><%= ac.getComment() %></div></td></tr>
    	<%}}%>

</tbody>
</table>
<!-- 
<div class="page_nav"><span> 3条数据  共1页</span><strong>1</strong> </div>

<p class="note" style="width:600px;">提示：前台文章评论有几分钟缓存，删除之后不会立即生效。（你可以在前台直接操作评论）</p> -->

