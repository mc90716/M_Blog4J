<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath %>">
		
		<title>M_Blog4J后台管理</title>
		<meta http-equiv=Content-Type content=text/html;charset=UTF-8>
	</head>

  </script>

	<frameset rows="64,*" frameborder="NO" border="0" framespacing="0">
		<frame src="<%=request.getContextPath() %>/background/admin_top.jsp" noresize="noresize" frameborder="0"
			name="topFrame" scrolling="no" marginwidth="0" marginheight="0"
			target="main" />
		<frameset cols="200,*" rows="560,*" id="frame">
			<frame src="<%=request.getContextPath() %>/background/left.jsp" name="leftFrame" noresize="noresize"
				marginwidth="0" marginheight="0" frameborder="0" scrolling="no"
				target="main" />
			<frame src="<%=request.getContextPath() %>/background/right.jsp" name="main" marginwidth="0" marginheight="0"
				frameborder="0" scrolling="auto" target="_self" />
		</frameset>
	</frameset>
	<noframes>

		<body></body>
	</noframes>
</html>
