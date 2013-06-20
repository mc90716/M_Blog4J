<%@ page language="java" import="java.util.*,com.blog.entity.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% 
ArrayList<BlogColumn> blogColumns = (ArrayList<BlogColumn>)request.getAttribute("blogColumn");
%>

<table id="lstBox" cellspacing="0">
	<thead>
		<tr>
			<th class="tdleft">专栏</th>
			<th style="width: 120px;">文章</th>
			<th style="width: 100px;">操作</th>
			<th style="width: 100px;">写文章</th>
		</tr>
	</thead>
	<tbody id="lstBody">
	<%for(BlogColumn bc : blogColumns) {%>
		<tr>
			<td class="tdleft"><span><%= bc.getColumnName()%></span></td>
			<td><a
				href="columnAction!viewColumnBlog.action?columnId=<%= bc.getColumnId()%>"
				class="red" target="_blank"><%= bc.getArticle().size() %></a></td>
			<td><a href="blogColumnAction!deleteArticleOfColumn.action?columnId=<%=bc.getColumnId() %>" name="del"
				>删除</a><br>
			</td>
			<td><a href="blogColumnAction!writeArticleToColumn.action?columnId=<%=bc.getColumnId() %>" target="_blank">写文章</a></td>
			<td></td>
		</tr>
		<%} %>
	</tbody>
</table>

<div style="margin-top: 20px;">
	<!-- <input id="txtCat" class="t_input" size="50" maxlength="30" type="text"> -->
	<input id="btnAdd" class="t_btn" value="开辟专栏" type="button" onclick="createColumn();">
</div>



<div class="bor_box">
	<form id="form1"  method="post" class="columnform">
		<table cellpadding="0" cellspacing="4">
			<colgroup>
				<col width="90">
				<col width="auto">
			</colgroup>
			<tbody>
				<tr>
					<th><span class="must">*</span>专栏名称：</th>
					<td><input class="input_1" name="blogColumn.columnName" id="title"
						maxlength="50" type="text"></td>
				</tr>
				<tr>
					<th valign="top"><span class="must">*</span>简介：</th>
					<td><textarea rows="5" cols="45" name="blogColumn.columnIntro" id="desc"></textarea>
						<p class="notic">(最多允许输入140个字符。)</p></td>
				</tr>
			<!-- 	<tr>
					<th valign="top">LOGO：</th>
					<td><input type="file" class="input_1"> 
						<p class="notic">支持 jpg, gif, png, bmp
							格式的图片，不要超过2MB。建议图片尺寸75x75像素。</p>
						<input id="logo" name="logo" type="hidden">
					</td>
				</tr> -->
				<!-- <tr>
					<th><span class="must">*</span>别名：</th>
					<td><input class="input_1" name="alias" id="alias"
						maxlength="20" type="text"> <span class="notic">专栏唯一标识</span></td>
				</tr> -->
				<tr>
					<th><span class="must">*</span>分类：</th>
					<td><select name="category" id="channelId">
							<option value="1">移动开发</option>
							<option value="2">Web前端</option>
							<option value="3">架构设计</option>
							<option value="4">编程语言</option>
							<option value="5">互联网</option>
							<option value="6">数据库</option>
							<option value="7">系统运维</option>
							<option value="8">云计算</option>
							<option value="9">研发管理</option>
							<option value="10">综合</option>
					</select></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td><span class="notic">*以上信息提交后不可修改，请认真填写。</span></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td><input value="提交申请" class="btn_submitColumn" onclick="saveColumn()"
						type="button"><span id="err"></span></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>



