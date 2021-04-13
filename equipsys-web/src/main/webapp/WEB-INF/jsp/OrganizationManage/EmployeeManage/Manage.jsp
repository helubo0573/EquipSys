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
<script type="text/javascript" src="../js/EmployeeInfo.js?d=202008231"></script>
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
})
</script>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			员工管理
		</div>
		<div id="emp-seachdiv" class="box-div form-inline" style="overflow: hidden;text-align: left;">
			<%@include file="search.jsp" %>
		</div>
		<div id="emp-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
			<shiro:hasPermission name="org:employee:save">
				<button class="btn btn-success" onclick="showEmployeeInfo()">新员工入职</button>
			</shiro:hasPermission>
			<!--<button></button>
			<button></button> -->
		</div>
		<div id="emp-listdiv" class="box-div form-inline" style="height: 600px;">
			<%@include file="List.jsp" %>
		</div>
		<form id="empinfo-form" class="form-horizontal box-div" style="display: none;">
			<input name="empinfo-id" id="empinfo-id" type="hidden">
			<input name="empinfo-deptid" id="empinfo-deptid" type="hidden">
			<input name="empinfo-postid" id="empinfo-postid" type="hidden">
			<table class="table table-bordered layeropen">
				<tbody>
					<tr>
						<th><label class="control-label">姓&nbsp;&nbsp;名</label></th>
						<td>
							<input class="form-control needing" name="empinfo-name" id="empinfo-name">
						</td>
						<th><label class="control-label">性&nbsp;&nbsp;别</label></th>
						<td>
							<div align="left">
								<label class="radio-inline" for="empsex-boy">
									<input type="radio" value="0" name="empinfo-sex" id="empsex-boy" style="bottom: 2px;">-男
								</label>
								<label class="radio-inline" for="empsex-girl">
									<input type="radio" value="1" name="empinfo-sex" id="empsex-girl" style="bottom: 2px;">-女
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<th><label class="control-label">部&nbsp;&nbsp;门</label></th>
						<td>
							<input class="form-control needing" name="empinfo-dept" id="empinfo-dept" data-dept="" readonly placeholder="点击岗位框选择部门及岗位信息">
						</td>
						<th><label class="control-label">岗&nbsp;&nbsp;位</label></th>
						<td>
							<input class="form-control needing point" name="empinfo-post" id="empinfo-post" data-post="" readonly onclick="showdepttree()" placeholder="点此选择部门岗位信息">
						</td>
					</tr>
					<tr>
						<th><label class="control-label">电&nbsp;&nbsp;话</label></th>
						<td>
							<input class="form-control needing" name="empinfo-mobil" id="empinfo-mobil">
						</td>
						<th><label class="control-label">入职时间</label></th>
						<td>
							<input class="layui-input form-control point layedate date-input" name="empinfo-indate" id="empinfo-indate" placeholder="yyyy-MM-dd" readonly>
						</td>
					</tr>
					<tr>
						<th><label class="control-label">备&nbsp;&nbsp;注</label></th>
						<td colspan="3"><textarea id="empinfo-remarks" name="empinfo-remarks" class="form-control" rows="2" style="resize: none;"></textarea></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>