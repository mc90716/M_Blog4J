var isSameName = null;

function checkUserName () {
  	var params = {
                     name:$('#em_name').attr('value')
                };
  	var url = "userAction!checkUserExist.action";
  	jQuery.post(url,params,regCallBackFun,'json');
}

function regCallBackFun(data){
	isSameName = data.result;
	if(isSameName == "yes"){
		$("#check_result_name").html("用户名已经被使用");
		$("#check_result_name").css("color","#FA1100");
	}else{
		$("#check_result_name").html("");
	}
}

function checkPasswd(){
	if($("#p1").val().length <6){
		$("#check_result_passwd1").html("密码长度不能小于六位");
		$("#check_result_passwd1").css("color","#FA1100");
		return 0;
	}
}


function submitRegForm(){
	if($("#em_name").val().length == 0){
		$("#check_result_name").html("请填写用户名");
		$("#check_result_name").css("color","#FA1100");
		return 0;
	}
	if($("#em_email").val().length == 0){
		$("#check_result_email").html("请填写Email");
		$("#check_result_email").css("color","#FA1100");
		return 0;
	}
	if($("#p1").val().length == 0){
		$("#check_result_passwd1").html("前填写密码");
		$("#check_result_passwd1").css("color","#FA1100");
		return 0;
	}
	if($("#cd").val().length == 0){
		$("#check_result_cd").html("请填写验证码");
		$("#check_result_cd").css("color","#FA1100");
		return 0;
	}
	if(isSameName == "yes"){
		$("#em_name").css("background","#FA1100");
		$("#em_name").focus();
		$("#check_result_name").html("前填写用户名");
		$("#check_result_name").css("color","#FA1100");
	}
	if((isSameName == "no")&&(checkEmail())&&(checkPasswdSame()))
	    $("#form_register").submit();
}

function checkEmail(){
	var filter  = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	if(filter.test($("#em_email").val())){
		$("#check_result_email").html("");
		return true;
	}else{
		$("#em_email").focus();
		$("#check_result_email").html("请正确填写Email");
		$("#check_result_email").css("color","#FA1100");
		return false;
	}
}

function createRandomImg(){
	$("#vcImg").attr("src","userAction!createRandomImg.action");
}

function login(){
	var params = {
		uName:$('#u').attr('value'),uPasswd:$('#p').attr('value')
	};
	var url = "userAction!loginUser.action";
  	jQuery.post(url,params,logCallBackFun,'json');
}
function logCallBackFun(data){
	if(data.logResult == "false"){
		alert("log error");
	}else if(data.logResult == "true"){
		window.location.href = "blogAction!showHomeCenter.action";
	}
}

function checkPasswdSame(){
	if($("#p1").val() == $("#p2").val()){
		$("#check_result_passwd2").html("");
		return true;
	}else{
		$("#p2").focus();
		$("#check_result_passwd2").html("两次输入的密码不一样");
		$("#check_result_passwd2").css("color","#FA1100");
		return false;
	}
}


function focusUser(){
	var params = {
                     userId:$('.focusId').attr('value')
                };
  	var url = "userAction!payAttention.action";
  	jQuery.post(url,params,fucusCallBackFun,'json');
}
function fucusCallBackFun(data){
	if(data.focusResult == "true"){
		alert("关注成功");
	}if(data.focusResult == "false"){
		alert("已经是好友");
	}
}


var toUserId = null;
var toUserName = null;


function leaveMessage(){
	var params = {
                     userId:$('.focusId').attr('value')
                };
                
     toUserId = $('.focusId').attr('value');
     toUserName = $('.currentUserName').attr('value');
   	var url = "messageAction!leaveMessage.action";
  	jQuery.post(url,params,messageCallBackFunction,'json');
}
function messageCallBackFunction(data){
	if(data.result == "true"){
		window.location.href="homeFunction/leaveWord.jsp?youId=" + toUserId + "&userName=" + toUserName;
	}if(data.result == "false"){
		alert("未关注");
	}
}
/*
(function(){
	$(".input_2").value="aaaaaaaaaaaaa";
	$(".input_2").html("qwer");
})();

jQuery(document).ready(function(){$(".input_2")[0].value=toUserName;});

*/



function sendMessage(){
	$(".sendMessage").submit();
	//alert(toUserName);
}

