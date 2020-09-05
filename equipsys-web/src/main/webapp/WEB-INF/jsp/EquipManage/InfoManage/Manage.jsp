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
<script type="text/javascript" src="../js/EquipInfo.js?d=202009052"></script>
<body>
	<div class="body-fdiv">
		<div class="title-div">
			设备信息管理
		</div>
		<div class="box-div" style="height: 700px;">
			<div style="float:left;display: inline-block; width: 240px; height: 100%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
				<%@include file="List.jsp" %>
			</div>
			<div id="equipsubinfo-div" style="margin-left: 250px;border: 1px solid;height: 100%;">
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
			<tr>
				<th width="100px">设备名称</th><td id="equipname" colspan="2" width="150px;"></td>
				<th width="100px">所属设备</th><td id="parantequip" width="380px"></td>
			</tr>
			<tr>
				<td colspan="2" width="150px">
					<div class="input-group input-group-sm" style="width: 180px;">
			            <input type="text" id="partstree-search" class="form-control" style="width: 150px;" placeholder="输入配件名称搜索">
			            <span class="input-group-addon point btn" id="sreach-btn">
			            	<i class="layui-icon" style="font-size: 12px;">&#xe615;</i>
			            </span>
			        </div>
				</td>
				<td colspan="3" rowspan="2">
					<div style="height: 458px;overflow-y:scroll;">
						<table  id="partslist-table" class="order-table table table-bordered layeropen" style="margin: 0px">
							<tr><th width="100px">配件类型</th><th width="145px">名称</th><th width="150px">规格</th><th width="70px">数量</th><th>移除</th></tr>
						</table>
					</div>
				</td>
			</tr>
			<tr height="428px">
				<td colspan="2">
					<ul id="equipsparts-tree" style="text-align: left;font-size: 18px;height: 412px;overflow-y: scroll;" class="ztree">
					</ul>
				</td>
				
			</tr>
		</table>
	</form>
	<form id="custompart-form" class="form-horizontal box-div" style="display: none;"><!-- 自定义配件 -->
		<input type="hidden" id="partsid" name="partsid">
		<input type="hidden" id="equipid" name="equipid">
		<input type="hidden" id="goodstypeid">
		<input type="hidden" id="model-id" name="modelid">
		<table class="order-table table table-bordered layeropen">
			<tr>
				<th width="120px">设备名称</th>
				<td width="200px" id="equip-name"></td>
			</tr>
			<tr>
				<th width="120px">配件类型</th>
				<td><input class="form-control point" id="goodstypename" readonly placeholder="点击选择配件类型"  onclick="PartshowGoodsType()"></td>
			</tr>
			<tr>
				<th>关联库存物料</th>
				<td><input class="form-control point" id="linkStockGoods" readonly placeholder="点击选择关联物料"  onclick="PartsetStockGoods()"></td>
			</tr>
			<tr>
				<th>配件名称</th>
				<td><input class="form-control needing" id="partname" name="partname"></td>
			</tr>
			<tr>
				<th>需求数量</th>
				<td><input class="form-control needing" id="quantity" name="quantity"></td>
			</tr>
		</table>
	</form>
</body>
</html>