<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<table class="table table-hover table-bordered table-striped" id="contracttb" style="margin-bottom: 3px;">
		<tr>
			<th>型号</th>
			<th width="80px;">数量</th>
			<th width="80px">单位</th>
			<th width="160px">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty modellist }">
				<c:forEach items="${modellist }" var="model" >
					<tr>
						<td>${model.modelNumberName }</td>
						<td>${model.quantity }</td>
						<td>${model.unit }</td>
						<td>
							<shiro:hasPermission name="store:goodsmodelnumber:income">
								<i class="layui-icon point" title="入库" onclick="showChangeStorage(0,${model.id},'${model.modelNumberName }','${model.unit }')">&#xe61f;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="store:goodsmodelnumber:outcome">
								<i class="layui-icon point" title="出库" onclick="showChangeStorage(1,${model.id},'${model.modelNumberName }','${model.unit }')">&#xe616;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="store:goodsmodelnumber:stockdetailinfo">
								<i class="layui-icon point" title="查看库存明细" onclick="queryChangeStorageLog(${model.id})">&#xe60a;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="store:goodsmodelnumber:stockchangelog">
								<i class="layui-icon point" title="库存变更记录" onclick="queryChangeStorageLog(${model.id})">&#xe62d;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="store:goodsmodelnumber:save">
								<i class="layui-icon point" title="修改" onclick="showEmployeeInfo(${model.id})">&#xe642;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="store:goodsmodelnumber:delete">
								<i class="layui-icon point" title="删除" onclick="deleteEmployee(${model.id})">&#xe640;</i>
							</shiro:hasPermission>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<div style="vertical-align:top;float: right;padding-top: 0px;margin-top: 0px;">
		${page}
	</div>
</body>
</html>