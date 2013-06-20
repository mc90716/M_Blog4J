function tabClick(element){
	var s1 = ['li_articleManage','li_columnManage','li_commentManage','li_recycleBin','li_publishManage'];
	var id = element.id;

	$("#" + id).attr("class","active");

	$.each(s1,function(key,val){
		if(val != id){
			$("#" + val).attr("class","");
		}
	});
	
	var s2 = ['articleManage','columnManage','commentManage','recycleBin','publishManage'];
	var str = id.substring(3, id.length);
	$("#" + str).css("display","block");
	$.each(s2,function(key,val){
		if(val != str){
			$("#" + val).css("display","none");
		}
	});
}


function createColumn(){
	$(".bor_box").css("display","block");
}

function saveColumn(){
	var a = $("#channelId").get(0).selectedIndex;
	$(".columnform").attr("action","blogColumnAction!addBlogColumn.action?categoryId=" + a);
	$(".columnform").submit();
}

/*$(function(){
	$(".t_btn").click(function(){
		$(".bor_box").css("display","block");
    });
});

*/
/*
(function(){
	alert("dqoqiuho");
	var str = '<%= request.getAttribute("article");%>';
	alert(str);
})();

$()
*/
/*
function tabEditClick(element){
	$("#li_publishManage").attr("class","active");
	$("#li_articleManage").attr("class","");
	$("#publishManage").css("display","block");
	$("#articleManage").css("display","none");
	//alert(element.id);
	var params = {
		id:element.id
	};
	jQuery.post('articleAction!editArticle.action',params,editArticleCallBackFun,'json');
}
function editArticleCallBackFun(data){
	$.each(data,function(key,value){
		if(key == 'content'){
			$('#myEditor').val(value);
			alert($('#myEditor').val());
		}
		if(key == 'artileTitle'){
			$('.input_title').html("ddddddddddddd");
		}
	});
	
}*/
