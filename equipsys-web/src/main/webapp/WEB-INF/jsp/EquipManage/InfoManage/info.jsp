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
#equipinfo-table tr{
	height: 39.15px;
}
</style>
</head>
<body>
	<div style="padding: 5px 10px 0px 10px;	text-align: center;">
		<div class="h5" style="border-bottom: 60%;margin-bottom: 10px;">
			设备信息
		</div>
		<div id="equip-info" class="info-div">
			<input type="hidden" id="equipid-hd">
			<table id="equipinfo-table" class="table table-bordered">
				<tr>
					<th width="88px;">设备名称</th><td width="200px"><label id="equipname-label"></label></td>
					<th width="88px;">设备等级</th><td width="200px"><label id="equiplevel-label"></label></td>
					<th width="88px;">所属设备</th><td><label id="equipparent-label"></label></td>
				</tr>
				<tr>
					<th>设备编号</th><td><label id="equipcode-label"></label></td>
					<th>启用日期</th><td><label id="equipenabledate-label"></label></td>
					<th>设备型号</th><td></td>
				</tr>
				<tr>
					<th>所属部门</th><td><label id="equipdept-label"></label></td>
					<th>所在位置</th><td><label id="equiplocation-label"></label></td>
					<th rowspan="2" style="vertical-align: middle;">供应商</th><td><label id="equipsupplier-label"></label></td>
				</tr>
				<tr>
					<th>操作员</th><td><label id="equipop-label" style="text-align: left;"></label></td>
					<th>维修员</th><td><label id="equipmp-label" style="text-align: left;"></label></td>
					<td></td>
				</tr>
				<tr>
					<th>备注</th><td colspan="5"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>