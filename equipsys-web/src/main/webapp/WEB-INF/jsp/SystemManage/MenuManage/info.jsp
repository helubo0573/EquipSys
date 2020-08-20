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
			系统页面资源管理
		</div>
		<div id="menu-info" class="info-div">
			<div class="form-horizontal"  style="overflow: hidden;">
				<input type="hidden" id="menu-id">
				<input type="hidden" id="parent-id">
				<div class="form-group">
					<label for="ftype" class="col-lg-3 control-label">资源类型:</label>
				    <div class="col-lg-7">
				    	<label class="radio-inline">
				    		<input name="menu-type" type="radio" value="0">目录级
				    	</label>
				    	<label class="radio-inline">
				    		<input name="menu-type" type="radio" value="1">菜单级
				    	</label>
				    	<label class="radio-inline">
				    		<input name="menu-type" type="radio" value="2">按钮级
				    	</label>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">资源名称:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-name" placeholder="目录、菜单或按钮名称">
				    </div>
				</div>
				<div class="form-group">
				    <label  class="col-lg-3 control-label">所属上级:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-parent" placeholder="点击选择当前资源的上级" readonly="readonly" style="cursor: pointer;" onclick="selectparent()">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">URL:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-url" placeholder="菜单级填写:/xxx/xxx.do">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">授权标识:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-perms" placeholder="按钮级填写:xxx:xxx:xxx">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">排序:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-order" placeholder="填写排列顺序">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">图标代码:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="menu-icon" placeholder="填写图标编码">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">备注:</label>
				    <div class="col-lg-7">
				      <textarea id="menu-remarks" class="form-control" rows="2" cols="" style="resize: none;" placeholder="填写资源说明"></textarea>
				    </div>
				</div>
			</div>
		</div>
		<div id="menu-btn" class="btn-div">
			<shiro:hasPermission name="sys:menu:add">
				<button id="insert-btn" class="btn btn-success" onclick="InsertMenu()">新增</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:menu:update">
				<button id="update-btn" class="btn btn-info" onclick="UpdateMenu()" disabled="disabled">修改</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:menu:delete">
				<button id="delete-btn" class="btn btn-danger" onclick="DeleteMenu()" disabled="disabled">删除</button>
			</shiro:hasPermission>
			<button id="reset-btn" class="btn btn-warning" onclick="ResetMenu()">重置</button>
		</div>
	</div>
</body>
</html>