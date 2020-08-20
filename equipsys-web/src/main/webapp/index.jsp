<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/Commons.css">
    <link rel="stylesheet" type="text/css" href="./css/P-Login.css?v=202005301">
	<title>焱鑫有色金属有限公司设备管理系统</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css?d=202005301">
<body onkeydown="keyLogin()">
	<div class="login-box frame-box">
		<div align="center" style="margin-bottom: 30px;">
			<h1>登&nbsp;&nbsp;&nbsp;&nbsp;录</h1>
		</div>
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
              	<div class="layui-input-block">
                	<input id="username" class="layui-input" placeholder="请输入账号">
              	</div> 
			</div>
			<div class="layui-form-item">
              	<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            	<div class="layui-input-block">
                	<input type="password" id="password" class="layui-input" placeholder="请输入密码">
            	</div>              
			</div>
			<div class="layui-form-item">
              	<label for="checkcode" class="layui-form-label">验证码</label>
            	<div class="layui-input-inline" style="width: 120px;">
                	<input class="layui-input" id="code-input" type="text" maxlength="4">
            	</div>
             		<img id="codeimg" src="user/getimgcode.do" style="position:static;">
			</div>
			<div class="layui-form-item">
				<input type="checkbox" name="" lay-skin="primary" checked style="color: white;">
				<label class="layui-form-label" style="clear: both;">记住用户名</label>
               	<input type="button" id="loginbt" value="登  录" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal" style="float:right;">
			</div>
		</form>
	</div>
</body>
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
<script src="./plugins/encrypt.js"></script>
<script src="./js/P-Login.js?d=202006301"></script>
<script type="text/javascript">
$(function(){
	if (window != top) top.location.href = location.href;
})
function keyLogin(){
 if (event.keyCode==13)  //回车键的键值为13
   $("#loginbt").click(); //调用登录按钮的登录事件
}
</script>
</html>