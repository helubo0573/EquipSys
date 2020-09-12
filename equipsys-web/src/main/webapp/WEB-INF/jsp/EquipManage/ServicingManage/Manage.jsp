<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			设备维修申请管理
		</div>
		<div id="servicingAppManage-seach" class="box-div form-inline" style="overflow: hidden;text-align: left;">
			<div class="input-group input-group-sm col-lg-3">
		        <span class="th input-group-addon">员工姓名</span>
		        <input class="form-control" id="search-name" placeholder="">
		    </div>
		    <div class="input-group input-group-sm col-lg-3">
		        <span class="th input-group-addon">所在部门</span>
		        <input class="form-control" id="search-dept" placeholder="">
		    </div>
		    <div class="input-group input-group-sm col-lg-3">
		        <span class="th input-group-addon">所在岗位</span>
		        <input class="form-control"  id="search-post" placeholder="">
		    </div>
		    <button class="search-btn btn btn-info right" style="height: 100%;width: 100px;" onclick="">查询</button>
		</div>
		<div id="emp-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
				<button class="btn btn-success" onclick="">新增设备维修申请</button>
		</div>
		<div id="servicingAppManage-list" class="box-div form-inline" style="height: 600px;">
			<%@include file="ApplicationList.jsp" %>
		</div>
	</div>
	<form id="servicingAppManage-info" class="form-horizontal box-div" style="display: none;">
		
	</form>
</body>
</html>