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
			<th width="150px">员工姓名</th>
			<th width="80px;">性别</th>
			<th width="120px">部门</th>
			<th width="120px">岗位</th>
			<th>电话号码</th>
			<th width="120px">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty employee }">
				<c:forEach items="${employee }" var="employee" >
					<tr>
						<td>${employee.empName }</td>
						<td><c:if test="${employee.empSex==0 }">男</c:if><c:if test="${employee.empSex==1 }">女</c:if></td>
						<td>${employee.deptname }</td>
						<td>${employee.postname }</td>
						<td>${employee.empMobil }</td>
						<td>
							<shiro:hasPermission name="org:employee:save">
								<i class="layui-icon point" title="修改" onclick="showEmployeeInfo(1,'${employee.id}')">&#xe642;</i>&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="org:employee:delete">
								<i class="layui-icon point" title="删除" onclick="deleteEmployee('${employee.id}','${employee.empName }')">&#xe640;</i>
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