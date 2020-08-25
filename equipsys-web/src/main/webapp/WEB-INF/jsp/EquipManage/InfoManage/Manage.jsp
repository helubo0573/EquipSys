<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
<style type="text/css">
#equipsublist-div{
	position: relative;
	bottom: 10px;
}
</style>
</head>
<script type="text/javascript" src="../js/EquipInfo.js?d=202008255"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.core.js"></script>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			设备信息管理
		</div>
		<div class="box-div" style="height: 700px;">
			<div style="float:left;display: inline-block; width: 200px; height: 100%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
				<%@include file="List.jsp" %>
			</div>
			<div id="equipsubinfo-div" style="margin-left: 210px;border: 1px solid;height: 100%;">
				<div>
					<%@include file="info.jsp" %>
				</div>
				<div id="equipsublist-div" style="height: 358px;padding: 10px;">
					<%@include file="SubInfo.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<form id="setparts-form" class="form-horizontal box-div" style="display: none;"><!-- 配件维护 -->
		<input type="hidden" id="equipid" name="equipid">
		<table class="order-table table table-bordered layeropen">
			<tr><td width="100px"></td><td width="50px"></td><td width="100px"></td><td width="100px"></td><td></td>
			</tr>
			<tr>
				<th>设备名称</th><td id="equipname" colspan="2" width="200px;"></td>
				<th>所属设备</th><td id="parantequip"></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="input-group input-group-sm" style="width: 180px;">
			            <input type="text" class="form-control" style="width: 150px;" placeholder="输入配件名称搜索">
			            <span class="input-group-addon point btn"><i class="layui-icon" style="font-size: 12px;">&#xe615;</i></span>
			        </div>
				</td>
				<td colspan="3" style="text-align: right;">
					<button class="btn btn-info btn-sm">新增自定义配件</button>
				</td>
			</tr>
			<tr height="428px">
				<td colspan="2">
					<ul id="equipsparts-tree" style="text-align: left;font-size: 18px;" class="ztree">
					</ul>
				</td>
				<td colspan="3">
					<table class="order-table table table-bordered layeropen">
						<tr>
							<th>配件类型</th>
							<th>名称</th>
							<th>规格</th>
							<th>数量</th>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>