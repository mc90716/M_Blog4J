<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>


<%
request.setCharacterEncoding("UTF-8");

String path = request.getContextPath();
request.setAttribute("path",path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body bgcolor="#F7F8F9">



<center>
<form action="intro!editIntro.action" method="post">
<br>
<h2 align="center">实验中心简介</h2>
<br><br>
<table width="900" height="400" border="0" >
<tr>
  <td height="350" colspan="6"><div align="center">
<textarea cols="80" id="context" name="context" rows="10" ><%= %></textarea>  

		
	</div></td> 
	
</tr>
<tr >
    <td  align="center"><input type="submit" value="修改简介" > </td>
</tr>
</table>
</form>
</center>
  </body>
</html>

