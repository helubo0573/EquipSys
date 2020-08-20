<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
</head>
<script type="text/javascript" src="../js/DeptManage.js?d=202006271"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.excheck.js"></script>
<body>
	<div class="body-sdiv">
		<div class="title-div">
			机构信息管理
		</div>
		<div class="box-div" style="height: 600px;">
			<div style="float:left;display: inline-block; width: 200px; height: 100%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
				<%@include file="List.jsp" %>
			</div>
			<div style="margin-left: 210px;border: 1px solid;height: 100%;">
				<%@include file="Info.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>