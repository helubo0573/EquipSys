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
<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle!important;
}
</style>
</head>
<script type="text/javascript" src="../js/EquipServicingImplement.js?d=20201011192"></script>
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
	$(".searchdatetime").each(function(){
	  	laydate.render({
	  	elem: this,
	  	type: 'datetime',
	  	format: 'yyyy-MM-dd HH:mm:ss',
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
		<input type="hidden" id="id" name="id">	<!-- 维修单id -->
		<input type="hidden" id="proposer-hd" name="proposer">	<!-- 申请维修人id -->
		<input type="hidden" id="dept-id" name="deptid">	<!-- 维修部门id -->
		<input type="hidden" id="equip-id" name="equipid">	<!-- 维修设备id -->
		<input type="hidden" id="Transactor-id" name="Transactorid">	<!-- 维修组人员 -->
		<table class="table table-bordered layeropen">
			<tr>
				<td colspan="4" align="center" style="font-weight: bold;">
					设备故障信息
				</td>
			</tr>
			<tr>
				<th width="80px">申请时间</th>
				<td width="255px">
					<input class="layui-input form-control point needing layedate date-input" name="application_time" id="application_time" placeholder="yyyy-MM-dd" readonly data-name='申请时间'>
				</td>
				<th width="80px">故障时间</th>
				<td>
					<input class="layui-input form-control point needing layedate date-input" name="backfire_time" id="backfire_time" placeholder="yyyy-MM-dd" readonly data-name='故障发生时间'>
				</td>
			</tr>
			<tr>
				<th width="80px;">申请人</th>
				<td>
					<div class="input-group input-group-sm col-lg-12">
						<input class="form-control point needing" id="proposer" style="width: 100px" readonly onclick="setProposer()" placeholder="点击选择" data-name='申请人'>
				        <span id="dept" style="width: 140px" class="th input-group-addon"></span>
				    </div>
				</td>
				<th width="80px;">设备名称</th>
				<td>
					<input class="form-control point needing" data-parent="" id="equip-name" readonly onclick="setServicingEquip()" placeholder="点击选择设备" data-name='设备'>
				</td>
			</tr>
			<tr>				
				<th>设备型号</th><td><label id="modelnumber"></label></td>
				<th>所在地点</th><td><label id="location"></label></td>
			</tr>
			<!-- <tr>
				<th>故障简述</th>
				<td colspan="3" style="vertical-align: middle;">
					<textarea class="form-control" id="remarks" name="remarks" rows="1" style="resize:none;"></textarea>
				</td>
			</tr> -->
			<tr>
				<th colspan="4" align="center" style="font-weight: bold;">
					设备维修信息
				</th>
			</tr>
			<tr>
				<th>
					维修单位
				</th>
				<td>
					<input class="form-control point needing" data-parent="" id="op-dept" readonly onclick="ServicingDept()" placeholder="点击选择维修单位" data-name='维修单位'>
				</td>
				<th>维修时间</th>
				<td>
					<div class="input-group input-group-sm col-lg-12">
				        <input class="layui-input form-control point layedate searchdatetime needing" id="search-sbackfiredate" style="width: 140px" name="SvrStartTime" id="application_time" placeholder="开始时间" readonly data-name='维修开始时间'>
				        <span class="th input-group-addon">-</span>
				        <input class="layui-input form-control point layedate searchdatetime needing" id="search-ebackfiredate" style="width: 140px" name="SvrEndTime" id="application_time" placeholder="结束时间" readonly data-name='维修结束时间'>
			       </div>
				</td>
			</tr>
			<tr>
				<th>维修人员</th>
				<td colspan="3"><input class="form-control point needing" id="setTransactor" readonly onclick="setTransactorInfo()" placeholder="点击选择维修组人员" data-name='维修人员'></td>
			</tr>
			<tr>
				<th>故障描述</th><td colspan="3"><textarea id="FaultSketch" name="failureBewrite" class="form-control needing" style="resize:none;" rows="2" data-name='故障描述'></textarea></td>
			</tr>
			<tr>
				<th>故障分析</th><td colspan="3"><textarea id="FaultAnalyse" name="failureCause"  class="form-control needing" style="resize:none;" rows="2" data-name='开始时间'故障分析></textarea></td>
			</tr>
			<tr>
				<th style="vertical-align: middle;">维修情况及结果综述</th><td colspan="3"><textarea id="FaultResult" name="servicingCause" class="form-control needing" style="resize:none;" rows="3" data-name='维修情况及结果综述'></textarea></td>
			</tr>
			<tr>
				<th height="200px" style="text-align: center;">
					零配件及材料耗用表<br>
					<input type="button" value="添加" class="btn btn-info" style="width: 68px;margin-right: 0px" onclick="setConsumptionSpart()">
				</th>
				<td colspan="3">
					<div style="height: 198px;overflow-y:scroll">
						<table id="ConsumptionSpart" class="table table-bordered layeropen">
							<tr>
								<th width="108px">零配件类型</th>
								<th>名称</th>
								<th width="150px">规格</th>
								<th width="60px">数量</th>
								<th width="50px">单位</th>
								<th width="80px">操作</th>
							</tr>						
						</table>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<form id="setConsumptionSpart-form" class="form-horizontal box-div" style="display: none;"><!-- 新增零配件 -->
		<input type="hidden" id="equipid" name="equipid">
		<table class="order-table table table-bordered layeropen">
			<tr>
				<th width="100px">设备名称</th><td id="equipname" colspan="2" width="150px;"></td>
				<th width="100px">设备型号</th><td id="equipmodel" width="380px"></td>
			</tr>
			<tr>
				<td colspan="2" width="150px">
					<div class="input-group input-group-sm" style="width: 160px;">
			            <input type="text" id="partstree-search" class="form-control" style="width: 130px;" placeholder="输入配件名称搜索">
			            <span class="input-group-addon point btn" id="sreach-btn">
			            	<i class="layui-icon" style="font-size: 12px;">&#xe615;</i>
			            </span>
			        </div>
				</td>
				<td colspan="3" rowspan="2">
					<div style="height: 458px;overflow-y:scroll;">
						<table  id="partslist-table" class="order-table table table-bordered layeropen" style="margin: 0px">
							<tr><th width="110px">配件类型</th><th width="175px">名称</th><th>规格</th><th width="70px">数量</th><th width="48px">单位</th><th width="48px">操作</th></tr>
						</table>
					</div>
				</td>
			</tr>
			<tr height="428px">
				<td colspan="2">
					<ul id="ConsumptionSpart-tree" style="text-align: left;font-size: 18px;height: 412px;overflow-y: scroll;" class="ztree">
					</ul>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>