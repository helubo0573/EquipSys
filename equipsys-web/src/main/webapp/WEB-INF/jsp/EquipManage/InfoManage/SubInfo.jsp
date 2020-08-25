<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="btn-Rdiv">
		<shiro:hasPermission name="equip:info:setparts">
		 	<button class="btn btn-info btn-xs left" id="equipsystem-manageparts" disabled onclick="showEquipParts()">配件维护</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="equip:info:save">
			<button class="btn btn-info btn-xs" id="equipsystem-addequip" onclick="showEquipInfo(0)">新增设备</button>
			<button class="btn btn-warning btn-xs" id="equipsystem-updateequip" onclick="showEquipInfo(1)" disabled="disabled">修改设备</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="equip:info:delete">
			<button class="btn btn-danger btn-xs" id="equipsystem-deleteequip" onclick="deleteEquipInfo()" disabled="disabled">删除设备</button>
		</shiro:hasPermission>
	</div>
	<div class="search-div" style="padding: 3px;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 10px;">
			配件信息
		</div>
		<div id="subequip-list">
			<%@include file="PartsList.jsp" %>
		</div>
	</div>
	<form id="equipinfo-form" class="form-horizontal box-div" style="display: none;">
		<input type="hidden" id="show-type">
		<table class="order-table table table-bordered layeropen">
			<tbody>
				<tr>
					<th><label class="control-label">设备名称</label></th>
					<td>
						<input class="form-control needing" id="subequip-name">
					</td>
					<th><label class="control-label">设备编号</label></th>
					<td>
						<input class="form-control needing" id="subequip-code">
					</td>
				</tr>
				<tr>
					<th><label class="control-label">设备级别</label></th>
					<td>
						<div class="needing">
							<label class="radio-inline" for="subequiplv-flagship">
								<input type="radio" value="0" name="subequip-level" id="subequiplv-flagship">-系统级
							</label>
							<label class="radio-inline" for="subequiplv-model">
								<input type="radio" value="1" name="subequip-level" id="subequiplv-model">-模组级
							</label>
							<label class="radio-inline" for="subequiplv-equip">
								<input type="radio" value="2" name="subequip-level" id="subequiplv-equip">-设备级
							</label>
						</div>
					</td>
					<th><label class="control-label">所属设备</label></th><td><input class="form-control point" data-parent="" id="subequip-parent" readonly onclick="showEquipparentTree()"></td>
				</tr>
				<tr>
					<th><label class="control-label">启用日期</label></th>
					<td>
						<input class="layui-input form-control point needing layedate" id="subequip-enabledate" placeholder="yyyy-MM-dd" readonly>
					</td>
					<th><label class="control-label">供应商</label></th><td><input class="form-control" id="subequip-supplier"></td>
				</tr>
				<tr>
					<th><label class="control-label">所属部门</label></th>
					<td>
						<select class="form-control point needing" id="subequip-attrdept">
							<option value="-1">选择所属部门</option>
							<c:forEach items="${deptlist }" var="dept">
								<option value="${dept.id }">${dept.deptName }</option>
							</c:forEach>
						</select>
					</td>
					<th><label class="control-label">所在位置</label></th><td><input class="form-control" id="subequip-location"></td>
				</tr>
				<tr>
					<th><label class="control-label">操作人员</label></th>
					<td colspan="3">
							<input data-emp="" class="form-control point" id="subequip-op" onclick="showEmpTree(0,this)" readonly>
					</td>
				</tr>
				<tr>
					<th><label class="control-label">维修人员</label></th>
					<td colspan="3">
							<input data-emp="" class="form-control point" id="subequip-mp" onclick="showEmpTree(1,this)" readonly>
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
</script>
</html>