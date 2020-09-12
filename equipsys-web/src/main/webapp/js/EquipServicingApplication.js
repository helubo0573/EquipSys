/**
 * 
 */
function getEquipServicingApplicationList(page){
	var param={
		
			pageSize:12,
			current:page
		}
	$("#servicingAppManage-list").load("../EquipServicingApplication/search.do",param);
}
function showApplicationInfo(type){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?'新增设备维修申请':"修改设备维修申请",
        area:[ '540px', '460px' ],
        btn:['保存','关闭'],
        content: $("#servicingAppManage-info"),
        success: function (layero, index){
			if(type==0){
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
        			url:"../employee/getEmployeeBySession.do",
        			success:function(data){
						$("#servicingAppManage-info #dept").html(data.deptname);
						$("#servicingAppManage-info #proposer").html(data.empname);
						$("#servicingAppManage-info #dept-id").html(data.deptid);
					}
				})
			}else{
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
        			url:"../equip/getequipinfo.do",
        			success:function(data){
						$("#servicingAppManage-info #dept").html(data.deptname);
						$("#servicingAppManage-info #proposer").html(data.empName);
					}
				})
			}
		},
		yes:function(index){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../EquipServicingApplication/save.do",
				data:$("#servicingAppManage-info").serialize(),
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getEquipServicingApplicationList(1)
						clearApplicationInfo();
						layer.close(index);
					}
				}
			})
		},
		end: function(index, layero){
        	clearApplicationInfo();
        	$("#servicingAppManage-info").hide();        	
        }		
	})
}
function clearApplicationInfo(){
	$("#servicingAppManage-info textarea").val("");
	$("#servicingAppManage-info #equip-name").val("")
	$("#servicingAppManage-info label").html("");
}

function setServicingEquip(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../equip/getequiptree.do",
		data:"dept="+$("#servicingAppManage-info #dept-id").val(),
		success:function(data){
			var htmlstr="<div style='padding:10px'>"+
					"<div id='servicingequiptree-search' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='partstree-search' class='form-control' style='width: 150px;' placeholder='输入设备名称搜索'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='servicingequiptree' class='ztree' style='color: #555'></ul>"+
				"</div>"
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '300px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: htmlstr,
                success: function (layero, index) {
					var setting={
						callback:{
				        	onClick:setServicingEquipForTree
		        		}
					}
                	$.fn.zTree.init($("#servicingequiptree"), setting, JSON.parse(data.equiptree));
					fuzzySearch('servicingequiptree','#servicingequiptree-search #sreach-btn',null,true);
                }
			})
		}
	})
}

function setServicingEquipForTree(event, treeId, treeNode){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../equip/getequipinfo.do",
		data:"id="+treeNode.id,
		success:function(data){
			$("#servicingAppManage-info #equip-id").val(data.equip.id)
			$("#servicingAppManage-info #equip-name").val(data.equip.equipName);
			$("#servicingAppManage-info #modelnumber").html(data.equip.equipModelNumber);
			$("#servicingAppManage-info #location").html(data.equip.location);
			layer.close(layer.index)
		}
	})
}