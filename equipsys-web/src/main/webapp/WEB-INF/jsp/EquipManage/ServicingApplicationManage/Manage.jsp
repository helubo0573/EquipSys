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
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
</head>
<script type="text/javascript" src="../js/EquipServicingApplication.js?d=202009143"></script>
<script type="text/javascript">
layui.use('laydate', function(){
  	var laydate = layui.laydate;
/* 	$(".layedate").each(function(){
	  	laydate.render({
	  	elem: this,
	  	format: 'yyyy-MM-dd',
	  	value:new Date()
		});		
	}) */
	$(".searchdate").each(function(){
	  	laydate.render({
	  	elem: this,
	  	format: 'yyyy-MM-dd',
	  	value:""
		});		
	})
})
</script>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			设备维修申请管理
		</div>
		<div id="servicingAppManage-seach" class="box-div form-inline" style="overflow: hidden;text-align: left;">
			<div class="input-group input-group-sm col-lg-3">
		        <span class="th input-group-addon">设备名称</span>
		        <input class="form-control" id="search-equipname" placeholder="输入查询关键字">
		    </div>
		    <div class="input-group input-group-sm col-lg-4">
		        <span class="th input-group-addon">申请时间</span>
		        <input class="layui-input form-control point layedate searchdate" id="search-sappdate" style="width: 95px" name="application_time" id="application_time" placeholder="查询开始时间" readonly>
		        <span class="th input-group-addon">-</span>
		        <input class="layui-input form-control point layedate searchdate" id="search-eappdate" style="width: 95px" name="application_time" id="application_time" placeholder="查询结束时间" readonly>
		    </div>
		    <div class="input-group input-group-sm col-lg-4">
		        <span class="th input-group-addon">故障时间</span>
		        <input class="layui-input form-control point layedate searchdate" id="search-sbackfiredate" style="width: 95px" name="application_time" id="application_time" placeholder="查询开始时间" readonly>
		        <span class="th input-group-addon">-</span>
		        <input class="layui-input form-control point layedate searchdate" id="search-ebackfiredate" style="width: 95px" name="application_time" id="application_time" placeholder="查询结束时间" readonly>
		    </div>
		    <button class="search-btn btn btn-info right" style="height: 100%;" onclick="getEquipServicingApplicationList(1)">查询</button>
		</div>
		<div id="emp-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
				<button class="btn btn-success" onclick="showApplicationInfo('0')">新增设备维修申请</button>
		</div>
		<div id="servicingAppManage-list" class="box-div form-inline" style="height: 600px;">
			<%@include file="List.jsp" %>
		</div>
	</div>
	<form id="servicingAppManage-info" class="form-horizontal box-div" style="display: none;">
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="dept-id">
		<input type="hidden" id="equip-id" name="equipid">
		<table class="table table-bordered layeropen">
			<tr>
				<th width="80px;">申请部门</th><td><label id="dept"></label></td>
				<th width="80px;">申请人</th><td><label id="proposer"></label></td>
			</tr>
			<tr>
				<th>申请时间</th><td><input class="layui-input form-control point needing layedate" name="application_time" id="application_time" placeholder="yyyy-MM-dd" readonly></td>
				<th>故障时间</th><td><input class="layui-input form-control point needing layedate" name="backfire_time" id="backfire_time" placeholder="yyyy-MM-dd" readonly></td>
			</tr>
			<tr>
				<th>设备名称</th>
				<td colspan="3">
					<input class="form-control point" data-parent="" id="equip-name" readonly onclick="setServicingEquip()">
				</td>
			</tr>
			<tr>				
				<th>设备型号</th><td colspan="3"><label id="modelnumber"></label></td>
			</tr>
			<tr>
				<th>所在地点</th><td colspan="3"><label id="location"></label></td>
			</tr>
			<tr>
				<th>故障简述</th>
				<td colspan="3" style="vertical-align: middle;">
					<textarea class="form-control" id="remarks" name="remarks" rows="4" style="resize:none;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>