<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="top-div">
		<span>
			焱鑫有色金属有限公司		
		</span>
		<span>
			${sessionScope.SysUser.deptname }
		</span>
		<span>
			${sessionScope.SysUser.empname }
		</span>
		<span>
			欢迎您！
		</span>
		<span>
			<label id="quit-a">
				退出
			</label>
		</span>
		<!-- <span>
			<button class="btn btn-sx">退出</button>
		</span> -->
	</div>
</body>
</html>