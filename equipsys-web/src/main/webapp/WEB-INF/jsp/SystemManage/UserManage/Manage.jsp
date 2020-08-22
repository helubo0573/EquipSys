<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
</head>
<script type="text/javascript" src="../js/SysUser.js?d=202007091"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.excheck.js"></script>
<body>
	<div  class="body-sdiv">
		<div class="title-div">
			用户管理
		</div>
		<div id="sysuser-seachdiv" class="box-div form-inline" style="overflow: hidden;text-align: left;">
			<%@include file="search.jsp" %>
		</div>
		<div id="sysuser-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
			<shiro:hasPermission name="sys:user:save">
				<button class="btn btn-success" onclick="showSysUserInfo('0')">新建用户</button>
			</shiro:hasPermission>
		</div>
		<div id="sysuser-listdiv" class="box-div form-inline" style="height: 600px;">
			<%@include file="List.jsp" %>
		</div>
		<form id="sysuser-form" class="form-horizontal box-div" style="display: none;">
			<input name="userinfoid" id="userinfo-id" type="hidden">
			<input name="userinfoempid" id="userinfo-empid" type="hidden">
			<table class="table table-bordered layeropen">
				<tbody>
					<tr>
						<th width="100px"><label class="control-label">用户名</label></th>
						<td>
							<input class="form-control needing" name="userinfoname" id="userinfo-name">
						</td>
						<th width="100px"><label class="control-label">所属员工</label></th>
						<td>
							<input class="form-control needing point" name="userinfo-empname" id="userinfo-empname" data-post="" readonly onclick="showEmpTree()" placeholder="点此选择用户所属员工">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>