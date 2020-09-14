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
						<td>${application.equipName }</td>
						<td><fmt:formatDate value="${application.backfireTime }" pattern="yyyy-MM-dd"/></td>
						<td>${application.proposDept }</td>
						<td>
							<c:if test="${application.status==0 }">新增申请</c:if>
							<c:if test="${application.status==1 }">维修中</c:if>
							<c:if test="${application.status==2 }">待验收</c:if>
							<c:if test="${application.status==3 }">验收未通过</c:if>
							<c:if test="${application.status==9 }">验收通过</c:if>
						</td>
						<td>
							<c:if test="${application.status==0 }">
								<i class="layui-icon point" title="修改申请单" onclick="showApplicationInfo('1','${application.id}')">&#xe642;</i>&nbsp;
								<i class="layui-icon point" title="撤销申请单" onclick="deleteApplication('${application.id}')">&#xe640;</i>&nbsp;
							</c:if>
							<c:if test="${application.status==2 }">
								<i class="layui-icon point" title="维修验收">&#xe6b2;</i>&nbsp;
							</c:if>
							<i class="layui-icon point" title="查看详情" onclick="showApplicationInfo('2','${application.id}')">&#xe63c;</i>
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