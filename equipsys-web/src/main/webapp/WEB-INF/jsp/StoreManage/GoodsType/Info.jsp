<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="padding: 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 20px;">
			物料类型信息维护
		</div>
		<form id="goodstype-form" class="form-horizontal info-div" style="overflow: hidden;">
			<input type="hidden" id="goodstypeinfo-id" name="id">
			<div class="form-group">
			    <label class="col-lg-3 control-label">类型名称:</label>
			    <div class="col-lg-7">
			      <input class="form-control needing" id="goodstypeinfo-name" name="name" placeholder="请输入物料类型名称">
			    </div>
			</div>
			<div class="form-group">
			    <label class="col-lg-3 control-label">序号:</label>
			    <div class="col-lg-7">
			      <input class="form-control" id="goodstypeinfo-order" name="order" placeholder="设定排序号">
			    </div>
			</div>
			<div class="form-group">
			    <label  class="col-lg-3 control-label">速查编码:</label>
			    <div class="col-lg-7">
			      <input class="form-control"  id="goodstypeinfo-quickcode" name="quickcode" placeholder="建议使用名称首拼或自定义编号">
			    </div>
			</div>
			<div class="form-group">
			    <label class="col-lg-3 control-label">备注:</label>
			    <div class="col-lg-7">
			      <textarea id="goodstypeinfo-remarks" name="remarks" class="form-control" rows="2" cols="" style="resize: none;" placeholder="填写物料类型说明"></textarea>
			    </div>
			</div>
		</form>
		<div id="goodstype-btn" class="btn-div">
			<shiro:hasPermission name="goodstype:info:save">
				<button id="insert-btn" class="btn btn-success" onclick="insertGoodsType()">新增</button>
				<button id="update-btn" class="btn btn-info" onclick="updateGoodsType()" disabled>修改</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="goodstype:info:delete">
				<button id="delete-btn" class="btn btn-danger" onclick="deleteGoodsType()" disabled>删除</button>
			</shiro:hasPermission>
			<button id="reset-btn" class="btn btn-warning" onclick="resetGoodsType()">重置</button>
		</div>
	</div>
</body>
</html>