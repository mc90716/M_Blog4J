<link href="images/skin.css" rel="stylesheet" type="text/css" />
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top" background="images/mail_leftbg.gif">
				<img src="images/left-top-right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="images/content-bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" class="left_topbg" id="table2">
					<tr>
						<td height="31">
							<div class="titlebt">
								欢迎界面
							</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="images/mail_rightbg.gif">
				<img src="images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
		<tr>
			<td valign="middle" background="images/mail_leftbg.gif">
				&nbsp;
			</td>
			<td valign="top" bgcolor="#F7F8F9">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td colspan="2" valign="top">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td valign="top">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" valign="top">
							<span class="left_bt">感谢您使用M_Blog4J网站后台管理系统</span>
							<br>
							<br>
							<span class="left_txt">&nbsp;<img src="images/ts.gif"
									width="16" height="16"> 提示：<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您现在使用的是网站后台管理系统</span><span
								class="left_ts"></span><br>
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
							</span>
						</td>
						<td width="7%">
							&nbsp;
						</td>
						<td width="40%" valign="top">
							<table width="100%" height="144" border="0" cellpadding="0"
								cellspacing="0" class="line_table">
								<tr>
									<td width="7%" height="27"
										background="images/news-title-bg.gif">
										<img src="images/news-title-bg.gif" width="2" height="27">
									</td>
									<td width="93%" background="images/news-title-bg.gif"
										class="left_bt2">
										最新动态
									</td>
								</tr>
								<tr>
									<td height="102" valign="top">
										&nbsp;
									</td>
									<td height="102" valign="top"></td>
								</tr>
								<tr>
									<td height="5" colspan="2">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" valign="top">
							<!--JavaScript部分-->
							<SCRIPT language=javascript>
	function secBoard(n) {
		for (i = 0; i < secTable.cells.length; i++)
			secTable.cells[i].className = "sec1";
		secTable.cells[n].className = "sec2";
		for (i = 0; i < mainTable.tBodies.length; i++)
			mainTable.tBodies[i].style.display = "none";
		mainTable.tBodies[n].style.display = "block";
	}
</SCRIPT>
							<!--HTML部分-->
							<!-- <TABLE width=72% border=0 cellPadding=0 cellSpacing=0 id=secTable>
								<TBODY>
									<TR align=middle height=20>
										<TD align="center" class=sec2 onclick=secBoard(0)>
											验证信息
										</TD>
										<TD align="center" class=sec1 onclick=secBoard(1)>
											系统参数
										</TD>
										<TD align="center" class=sec1 onclick=secBoard(2)>
											版权说明
										</TD>
									</TR>
								</TBODY>
							</TABLE> -->
							<TABLE class=main_tab id=mainTable cellSpacing=0 cellPadding=0
								width=100% border=0>
								<!--关于TBODY标记-->
								<TBODY style="DISPLAY: block">
									<TR>
										<TD vAlign=top align=middle>
											<TABLE width=98% height="133" border=0 align="center"
												cellPadding=0 cellSpacing=0>
												<TBODY>
													<TR>
														<TD colspan="3"></TD>
													</TR>
													<TR>
														<TD height="5" colspan="3"></TD>
													</TR>
													<TR>
														<TD width="4%" background="images/news-title-bg.gif"></TD>
														<TD width="91%" background="images/news-title-bg.gif"
															class="left_ts">
															程序说明：
														</TD>
														<TD width="5%" background="images/news-title-bg.gif"
															class="left_txt">
															&nbsp;
														</TD>
													</TR>
													<TR>
														<TD bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															<span class="left_ts">1、</span>本程序由Struts2+Hibernate+Spring编写
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															&nbsp;
														</TD>
													</TR>
													<TR>
														<TD bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															<span class="left_ts">2、</span>本程序使用MySQL数据库
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															&nbsp;
														</TD>
													</TR>
													<TR>
														<TD bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															<span class="left_ts">3、</span> 运行服务器为Tomcat
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															&nbsp;
														</TD>
													</TR>
													<TR>
														<TD bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															<span class="left_ts">4、</span>程序使用，技术支持请联系qq760678330。
														</TD>
														<TD bgcolor="#FAFBFC" class="left_txt">
															&nbsp;
														</TD>
													</TR>
													<TR>
														<TD height="5" colspan="3"></TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
								</TBODY>
								<!--关于tBodies集合-->
								<TBODY style="DISPLAY: none">
									<TR>
										<TD vAlign=top align=middle>
											<TABLE width=98% border=0 align="center" cellPadding=0
												cellSpacing=0>
												<TBODY>
													<TR>
														<TD colspan="3"></TD>
													</TR>
													<TR>
														<TD height="5" colspan="3"></TD>
													</TR>
													<TR>
														<TD width="4%" height="25"
															background="images/news-title-bg.gif"></TD>
														<TD height="25" colspan="2"
															background="images/news-title-bg.gif" class="left_txt">
															<span class="TableRow2">服务器IP:<%=request.getRemoteAddr() %></span>
														</TD>
													</TR>
													<TR>
														<TD height="25" bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD width="42%" height="25" bgcolor="#FAFBFC">
															<span class="left_txt">服务器类型：Windows Server</span>
														</TD>
														<TD width="54%" height="25" bgcolor="#FAFBFC">
															<span class="left_txt"></span>
														</TD>
													</TR>
													
													<TR>
														<TD height="25" bgcolor="#FAFBFC">
															&nbsp;
														</TD>
														<TD height="25" bgcolor="#FAFBFC">
															<span class="left_txt">FSO文本读写：</span><span
																class="TableRow2"><font color="#FF0066"><b><img
																			src="images/X.gif" width="12" height="13">
																</b>
															</font>
															<img src="images/g.gif" width="12" height="12">
															</span>
														</TD>
														<TD height="25" bgcolor="#FAFBFC">
															<span class="left_txt">数据库使用：</span><span class="left_ts"><img
																	src="images/X.gif" width="12" height="13"><b
																style="color: blue"> MySQL</b>
															</span>
														</TD>
													</TR>

													<TR>
														<TD height="5" colspan="3"></TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
								</TBODY>
								<!--关于display属性-->
								<TBODY style="DISPLAY: none">
									<TR>
										<TD vAlign=top align=middle>
											<TABLE width=98% border=0 align="center" cellPadding=0
												cellSpacing=0>
												
											</TABLE>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
						<td>
							&nbsp;
						</td>
						<td valign="top">
							
						</td>
					</tr>
					<tr>
						<td height="40" colspan="4">
							<table width="100%" height="1" border="0" cellpadding="0"
								cellspacing="0" bgcolor="#CCCCCC">
								<tr>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="2%">
							&nbsp;
						</td>
						<td width="51%" class="left_txt">
							<img src="images/icon-mail2.gif" width="16" height="11">
							客户服务邮箱：760678330@qq.com
							<br>
							<img src="images/icon-phone.gif" width="17" height="14">
							官方网站：#
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
			<td background="images/mail_rightbg.gif">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td valign="bottom" background="images/mail_leftbg.gif">
				<img src="images/buttom_left2.gif" width="17" height="17" />
			</td>
			<td background="images/buttom_bgs.gif">
				<img src="images/buttom_bgs.gif" width="17" height="17">
			</td>
			<td valign="bottom" background="images/mail_rightbg.gif">
				<img src="images/buttom_right2.gif" width="16" height="17" />
			</td>
		</tr>
	</table>
</body>
