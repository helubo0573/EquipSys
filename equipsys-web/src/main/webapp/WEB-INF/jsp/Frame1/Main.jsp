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
									<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								</li>
								<li class="nav-item" role="presentation">
									<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile
										<span class="closebtn" onclick="this.parentElement.style.display='none'">&times;</span>
									</a>
								</li>
								<li class="nav-item" role="presentation">
									<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
								</li>
							</ul>
							<div class="tab-content p-3" id="myTabContent">
								<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</div>
								<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</div>
								<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
						var innerHTML="<li><a class='menu_a' data-fid='"+m.parentId+"' data-url='.."+m.url+"' data-id='"+m.id+"' onclick='openUrl(this)'><i class='bx bx-magnet'></i>"+m.menuName+"</a></li>";
						$("ul[data-fid='"+m.parentId+"']").append(innerHTML)
					}
				})
				$("#menu").metisMenu();
			}
		})
	});
});
function openUrl(e){
	var NavStr="<li class='nav-item' role='presentation'>"+
					"<a class='nav-link' id='home-tab' data-toggle='tab' href='#"+$(e).attr("data-url")+"' role='tab' aria-controls='home' aria-selected='true'>Home"+
					"<span class='closebtn' onclick='this.parentElement.style.display=\"none\"'>&times;</span></a>"+
					
				"</li>";
	var DivStr="<div class='tab-pane fade' id='profile' role='tabpanel' aria-labelledby='profile-tab'>"+$(e).attr("data-url")+"</div>";
	$("#myTab").append(NavStr);
	$("#myTabContent").append(DivStr)	
}
</script>
<script src="../plugins/metismenu/js/metisMenu.min.js"></script>
</html>