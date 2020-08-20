<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			仓库仓位信息维护
		</div>
		<form id="storeinfo-form" class="form-horizontal info-div" style="overflow: hidden;">
			<input type="hidden" id="storeid-info" name="storeid">
			<input type="hidden" id="parentid-info" name="parentid">
			<div class="form-group">
			    <label class="col-lg-3 control-label">仓库(位)名称:</label>
			    <div class="col-lg-7">
			      <input class="form-control needing" id="storename-info" name="storename" placeholder="请输入仓库或仓位名称">
			    </div>
			</div>
			<div class="form-group">
			    <label class="col-lg-3 control-label">序号:</label>
			    <div class="col-lg-7">
			      <input class="form-control" id="storeorder-info" name="storeorder" placeholder="设定排序号">
			    </div>
			</div>
			<div class="form-group">
			    <label  class="col-lg-3 control-label">所属仓库:</label>
			    <div class="col-lg-7">
			      <input class="form-control" id="parentstore-info" placeholder="不选则为仓库" readonly="readonly" style="cursor: pointer;">
			    </div>
			</div>
			<div class="form-group">
			    <label class="col-lg-3 control-label">备注:</label>
			    <div class="col-lg-7">
			      <textarea id="storeremarks-info" name="remarks" class="form-control" rows="2" cols="" style="resize: none;" placeholder="填写位置说明、存储品类等"></textarea>
			    </div>
			</div>
		</form>
		<div id="store-btn" class="btn-div">
			<shiro:hasPermission name="store:info:save">
				<button id="insert-btn" class="btn btn-success" onclick="insertStoreInfo()">新增</button>
				<button id="update-btn" class="btn btn-info" onclick="updateStoreInfo()" disabled>修改</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="store:info:delete">
				<button id="delete-btn" class="btn btn-danger" onclick="deleteStoreInfo()" disabled>删除</button>
			</shiro:hasPermission>
			<button id="reset-btn" class="btn btn-warning" onclick="resetStoreInfo()">重置</button>
		</div>
	</div>
</body>
</html>