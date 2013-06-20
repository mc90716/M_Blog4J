<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*,com.blog.bean.*,com.blog.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<% 
   PageView<Message> pageView = (PageView<Message>)request.getAttribute("allMessages"); 
   List<Message> messages = pageView.getRecords();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>M_Blog4J后台管理</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>


<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/background/js/verify.js"></script>

<script>
var  highlightcolor='#eafcd5';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
function checkAll(){
	var object = document.getElementsByName("checkbox14");
	var length = object.length;
	if(object[0].checked){
		for(var i=0;i<length;i++){
			 object[i].checked = false;
	    }
	}
	else{
	   for(var i=0;i<length;i++){
		 object[i].checked = true;
	  }
	}
}

function topage(currentPage){
	//window.location.href='news!showNews.action?currentPage='+currentPage;
}


function delcfm(msgId) {
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
    else{
    	window.location.href='adminUserAction!delMessage.action?messageId='+msgId;
    }
}

function delAll(currentPage){
	 if (!confirm("确认要删除？")){
		 window.event.returnValue = false;
	 }
	 else{
		var id = null;
		var object = document.getElementsByName("checkbox14");
		var length = object.length;
		for(var i=0;i<length;i++){
			if(object[i].checked==true)
				id = i + "," + id;
		}
		//window.location.href='article!deleteArticle.action?currentPage='+currentPage+'&id='+id;
	 }
}
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="<%=request.getContextPath() %>/background/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="<%=request.getContextPath() %>/background/images/tab_05.gif"><img src="<%=request.getContextPath() %>/background/images/311.gif" width="16" height="16" /> <span class="STYLE4"></span></td>
        <td width="281" background="<%=request.getContextPath() %>/background/images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60">
              	<form action="adminUserAction!searchMessage.action" method="post">
              	<table width="87%" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                  <td class="STYLE1">
                   	 <div align="center">
                        <input type="text" name="keyWord_send" placeholder="按发送者查找"/>
                     </div>
                    </td>
                    <td class="STYLE1"><div align="center"><input type="submit" name="查找" value="查找"/></div></td>
                    
                    <td class="STYLE1">
                   	 <div align="center">
                        <input type="text" name="keyWord_accept" placeholder="按接收者查找"/>
                     </div>
                    </td>
                    <td class="STYLE1"><div align="center"><input type="submit" name="查找" value="查找"/></div></td>
                  
                  
                    <td class="STYLE1">
                   	 <div align="center">
                        <input type="text" name="keyWord_content" placeholder="按留言内容查找"/>
                     </div>
                    </td>
                    <td class="STYLE1"><div align="center"><input type="submit" name="查找" value="查找"/></div></td>
                  </tr>
              </table>
              </form>
              </td>
            </tr>
        </table></td>
        <td width="14"><img src="<%=request.getContextPath() %>/background/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=request.getContextPath() %>/background/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
        
        
        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="6%" height="26" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">选择</div></td>
            <td width="8%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">留言ID</div></td>
            <td width="10%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">留言标题</div></td>
             <td width="10%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">留言内容</div></td>
             <td width="10%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">发送者</div></td>
            <td width="14%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">接受者</div></td>

            <td width="7%" height="18" background="<%=request.getContextPath() %>/background/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作</div></td>
          </tr>
        <% for(Message message : messages) {%>
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox14" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=message.getMsgId()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=message.getTitle()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=message.getContent()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=message.getFromUser().getUserName()%></div></td>
			<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=message.getToUser().getUserName()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2">
           
            <img src="<%=request.getContextPath() %>/background/images/010.gif" width="9" height="9" /></span>
            <span class="STYLE2"> </span><span class="STYLE1"></span>
             [<a href="#"  onclick="delcfm(<%=message.getMsgId()%>)">删除</a>]
          
            <span class="STYLE1"></span></div></td>
          </tr>
           <%} %>
        </table></td>
        
        
        <td width="9" background="images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=request.getContextPath() %>/background/images/tab_20.gif" width="15" height="29" /></td>
        <td background="images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共<%=pageView.getTotalrecord() %>条纪录，当前第<%=pageView.getCurrentpage() %>页，每页<%=pageView.getMaxresult()%>条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="62" height="22" valign="middle"><div align="right"><a href="adminUserAction!showAllMessageByPage.action?currentPage=1" ><img src="<%=request.getContextPath() %>/background/images/first.gif" width="37" height="15" /></a></div></td>
                  
                  <%if(pageView.getCurrentpage() > 1) {%>
                  <td width="50" height="22" valign="middle"><div align="right"><a href="adminUserAction!showAllMessageByPage.action?currentPage=<%=pageView.getCurrentpage()-1 %>"><img src="<%=request.getContextPath() %>/background/images/back.gif" width="43" height="15" /></a></div></td>
                  <%}%>
                  
                 
                  <td width="54" height="22" valign="middle"><div align="right"><a href="adminUserAction!showAllMessageByPage.action?currentPage=<%=pageView.getCurrentpage()+1%>"><img src="<%=request.getContextPath() %>/background/images/next.gif" width="43" height="15" /></a></div></td>
                 
                  
                  <td width="49" height="22" valign="middle"><div align="right"><a href="adminUserAction!showAllMessageByPage.action?currentPage=<%=pageView.getTotalpage() %>"><img src="<%=request.getContextPath() %>/background/images/last.gif" width="37" height="15" /></a></div></td>
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input id="page" name="textfield" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" onchange="javaScript:topage(this.value)"/>
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><a href="#"><img src="<%=request.getContextPath() %>/background/images/go.gif" width="37" height="15" /></a></td>
                </tr>
              </table>
              
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=request.getContextPath() %>/background/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<s:debug></s:debug>
</body>
</html>
