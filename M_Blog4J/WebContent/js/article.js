function replayComment(element){
	var a = element.title;
	$(".replayCommentId")[0].value = a;
	//alert(a);
	$("#comment_content").focus();
	$("#comment_content").val("reply"+a+":");
	$(".commentForm").submit();
}

function favourite(){
	var params = {
                     articleId:$('.articleId').attr('value')
                };
  	var url = "articleAction!collectArticle.action";
  	jQuery.post(url,params,collectCallBackFun,'json');
}
function collectCallBackFun(data){
	if(data.favouriteFlag == "true"){
		alert("收藏成功");
	}else{
		alert("已经存在");
	}
}
