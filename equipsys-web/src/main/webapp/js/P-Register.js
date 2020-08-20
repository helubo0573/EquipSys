/**
 * 
 */
$(function(){
	$("#register-btn").click(function(){
		var data=creatRegisterParam();
		if(data!=null){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"user/register.do",
				data:data,
				success:function(data){
					
				}
			})
		}
	})
	$("#re-username").blur(function(){
		var re =/^[a-zA-Z0-9]+$/;
		var headre=/^[a-zA-Z][a-zA-Z0-9]+$/;
		var username=$(this).val().trim();
		if(username==""){
			layer.tips("用户名不能为空",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"用户名不能为空","error-input")
			return;
		}
		if(username.length<6 || username.length>12){
			layer.tips("用户名长度不能小于6位或大于12",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"用户名长度不能小于6位或大于12","error-input")
			return;
		}
		if(!headre.test(username)){
			layer.tips("用户名第一位必须为英文",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"用户名第一位必须为英文","error-input")
			return;
		}
		if(!re.test(username)){
			layer.tips("用户名只能包含英文及数字",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"用户名只能包含英文及数字","error-input")
			return;
		}
		setELFlag($(this),"","right-input")
	})
	$("#re-phone").blur(function(){
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
		var phone=$(this).val().trim();
		if(phone==""){
			layer.tips("手机号不能为空",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"手机号不能为空","error-input")
			return;
		}
		if (!myreg.test(phone)){
			layer.tips("手机号格式不正确",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"手机号格式不正确","error-input")
			return;
		}
		setELFlag($(this),"","right-input")
	})
	$("#re-password").blur(function(){
		var password=$(this).val().trim();
		var repassword=$("#re-repassword").val().trim();
		if(password.length<12 || password.length>18){
			layer.tips("密码长度不能小于12或大于18",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"密码长度不能小于12或大于18","error-input")
			return;
		}
		setELFlag($(this),"","right-input");
		checkpassword();
	})
	$("#re-repassword").blur(function(){
		checkpassword();
	})
	$("#re-checkmsg").blur(function(){
		var msg=$(this).val().trim();
		if(msg.length!=6){
			layer.tips("请填写6位验证码",$(this),{tips: [2, '#ff0000']});
			setELFlag($(this),"请填写6位验证码","error-input")
			return;
		}
		setELFlag($(this),"","right-input");
	})
	$("div.col-lg-9").find("input").mouseover(function(){
		if($(this).hasClass("error-input")){
			layer.tips($(this).attr("data-msg"),$(this),{tips: [2, '#ff0000']})
		}
	})
})

function setELFlag(e,msg,classname){
	$(e).removeClass("error-input");
	$(e).removeClass("right-input");
	$(e).attr("data-msg",msg);
	$(e).addClass(classname)
}

function checkpassword(){
	var password=$("#re-password").val().trim();
	var repassword=$("#re-repassword").val().trim();
	if(11<password.length && password.length<19 && repassword.length>0){
		if(password!=repassword){
			layer.tips("两次密码不一致",$("#re-password"),{tips: [2, '#ff0000']});
			setELFlag($("#re-password"),"两次密码不一致","error-input")
			layer.tips('两次密码不一致',$("#re-repassword"), {tipsMore: true,tips: [2, '#ff0000']});
			setELFlag($("#re-repassword"),"两次密码不一致","error-input")
			return;
		}else{
			setELFlag($("#re-repassword"),"","right-input");
			setELFlag($("#re-password"),"","right-input");
		}
	}
}
function creatRegisterParam(){
	var param="1";
	var applye=$('#applye-ck').is(':checked');
	$("div.col-lg-9").find("input").each(function(index,e){
		if($(e).val().trim()==""){
			layer.tips($(this).attr("title")+"不能为空",$(e),{tipsMore: true,tips: [2, '#ff0000']})
			setELFlag($(this),$(this).attr("title")+"不能为空","error-input")
			param=null
		}
		if($(e).hasClass("error-input")){
			layer.tips($(this).attr("data-msg"),$(this),{tipsMore: true,tips: [2, '#ff0000']})
			param=null;
		}
	})
	if(!applye){
		layer.tips("若无法认同协议将不能注册用户",$('#applye-ck'),{tipsMore: true,tips: [2, '#ff0000']})
		param=null;
	}
	return param=="1"?"username="+$("#re-username").val().trim()+"&password="+hex_md5($("#re-password").val().trim())+"&phone="+$("#re-phone").val().trim()+"&checkmsg="+$("#re-checkmsg").val().trim():null;
}