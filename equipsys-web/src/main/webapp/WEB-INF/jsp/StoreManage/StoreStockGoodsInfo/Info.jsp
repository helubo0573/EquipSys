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
	<div style="padding: 5px 10px 0px 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 10px;">
			库存物料信息
		</div>
		<div id="storegoods-info" class="info-div" style="margin-bottom: 10px;">
			<table id="storegoodsinfo-table" class="table table-bordered">
			<tr>
				<th width="100px;">物料名称</th><td width="200px"><label id="goodsname-label" class="col-lg-12" style="height: 18px;"></label></td>
				<th width="100px;">物料类型</th><td><label class="col-lg-12" id="goodstypename-label" style="height: 18px;"></label></td>
			</tr>
			<tr>
				<th>备注</th><td colspan="3"><label style="height: 36px;text-align: left;" class="col-lg-12" id="remarks-label"></label></td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="right">
						<shiro:hasPermission name="store:goodsinfo:save">
							<button class="btn btn-info btn-xs" onclick="showStockGoodsInfo('0')">新增</button>
							<button class="btn btn-warning btn-xs" onclick="showStockGoodsInfo('1')">修改</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="store:goodsinfo:delete">
							<button class="btn btn-danger btn-xs" onclick="deleteStockGoodsInfo()">删除</button>
						</shiro:hasPermission>
					</div>
				</td>
			</tr>
		</table>
		</div>
	</div>
	<div style="padding: 5px 10px 0px 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 10px;">
			库存物料信息
		</div>
		<div style="border: white 1px solid;height: 430px;width: 100%;text-align: center;">
			<div class="btn-Rdiv" style="margin: 8px 0px">
				<shiro:hasPermission name="store:goodsmodelnumber:save">
					<button class="btn btn-info btn-xs" id="addmodelnumber" onclick="showGoodsModelNumberInfo(0)" disabled>新增物料规格</button>
				</shiro:hasPermission>
			</div>
			<div id="modelnumber-list" style="width: 98%;text-align: center;margin-left: 1%">
				<%@include file="subList.jsp" %>
			</div>
		</div>
	</div>
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
</script>
</html>