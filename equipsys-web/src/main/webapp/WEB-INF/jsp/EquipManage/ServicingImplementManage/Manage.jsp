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
</head>
<script type="text/javascript" src="../js/EquipServicingImplement.js?d=202009181"></script>
<script type="text/javascript">
layui.use('laydate', function(){
  	var laydate = layui.laydate;
 	$(".date-input").each(function(){
	  	laydate.render({
	  	elem: this,
	  	format: 'yyyy-MM-dd',
	  	value:new Date()
		});		
	})
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
			设备维修管理
		</div>
		<div id="servicingImpManage-seach" class="box-div form-inline" style="overflow: hidden;text-align: left;">
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
		    <button class="search-btn btn btn-info right" style="height: 100%;" onclick="getEquipServicingImplementList(1)">查询</button>
		</div>
		<div id="eqimp-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
				<button class="btn btn-success" onclick="showImplementInfo('0')">新增设备维修单</button>
		</div>
		<div id="servicingImpManage-list" class="box-div form-inline" style="height: 600px;">
			<%@include file="List.jsp" %>
		</div>
	</div>
	<form id="servicingImpManage-info" class="form-horizontal box-div" style="display: none;">
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="dept-id">
		<input type="hidden" id="equip-id" name="equipid">
		<table class="table table-bordered layeropen">
			<tr>
				<th>申请时间</th><td><input class="layui-input form-control point needing layedate date-input" name="application_time" id="application_time" placeholder="yyyy-MM-dd" readonly></td>
				<th>故障时间</th><td><input class="layui-input form-control point needing layedate date-input" name="backfire_time" id="backfire_time" placeholder="yyyy-MM-dd" readonly></td>
			</tr>
			<tr>
				<th width="80px;">申请人</th>
				<td>
					<div class="input-group input-group-sm col-lg-12">
						<input class="form-control point" id="proposer" style="width: 120px" readonly onclick="setServicingAppproposer()" placeholder="点击选择">
				        <span id="dept" style="width: 140px" class="th input-group-addon"></span>
				    </div>
				</td>
				<th width="80px;">设备名称</th>
				<td>
					<input class="form-control point" data-parent="" id="equip-name" readonly onclick="setServicingEquip()">
				</td>
			</tr>
			<tr>				
				<th>设备型号</th><td><label id="modelnumber"></label></td>
				<th>所在地点</th><td><label id="location"></label></td>
			</tr>
			<tr>
				<td colspan="4">
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