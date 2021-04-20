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
<link href="../plugins/simplebar/css/simplebar.css" rel="stylesheet" />	<!--滚动轴插件-->
<link href="../plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />	<!--滚动轴插件-->
<link href="../plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />	<!--左侧菜单插件-->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
<!-- Icons CSS -->
<link rel="stylesheet" href="../css/icons.css" />
<link href="../css/System.css?d=202104133" rel="stylesheet"> <!-- 主体样式 -->
</head>
<body class="bg-theme bg-theme1">	<!-- 在body中设置css属性实现动态背景 -->
	<div class="wrapper"><!-- 封装界面 -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">	<!--左侧栏上部块级样式,实现左上角的系统标题,样式在app.css文件中设置样式-->
				<div class="">
					<img src="../static/img/logo-icon.png" class="logo-icon-2" alt="" />
				</div>
				<div>
					<h4 class="logo-text">焱鑫有色</h4>
				</div>
				<a href="javascript:;" class="toggle-btn ml-auto"> <i class="bx bx-menu"></i>
				</a>
			</div>
			<ul class="metismenu" id="menu">	<!--左侧手风琴折叠菜单实现，在app.css中设定样式-->
			</ul>
		</div>
		<header class="top-header">	<!-- 页面头部 -->
		</header>
		<div class="page-wrapper"> <!-- 页面内容主题 -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="page-breadcrumb d-none d-md-flex align-items-center mb-3">
						<div class="card-body">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="home" aria-controls="home" aria-selected="true">首页</a>
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								</li>
							</ul>
							<div class="tab-content p-3" id="myTabContent">
								<div class="tab-pane fade show active" id="home" role="home" aria-labelledby="home-tab">
									首页
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
<script src="../plugins/metismenu/js/metisMenu.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(function(){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:'../menu/getmenu.do',
			success:function(data){
				$.each(data,function(i,m){
					if(m.parentId=="0"){
						var innerHTML="<li><a class='has-arrow' href='javascript:;'><div class='parent-icon'><i class='bx bx-magnet'></i></div><div class='menu-title'>"+m.menuName+"</div></a><ul data-fid='"+m.id+"'></ul></li>"
						$(".metismenu").append(innerHTML)
					}else{
						var innerHTML="<li><a class='menu_a' data-fid='"+m.parentId+"' data-url='.."+m.url+"' data-id='"+m.id+"' data-name='"+m.menuName+"' onclick='openUrl(this)'><i class='bx bx-magnet'></i>"+m.menuName+"</a></li>";
						$("ul[data-fid='"+m.parentId+"']").append(innerHTML)
					}
				})
				$("#menu").metisMenu();
			}
		})
	});
});
function openUrl(e){
	if(checkpage(e)){
		if(checktablen()){
			var NavStr="<li class='nav-item' role='presentation'>"+
							"<a class='nav-link active' id='home-tab' data-toggle='tab' href='#"+$(e).attr("data-url")+"' role='"+$(e).attr("data-id")+"' aria-controls='home' aria-selected='true'>"+$(e).attr("data-name")+
							"<span class='closebtn' onclick='closeTab(this)'>&times;</span></a>"+
						"</li>";
			var DivStr="<div class='tab-pane fade show active' id='profile' role='"+$(e).attr("data-id")+"' aria-labelledby='profile-tab'></div>";
			$(".nav-item .nav-link").removeClass("active");
			$(".tab-content .tab-pane").removeClass("show active");
			$("#myTab").append(NavStr);
			$("#myTabContent").append(DivStr)
			$("#myTabContent .active").load($(e).attr("data-url"))
		}
	}else{
		var role=$(e).attr("data-id");
		$(".nav-item .nav-link").removeClass("active");
		$(".tab-content .tab-pane").removeClass("show active");
		$(".nav-item a[role="+role+"]").addClass("active");
		$(".tab-content .tab-pane[role="+role+"]").addClass("show active")
	}
}
$("#myTab").on("click",".nav-link",function(){	//监听选项卡点击切换
	if(!$(this).hasClass('active')){
		$(".nav-item .nav-link").removeClass("active");
		$(".tab-content .tab-pane").removeClass("show active");
		$(this).addClass("active");
		$(".tab-content .tab-pane[role="+$(this).attr("role")+"]").addClass("show active")
	}
})
function closeTab(e){
	var tab=$(e).parent().parent();
	var prevtab=$(tab).prev().find("a");
	if($(e).parent().hasClass('active')){
		$(prevtab).addClass("active");
		$(".tab-content .tab-pane[role="+$(prevtab).attr("role")+"]").addClass("show active");		
	}
	$(tab).remove();
	$(".tab-content .tab-pane[role="+$(e).parent().attr("role")+"]").remove();
}
function checkpage(e){
	var role=$(e).attr("data-id");
	if($(".tab-content .tab-pane[role="+role+"]").length>0){
		return false;
	}else{
		return true;
	}
	return 
}
function checktablen(){
	var n=$("#myTab").children().length
	if(n<=8){
		return true;
	}else{
		alert("打开的页面太多,请关闭一些再尝试打开新页面")
		return false;
	}
}
</script>
</html>