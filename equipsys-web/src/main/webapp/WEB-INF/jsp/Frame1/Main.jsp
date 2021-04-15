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
<link href="../css/System.css?d=202104132" rel="stylesheet"> <!-- 主体样式 -->
<link href="../plugins/simplebar/css/simplebar.css" rel="stylesheet" />	<!--滚动轴插件-->
<link href="../plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />	<!--滚动轴插件-->
<link href="../plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />	<!--左侧菜单插件-->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
<!-- Icons CSS -->
<link rel="stylesheet" href="../css/icons.css" />
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
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
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
						//var innerHTML="<dl class='menu_dl' data-fid='"+m.id+"'><dt class='"+m.iconCode+"'>"+m.menuName+"<img src='${pageContext.request.contextPath}/static/img/menu_click.png'></dt></dl>";
						var innerHTML="<li><a class='has-arrow' href='javascript:;'><div class='parent-icon'><i class='bx bx-magnet'></i></div><div class='menu-title'>"+m.menuName+"</div></a><ul data-fid='"+m.id+"'></ul></li>"
						$(".metismenu").append(innerHTML)
					}else{
						var innerHTML="<li><a class='menu_a' data-fid='"+m.parentId+"' data-url='.."+m.url+"' data-id='"+m.id+"'><i class='bx bx-magnet'></i>"+m.menuName+"</a></li>";
						$("ul[data-fid='"+m.parentId+"']").append(innerHTML)
					}
				})
				$("#menu").metisMenu();
				/* layui.use('element', function(){
					var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
					element.on('nav(menu)', function(elem){//监听导航点击
						var fid=$(elem).attr("data-fid");
						if(fid>0){
							var id=$(elem).attr("data-id");
							if(checkpage(element,id)){
								if(checktablen()){
									var title=elem.text();
									var url=$(elem).attr("data-url");
									$("#templdiv").load(url,'',function(response){
										element.tabAdd('tab-content', {
									    	title:title,//用于演示
									        content:response,
									        id: id //实际使用一般是规定好的id，这里以时间戳模拟下
									    })
									    element.tabChange('tab-content', id);
										$("#templdiv").html("")
									 })
								}
							}
						}
					})
				}) */
				
			}
		})
	});
});
/* function checkpage(element,id){
	if($("li[lay-id='"+id+"']").length>0){
		element.tabChange('tab-content',id);
		return false;
	}else{
		return true;
	}
	return 
}
function checktablen(){
	var n=$("#tab-ul li").length
	if(n<=7){
		return true;
	}else{
		layer.msg("打开的页面太多,请关闭一些再尝试打开新页面")
		return false;
	}
} */
</script>
<script src="../plugins/metismenu/js/metisMenu.min.js"></script>
</html>