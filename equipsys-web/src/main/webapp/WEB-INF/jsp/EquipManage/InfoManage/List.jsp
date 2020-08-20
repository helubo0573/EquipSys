<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#equipinfo-tree li a{
	color: #fff;
}
</style>
</head>
<body>
	<div>
		<ul id="equipinfo-tree" style="text-align: left;font-size: 18px;color: #fff" class="ztree maintree">
		</ul>
	</div>
</body>
<script type="text/javascript">
$(function(){
	getEquipTree("#equipinfo-tree");
})
</script>
</html>