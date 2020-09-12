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
<body>
	<table class="table table-hover table-bordered table-striped" id="contracttb" style="margin-bottom: 3px;">
		<tr>
			<th>申请时间</th>
			<th>设备名称</th>
			<th>故障时间</th>
			<th>部门</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty applicationList }">
				<c:forEach items="${applicationList }" var="application">
					<tr>
						<td><fmt:formatDate value="${application.applicationTime }" pattern="yyyy-MM-dd"/></td>
						<td><%-- ${application.equipName } --%></td>
						<td><fmt:formatDate value="${application.backfireTime }" pattern="yyyy-MM-dd"/></td>
						<td><%-- ${application.proposDept } --%></td>
						<td>${application.status }</td>
						<td></td>
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