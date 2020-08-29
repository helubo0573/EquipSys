<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<table class="table table-hover table-bordered table-striped" style="margin-bottom: 3px;">
		<tr>
			<th width="140px">配件类型</th>
			<th>配件名称</th>
			<!-- <th>配件规格</th> -->
			<th width="80px">需求数量</th>
			<th width="80px">库存</th>
			<th width="60px">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty partslist }">
				<c:forEach items="${partslist }" var="parts" >
					<tr>
						<td><c:if test="${empty parts.typeName}">自定义配件</c:if><c:if test="${not empty parts.typeName }">${parts.typeName }</c:if></td>
						<td>${parts.partsName }</td>
						<%-- <td>${parts.modelName }</td> --%>
						<td>${parts.quantity }</td>
						<td>${parts.storeQuantity }</td>
						<td>
							<shiro:hasPermission name="equip:info:setparts">
								<i class="layui-icon point" title="修改" onclick="updatePart('${parts.id}','${parts.goodstypeId }','${parts.typeName}','${parts.partsName }','${parts.quantity }','${parts.goodsModelId }')">&#xe642;</i>&nbsp;
								<i class="layui-icon point" title="删除" onclick="deletepart('${parts.id}')">&#xe640;</i>
							</shiro:hasPermission>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td align="center" colspan="6">设备还未添加配件信息</td></tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div style="vertical-align:top;float: right;padding-top: 0px;margin-top: 0px;">
		${page}
	</div>
</body>
</html>