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
	<div style="padding: 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 20px;">
			角色信息维护
		</div>
		<form id="roleinfo-form" class="form-horizontal info-div" style="overflow: hidden;">
			<input type="hidden" id="roleid-info" name="roleid">
			<input type="hidden" id="deptid-info" name="deptid">
			<div class="form-group">
			    <label class="col-lg-3 control-label">角色名称:</label>
			    <div class="col-lg-7">
			      <input class="form-control needing" id="rolename-info" name="rolename" placeholder="请输入角色名称">
			    </div>
			</div>
			<div class="form-group">
			    <label  class="col-lg-3 control-label">所属部门:</label>
			    <div class="col-lg-7">
			      <input class="form-control" id="roledept-info" placeholder="不选则为全局角色" readonly="readonly" style="cursor: pointer;">
			    </div>
			</div>
			<div class="form-group">
			    <label class="col-lg-3 control-label">备注:</label>
			    <div class="col-lg-7">
			      <textarea id="roleremarks-info" name="remarks" class="form-control" rows="2" cols="" style="resize: none;" placeholder="填写资源说明"></textarea>
			    </div>
			</div>
		</form>
		<div id="role-btn" class="btn-div">
			<shiro:hasPermission name="sys:role:save">
				<button id="insert-btn" class="btn btn-success" onclick="insertRole()">新增</button>
				<button id="update-btn" class="btn btn-info" onclick="updateRole()" disabled>修改</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:role:delete">
				<button id="delete-btn" class="btn btn-danger" onclick="deleteRole()" disabled>删除</button>
			</shiro:hasPermission>
			<button id="reset-btn" class="btn btn-warning" onclick="resetRole()">重置</button><br><br>
			<shiro:hasPermission name="sys:role:setmenu">
				<button id="rolemenu-btn" class="btn btn-success" disabled onclick="setRoleTMenu()">角色资源维护</button>
			</shiro:hasPermission>
		</div>
	</div>
</body>
</html>