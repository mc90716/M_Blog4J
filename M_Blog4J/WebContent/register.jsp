<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>M_Blog4J</title>
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="css/miaov_style.css" type="text/css" />

<link rel="stylesheet" href="css/log.css" type="text/css" media="all">
<script language="javascript" type="text/javascript">
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("login").style.display = "block";
	}
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("login").style.display = 'none';
	}
	
	
	function refresh(){
		alert("aaaaaaa");
	document.getElementById('img_vcode').src="userAction!createRandomImg.action";
	}
</script>

<script type="text/javascript" src="js/maxheight.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/miaov.js"></script>
<script type="text/javascript" src="js/log.js"></script>
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
<body id="page1">
	<div id="bg"></div>

	<div id="login">
		<div class="close">
			<img src="images/close.jpg" onclick="hidediv();" />
		</div>
		<form name="logform" action="#">
			<table cellspacing="8">
				<tbody>
					<tr>
						<td width="70"><font style="font-weight: bold;">帐 号：</font></td>
						<td><input id="u" name="userName" class="inputbox" maxlength="50"
							placeholder="邮箱/用户名" type="text"></td>
					</tr>
					<tr>
						<td><font style="font-weight: bold;">密 &nbsp&nbsp 码：</font></td>
						<td><input id="p" name="passWd" class="inputbox" maxlength="50" 
							type="password"></td>
					</tr>
					<tr>
						<td></td>
						<!-- <td><input id="chkRemember" name="chkRemember"
							style="vertical-align: middle" class="checkbox" type="checkbox"><label
							for="chkRemember" class="font_red">记住我一周</label></td> -->
					</tr>
					<tr>
						<td></td>
						<td><a id="aLogin" class="btn_login"
							href="javascript:void(0);" onclick="login();"><span>登 录</span></a><a
							class="register_a" href="register.jsp"><span class="register">立即注册</span></a>
							<!-- <a href="https://passport.csdn.net/account/forgotpassword" class="font_gray" target="_top">忘记密码</a>
		    <input type="button" onclick="hidediv();" value="关闭"/>
		     --></td>
					</tr>
					<tr>
						<td></td>
						<td><span id="sp_err" style="color: #ff0000;">&nbsp;</span></td>
					</tr>
					<tr id="tr_third" style="display: none;">
						<td><b style="font-size: 12px; color: #777;">第三方登录</b></td>
						<td id="td_third"></td>
					</tr>
				</tbody>
			</table>
		</form>

	</div>

	<div class="tail-bottom">
		<div id="main">
			<!-- header -->
			<header>
			<div class="nav-box">
				<nav>
				<ul class="fright">
					<li class="current"><a href="javascript:void(0);" onclick="showdiv();"><img
							src="images/pic-home-act.gif" title="空间">我的空间</a></li>
					<li class="current"><a href="javascript:void(0);"
						onClick="showdiv();"><img src="images/pic-mail.gif">登录</a></li>
					<li class="current"><a href="register.jsp"><img
							src="images/pic-sitemap.gif" title="注册">注册</a></li>
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

			<div class="content_login">
				<div class="top_bg"></div>
				<form action="userAction!regUser" id="form_register">
					<div class="login_cont01">
						<h5></h5>
						<div class="table">

							<table width="100%" border="0">
								<tbody>
									<tr>
										<td width="150" valign="top" class="right"><dfn>*</dfn>用户名：</td>
										<td>
											<div class="oneline">
												<input type="text" maxlength="100" class="inputbox" id="em_name"
													name="user.userName" onblur="checkUserName()"><span id="check_result_name"></span>
											</div>
											<div class="twoline">（用户名在全站内是唯一的）</div>

										</td>
									</tr>
									<tr>
										<td width="150" valign="top" class="right"><dfn>*</dfn>E-mail：</td>
										<td>
											<div class="oneline">
												<input type="text" maxlength="100" class="inputbox" id="em_email"
													name="user.email" ><span id="check_result_email"></span>
											</div>
											<div class="twoline">
												（有效的E-mail地址才能收到激活码，帐户在激活后才能享受网站服务。）</div>

										</td>
									</tr>
									<tr>
										<td valign="top" class="right"><dfn>*</dfn>密码：</td>
										<td>
											<div class="oneline">
												<input type="password" maxlength="50" class="inputbox"
													id="p1" name="user.passWd" onblur="checkPasswd()"><span id="check_result_passwd1"></span>
												<ul style="display: none;" id="pwd-strong">
													<li>弱</li>
													<li>中</li>
													<li>强</li>
												</ul>
											</div>
											<div class="clear"></div>
											<div class="twoline">
												（为了您帐户的安全，建议使用字符+数字等多种不同类型的组合，且长度大于5位。）</div>

										</td>
									</tr>
									<tr>
										<td valign="top" class="right"><dfn>*</dfn>再次输入密码：</td>
										<td>
											<div class="oneline">
												<input type="password"  maxlength="50" class="inputbox"
													id="p2"><span id="check_result_passwd2"></span>
											</div>
											<div class="twoline">（确保您记住密码。）</div>

										</td>
									</tr>
									<tr>
										<td valign="top" class="right"><dfn>*</dfn>校验码：</td>
										<td>
											<div class="oneline">
												<input type="text" maxlength="10" class="inputbox" id="cd" name="vcode"><span id="check_result_cd"></span>
											</div>
											<div style="clear: both;"></div>
											<div style="color: Red; font-size: 12px;"></div>
										</td>
									</tr>
									
		<!-- 							
									<a onclick="javascript:refresh();">qqqqqqqqqqqqqqqqqqqqqqqqqqqqq</a>
									
									 <tr height="16px;">
			<td align="right"  height="25px">验证码：</td>
			<td><input class="inp2" type="text"  id="vcode"  name="vcode" />
			&nbsp;<a href="#"><img id="img_vcode" title="看不清？点击换一张。" onclick="javascript:refresh();" border="0" align="middle" onclick="refresh()" src="Vcode"/></a>
                              </td>
		
		  </tr>
					 -->				

									
									<tr style="" id="tr_vc">
										<td valign="top" class="right"></td>
										<td><img style="vertical-align: middle"
											src="userAction!createRandomImg" id="vcImg">
											 <a class="font_gray" href="javascript:createRandomImg();" id="aRecode">看不清，换一张</a></td>
									</tr>

								</tbody>
							</table>
						</div>
						<a href="javascript:void(0);" class="btn_register" id="btn_register" onclick="submitRegForm()"><span>注册</span></a>
				</form>
				<div class="btm_bg"></div>
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






