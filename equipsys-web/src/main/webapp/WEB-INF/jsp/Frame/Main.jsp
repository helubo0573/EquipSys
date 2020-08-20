<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasRole name="user"></shiro:hasRole> 
<%@include file="../common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>焱鑫有色金属有限公司设备管理系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/P-Main.css?d=202003083">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
</head>
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<body style="background-color: black;">
	<div class="body-div">
		<div class="frame-box top-div">
			<%@include file="./Top.jsp"%>
		</div>
		<%-- <div class="frame-box headbar-div">
			<%@include file="./HeadBar.jsp" %>
		</div> --%>
		<div class="content-div">
			<div class="frame-box sider-div">
				<%@include file="./Sider.jsp" %>
			</div>
			<div class="frame-box workbench-div">
				<%@include file="./Workbench.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>