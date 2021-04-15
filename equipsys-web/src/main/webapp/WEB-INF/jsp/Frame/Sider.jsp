<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="menu_div">
		<ul class="layui-nav layui-nav-tree layui-inline menu_div" lay-filter="menu">
			
		</ul>
	</div>
	<div id="templdiv" hidden="true"></div>
</body>
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
						var innerHTML="<li class='layui-nav-item'><a>"+m.menuName+"</a><dl class='layui-nav-child' data-fid='"+m.id+"'></dl></li>"
						$(".layui-nav").append(innerHTML)
					}else{
						var innerHTML="<dd><a class='menu_a' data-fid='"+m.parentId+"' data-url='.."+m.url+"' data-id='"+m.id+"'>"+m.menuName+"</a></dd>";
						$("dl[data-fid='"+m.parentId+"']").append(innerHTML)
					}
				})
				layui.use('element', function(){
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
				})
			}
		})
	});
});
function checkpage(element,id){
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
}
</script>
</html>