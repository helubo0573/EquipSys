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
	<select size="28" style="width: 100%;color: black;">
		<c:forEach items="${typelist }" var="type">
			<option value="${type.id }" data-order="${type.typeOrder }" data-quickcode="${type.quickCode }" data-remarks="${type.remarks }" onclick="getGoodsTypeInfo(this)">${type.typeName }</option>
		</c:forEach>
	</select>
</body>
</html>