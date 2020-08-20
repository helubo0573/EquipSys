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
    <div class="input-group input-group-sm col-lg-3">
        <span class="th input-group-addon">用户名</span>
        <input class="form-control" id="username-search" placeholder="">
    </div>
	<div class="input-group input-group-sm col-lg-3">
        <span class="th input-group-addon">员工姓名</span>
        <input class="form-control" id="userempname-search" placeholder="">
    </div>
    <button class="search-btn btn btn-info right" style="height: 100%;width: 100px;" onclick="getUserPage(1)">查询</button>
</body>
</html>