/**
 */
$(function(){
//	checkcode();
	
	$("#codeimg").click(function(){
		checkcode();
	})
	$("#loginbt").click(function(){
		var username=$("#username").val().trim();
		var password=$("#password").val().trim();
		var checkcode=$("#code-input").val().trim();
		if(username==""){
			layer.msg("用户名不能为空",{btn:['确定']});
			return;
		}
		if(password==""){
			layer.msg("密码不能为空",{btn:['确定']});
			return;
		}
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"user/login.do",
			data:"username="+username+"&password="+hex_md5(password)+"&checkcode="+checkcode,
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg)
					window.location="manage/mainpage.do";
				}else{
					layer.msg(data.msg,{time:3000,btn:['确定']})
					var times = (new Date()).getTime();
					var urls="user/getimgcode.do?timestamp="+times;
					$("#codeimg").attr("src",urls)
				}
			},
			error:function(data){
				layer.msg(data.msg,{time:3000,btn:['确定']})				
			}
		})
	})
	function checkcode(){
		var times = (new Date()).getTime();
		var urls="user/getimgcode.do?timestamp="+times;
		$("#codeimg").attr("src",urls)
	}
})


