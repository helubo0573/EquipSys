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
	<div>
		<div id="stockgoods-search" class="input-group input-group-sm" style="width: 180px;">
            <input type="text" id="partstree-search" class="form-control" style="width: 150px;" placeholder="输入物料名称搜索">
            <span class="input-group-addon point btn" id="sreach-btn">
            	<i class="layui-icon" style="font-size: 12px;">&#xe615;</i>
            </span>
        </div>
		<ul id="stockgoods-tree" style="text-align: left;font-size: 18px;" class="ztree">
		</ul>
	</div>
</body>
</html>