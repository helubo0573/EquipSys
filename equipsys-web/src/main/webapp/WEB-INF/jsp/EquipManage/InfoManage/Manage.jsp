<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../plugins/zTree/css/zTreeStyle/zTreeStyle.css?d=202006021">
<style type="text/css">
#equipsublist-div{
	position: relative;
	bottom: 10px;
}
</style>
</head>
<script type="text/javascript" src="../js/EquipInfo.js?d=2020060164"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../plugins/zTree/js/jquery.ztree.excheck.js"></script>
<body>
	<div class="body-bdiv">
		<div class="title-div">
			设备信息管理
		</div>
		<div class="box-div" style="height: 700px;">
			<div style="float:left;display: inline-block; width: 200px; height: 100%; padding: 10px; border: 1px solid #ddd; overflow: auto;">
				<%@include file="List.jsp" %>
			</div>
			<div id="equipsubinfo-div" style="margin-left: 210px;border: 1px solid;height: 100%;">
				<div>
					<%@include file="info.jsp" %>
				</div>
				<div id="equipsublist-div" style="height: 358px;padding: 10px;">
					<%@include file="SubInfo.jsp" %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>