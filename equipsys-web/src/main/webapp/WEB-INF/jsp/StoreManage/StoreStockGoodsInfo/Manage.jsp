<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
</head>
<script type="text/javascript" src="../js/StoreStockGoodsInfo.js?d=202008231"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.core.js?d=202006028"></script>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			物料库存信息管理
		</div>
		<div class="box-div" style="height: 700px;">
			<div style="float:left;display: inline-block; width: 160px; height: 100%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
				<%@include file="List.jsp" %>
			</div>
			<div id="stockgoodsInfo-div" style="margin-left: 170px;border: 1px solid;height: 100%;">
				<div>
					<%@include file="Info.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<form id="storegoods-form" class="form-horizontal box-div" data-type="0" style="display: none;"><!-- 物料信息 -->
		<input type="hidden" id="storegoods-id" name="goodsid">
		<input type="hidden" id="goodstype-id" name="typeid">
		<table id="storegoods-table" class="table table-bordered layeropen">
			<tr>
				<th width="100px;">物料名称</th><td width="200px"><input id="goodsname" name="goodsname" class="form-control"></td>
				<th width="100px;">物料类型</th><td><input class="form-control point" id="goodstypename" readonly placeholder="点击选择物料类型" onclick="showGoodsType()"></td>
			</tr>
			<tr>
				<th>备注</th><td colspan="3"><textarea class="form-control" rows="2" style="resize: none;" id="remarks" name="remarks" placeholder="填写物料说明"></textarea></td>
			</tr>
		</table>
	</form>
	<form id="goodsmodelnumberinfo-form" class="form-horizontal box-div" style="display: none;"><!-- 物料规格信息 -->
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="goods-id" name="goodsid">
		<input type="hidden" id="storeid" name="storeid">
		<table class="order-table table table-bordered layeropen">
			<tbody>
				<tr>
					<th width="80px"><label class="control-label">物料名称</label></th>
					<td>
						<label class="control-label" id="stockgoodsname"></label>
					</td>
					<th width="80px"><label class="control-label">物料类型</label></th>
					<td>
						<label class="control-label" id="goodstypename"></label>
					</td>
				</tr>
				<tr>
					<th><label class="control-label">型号</label></th>
					<td><input class="needing form-control" name="modelname"></td>
					<th><label class="control-label">库存仓位</label></th>
					<td><input class="needing form-control point" id="storename" readonly onclick="setstore()"></td>
				</tr>
				<tr>
					<th><label class="control-label">单位</label></th>
					<td><input class="form-control" name="unit"></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th><label class="control-label">备注</label>
					<td colspan="3">
						<textarea rows="2" class="form-control" style="resize:none;" name="remarks"></textarea>
					</td>
			</tbody>
		</table>
	</form>
	<form id="ChangeStorage-form" class="form-horizontal box-div" style="display: none;">
		<input type="hidden" id="modelid" name="modelid">
		<input type="hidden" id="employeeid" name="employeeid">
		<input type="hidden" id="deptid" name="deptid">
		<table class="order-table table table-bordered layeropen">
			<tbody>
				<tr>
					<th width="88px"><label class="control-label">物料名称</label></th>
					<td width="150px">
						<label class="control-label" id="goodsname"></label>
					</td>
					<th width="88px"><label class="control-label">物料类型</label></th>
					<td>
						<label class="control-label" id="goodstype"></label>
					</td>
				</tr>
				<tr>
					<th><label class="control-label">物料规格</label></th>
					<td>
						<label class="control-label" id="mnname"></label>
					</td>
					<th><label class="control-label" id="changedate">时间</label></th>
					<td>
						<input class="layui-input form-control point needing layedate" placeholder="yyyy-MM-dd" readonly name="changedate">
					</td>
				</tr>
				<tr>
					<th><label class="control-label" id="changetype">入库类型</label></th>
					<td>
						<select id="changetype-select" name="changetype" class="form-control">
							
						</select>
					</td>
					<th><label class="control-label">数量</label></th>
					<td>
						<div class="input-group">
							<input id="quantity" name="quantity" class="form-control" value="" type="number">
				            <span class="input-group-addon" id="unit">.00</span>
				        </div>
					</td>
				</tr>
				<tr class="income">
					<th><label class="control-label">单价（￥）</label></th>
					<td>
						<input id="price" name="price" class="form-control" value="0.00">
					</td>
					<th><label class="control-label">总价（￥）</label></th>
					<td><input class="form-control point" id="total" readonly value="0.00"></td>
				</tr>
				<tr class="outcome">
					<th>
						<label class="control-label">领料人</label>
					</th>
					<td>
						<input id="employeename" class="form-control point" onclick="showUnPostEmpTree()" readonly placeholder="点击选择领料人">
					</td>
					<th><label class="control-label">领料部门</label></th>
					<td>
						<input id="deptname" class="form-control" readonly>
					</td>
				</tr>
				<tr class="outcome">
					<th><label class="control-label">用途</label></th>
					<td colspan="3"><input class="form-control" name="use"></td>
				</tr>
				<tr class="income">
					<th><label class="control-label">供应商</label></th>
					<td colspan="3"><input class="form-control" name="supplier"></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<textarea rows="2" class="form-control" style="resize: none;" name="remarks"></textarea>
					</td>
				</tr>				
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
layui.use('laydate', function(){
  	var laydate = layui.laydate;
	$(".layedate").each(function(){
	  	laydate.render({
	  	elem: this,
	  	format: 'yyyy-MM-dd',
	  	value:new Date()
		});		
	})
})
$(function(){
	getStockGoodsTree("#stockgoods-tree");
})
</script>
</html>