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
		 	<button class="btn btn-info btn-xs left" id="equipsystem-manageparts" disabled onclick="showEquipParts()">批量配件维护</button>
		 	<button class="btn btn-info btn-xs left" id="custompart-btn" disabled onclick="showCustomPart()">添加自定义配件</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="equip:info:save">
			<button class="btn btn-info btn-xs" id="equipsystem-addequip" onclick="showEquipInfo(0)">新增设备</button>
			<button class="btn btn-warning btn-xs" id="equipsystem-updateequip" onclick="showEquipInfo(1)" disabled="disabled">修改设备</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="equip:info:delete">
			<button class="btn btn-danger btn-xs" id="equipsystem-deleteequip" onclick="deleteEquipInfo()" disabled="disabled">删除设备</button>
		</shiro:hasPermission>
	</div>
	<div id="search-div" style="padding: 3px;">
		<input type="hidden" id="search-modelid">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 10px;text-align: center;">
			配件信息
		</div>
		<div>
			<div id="partssearch-div" class="input-group col-lg-9">
				<span class="input-group-addon">配件类型</span>
	            <input type="text" class="form-control point" style="width: 150px;" placeholder="点击选择" readonly onclick="">
	            <span class="input-group-addon">配件名称</span>
	            <input type="text" class="form-control" style="width: 150px;"placeholder="输入配件名称" id="search-partsname">
	            <span class="input-group-addon">需求数量</span>
	            <input type="text" class="form-control" style="width: 150px;" placeholder="输入需求数量" id="search-quantity">
			</div>
			<button class="btn btn-info btn-ms" style="float: right;position: relative;bottom:0px;top:0px;margin-top: -30px;" onclick="searchParts()">查询</button>
		</div>
		<div id="subequip-list">
			<%@include file="PartsList.jsp" %>
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