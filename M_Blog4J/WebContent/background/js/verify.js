//定义用户校验的方法
function recommendArticle(element){
	var params = {
            articleId:element
       };
var url = "adminArticleAction!recommendArticle.action";
jQuery.post(url,params,recommendCallBackFun,'json');
}
//回调函数
function recommendCallBackFun(data){
   alert("推荐成功");
}

function lockUser(element){
	var params = {
            userId:element
       };
    var url = "adminUserAction!lockUser.action";
    jQuery.post(url,params,lockUserCallBackFun,'json');
}
function lockUserCallBackFun(data){
	alert("锁定成功");
}

function unLockUser(element){
	var params = {
            userId:element
       };
    var url = "adminUserAction!unLockUser.action";
    jQuery.post(url,params,unLockUserCallBackFun,'json');
}
function unLockUserCallBackFun(data){
	alert("解锁");
}
