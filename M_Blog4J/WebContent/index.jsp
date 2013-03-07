<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		
	});
</script>

</head>
<body>
	<form action="user/userAction!regUser" method="post">
	<table border="1">
	  <tr><td>用户名</td><td><input type="text" name="user.userName"></td></tr>
	  <tr><td>密码</td><td><input type="password" name="user.passwd"></td></tr>
	  <tr><td>确认密码</td><td><input type="password" name="passwd2"></td></tr>
	  <tr><td>邮箱</td><td><input type="text" name="user.email"></td></tr>
	  <tr><td>昵称</td><td><input type="text" name="user.displayName"></td></tr>
	  <tr><td>个性签名</td><td><input type="text" name="user.signature"></td></tr>
	  <tr><td><input type="submit" value="提交"></td><td><input type="button" value="取消"></td></tr>
	</table>
</form>
</body>
</html>