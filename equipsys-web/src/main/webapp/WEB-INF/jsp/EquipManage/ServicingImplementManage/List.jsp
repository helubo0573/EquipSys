<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="table table-hover table-bordered table-striped" id="contracttb" style="margin-bottom: 3px;">
		<tr>
			<th width="120px">申请时间</th>
			<th width="120px">故障时间</th>
			<th>设备名称</th>
			<th width="80px">状态</th>
			<th width="120px">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty ServicingImplementList }">
				<c:forEach items="${ServicingImplementList }" var="ServicingImplement">
					<tr>
						<td><fmt:formatDate value="${ServicingImplement.appTime }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${ServicingImplement.backTime }" pattern="yyyy-MM-dd"/></td>
						<td>${ServicingImplement.equipName }</td>
						<td>
							<c:if test="${ServicingImplement.status==0 }">新增申请</c:if>
							<c:if test="${ServicingImplement.status==1 }">维修完成</c:if>
						</td>
						<td>
							<c:if test="${ServicingImplement.status==0 }">
								<shiro:hasPermission name="equip:implement:save">
									<i class="layui-icon point" title="修改申请单" onclick="showImplementInfo('1',${ServicingImplement.id})">&#xe642;</i>&nbsp;
								</shiro:hasPermission>
								<shiro:hasPermission name="equip:implement:delete">
									<i class="layui-icon point" title="删除申请单" onclick="deleteImplement('${ServicingImplement.id}')">&#xe640;</i>&nbsp;
								</shiro:hasPermission>
							</c:if>
							<i class="layui-icon point" title="查看详情" onclick="showImplementInfo('2',${ServicingImplement.id})">&#xe63c;</i>
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