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
</head>
<body>
	<div style="padding: 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 20px;">
			机构信息管理
		</div>
		<div id="org-info" class="info-div">
			<div class="form-horizontal"  style="overflow: hidden;">
				<input type="hidden" id="dept-id">
				<div class="form-group">
					<label for="ftype" class="col-lg-3 control-label">部门类型:</label>
				    <div class="col-lg-7" align="left">
				    	<label class="radio-inline">
				    		<input name="dept-type" type="radio" id="depttype-dept" value="0">部门
				    	</label>
				    	<label class="radio-inline">
				    		<input name="dept-type" type="radio" id="depttype-post" value="1">岗位
				    	</label>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">部门名称:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="dept-name" placeholder="部门或岗位名称">
				    </div>
				</div>
				<div class="form-group">
				    <label  class="col-lg-3 control-label">所属上级:</label>
				    <div class="col-lg-7">
				      <input class="form-control" id="dept-parent" data-parent="" placeholder="点击选择当前资源的上级" readonly="readonly" style="cursor: pointer;" onclick="shouwParentDeptTree()">
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">备注:</label>
				    <div class="col-lg-7">
				      <textarea id="dept-remarks" class="form-control" rows="2" cols="" style="resize: none;" placeholder="填写资源说明"></textarea>
				    </div>
				</div>
			</div>
		</div>
		<div id="dept-btn" class="btn-div">
			<shiro:hasPermission name="org:dept:save">
				<button id="insert-btn" class="btn btn-success" onclick="InsertDept()">新增</button>
				<button id="update-btn" class="btn btn-info" onclick="UpdateDept()" disabled="disabled">修改</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="org:dept:delete">
				<button id="delete-btn" class="btn btn-danger" onclick="DeleteDept()" disabled="disabled">删除</button>
			</shiro:hasPermission>	
			<button id="reset-btn" class="btn btn-warning" onclick="ResetDept()">重置</button>
		</div>
	</div>
</body>
</html>