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
			<th width="150px">用户名</th>
			<th width="80px;">所属员工</th>
			<th width="120px">用户状态</th>
			<th width="120px">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty userlist }">
				<c:forEach items="${userlist }" var="user" >
					<tr>
						<td>${user.username }</td>
						<td>${user.empname }</td>
						<td><c:if test="${user.staus ==1}">正常</c:if><c:if test="${user.staus ==2}">锁定</c:if></td>
						<td>
							<shiro:hasPermission name="sys:user:save">
								<i class="layui-icon point" title="修改用户" onclick="showSysUserInfo('1','${user.id}')">&#xe642;</i>&nbsp;&nbsp;
							</shiro:hasPermission>
							<shiro:hasPermission name="sys:user:manage">
								<i class="layui-icon point" title="删除用户" onclick="deleteSysUser('${user.id}','${user.empname }')">&#xe640;</i>&nbsp;&nbsp;							
								<i class="layui-icon point" title="重置密码" onclick="resetPassword('${user.id}')">&#xe9aa;</i>&nbsp;&nbsp;
								<i class="layui-icon point" title="用户角色维护" onclick="setUserRole('${user.id}')">&#xe770;</i>&nbsp;&nbsp;
								<i class="layui-icon point" title='<c:if test="${user.staus ==1}">锁定用户</c:if><c:if test="${user.staus ==2}">解锁用户</c:if>' onclick="clockUser('${user.id}','${user.staus}')">&#xe673;</i>
							</shiro:hasPermission>
							<shiro:hasPermission name="sys:admin">
								<i class="layui-icon point" title='<c:if test="${user.isadmin==0}">设为系统管理员</c:if><c:if test="${user.isadmin==1}">解除系统管理员</c:if>' onclick="tobeadmin('${user.id}','${user.isadmin}')">
									<c:if test="${user.isadmin==0}">&#xe67b;</c:if>
									<c:if test="${user.isadmin==1}">&#xe67a;</c:if>
								</i>
							</shiro:hasPermission>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4">未查询到任何数据</td></tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div style="vertical-align:top;float: right;padding-top: 0px;margin-top: 0px;">
		${page}
	</div>
</body>
</html>