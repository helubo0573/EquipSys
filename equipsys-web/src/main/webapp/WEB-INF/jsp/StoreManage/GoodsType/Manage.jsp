<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript" src="../js/GoodsType.js?d=202007174"></script>
<body>
	<div class="body-sdiv">
		<div class="title-div">
			物料类型维护
		</div>
		<div class="box-div" style="height: 580px;">
			<div id="GoodsTypeList-div" style="float:left;display: inline-block; width: 200px; height: 100%; padding: 10px; border: 1px solid #ddd;">
				<%@include file="List.jsp" %>
			</div>
			<div style="margin-left: 210px;border: 1px solid;height: 100%;">
				<%@include file="Info.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>