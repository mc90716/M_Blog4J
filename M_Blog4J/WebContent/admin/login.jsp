<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  
<base href="<%=basePath%>">
    
    <title>M_Blog4J后台管理</title>
    

	<script type="text/javascript" src="js/verify.js"></script>
	<script type="text/javascript" src="<%=basePath%>/background/js/jquery-1.3.2.js"></script>
	<link href="css/skin.css" rel="stylesheet" type="text/css">
	
  </head>

  <style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
	function correctPNG()
	{
	    var arVersion = navigator.appVersion.split("MSIE");
	    var version = parseFloat(arVersion[1]);
	    if ((version >= 5.5) && (document.body.filters)) 
	    {
	       for(var j=0; j<document.images.length; j++)
	       {
	          var img = document.images[j];
	          var imgName = img.src.toUpperCase();
	          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
	          {
	             var imgID = (img.id) ? "id='" + img.id + "' " : "";
	             var imgClass = (img.className) ? "class='" + img.className + "' " : "";
	             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' ";
	             var imgStyle = "display:inline-block;" + img.style.cssText ;
	             if (img.align == "left") imgStyle = "float:left;" + imgStyle;
	             if (img.align == "right") imgStyle = "float:right;" + imgStyle;;
	             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle;
	             var strNewHTML = "<span " + imgID + imgClass + imgTitle
	             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
	             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
	             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" ;
	             img.outerHTML = strNewHTML;
	             j = j-1;
	          }
	       }
	    }    
	}

	 function FrontPage_Form1_Validator(theForm)
	  {

	    if (theForm.user.value == "")
	    {
	      alert("请输入管理员账号！");
	      theForm.user.focus();
	      return (false);
	    }

	    if (theForm.user.value.length < 2)
	    {
	      alert("在 用户名 域中，请至少输入 2 个字符。");
	      theForm.user.focus();
	      return (false);
	    }

	    if (theForm.user.value.length > 20)
	    {
	      alert("在 用户名 域中，请最多输入 20 个字符。");
	      theForm.user.focus();
	      return (false);
	    }

	    if (theForm.pass.value == "")
	    {
	      alert("请输入管理员密码");
	      theForm.pass.focus();
	      return (false);
	    }
	  }
</script>


  <body>
    <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right"  valign="top"><img src="images/logo.png" width="279" height="68">
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>联系电话：0531—80687807</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>邮箱地址： mc90716@163.com</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>邮&nbsp;&nbsp;&nbsp;编： 250357</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>地&nbsp;&nbsp;&nbsp;址： 山东省济南市长清区大学科技园海棠路5001号</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td align="left"><img src="images/cailiao.png" width="250" height="30"></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="center">
                    <form name=FrontPage_Form1 action="adminUserAction!adminLogin.action" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text"><input name="user.userName" class="editbox4" value="" size="20"></td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" size="20" name="user.passWd">
                              <img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr><td height="5"></td></tr> 
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="15%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 陆"> </td>
                            <td width="67%" class="top_hui_text"><input name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()"></td>
                          </tr>
                          <tr><td height="35"></td></tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
      	<td align="center"><span class="login-buttom-txt"></span></td>
      </tr>
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2011-2012 www.sdjtu.cn</span></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
