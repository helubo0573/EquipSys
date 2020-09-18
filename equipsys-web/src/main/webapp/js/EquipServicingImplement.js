/**
 * 
 */
function getEquipServicingImplementList(page){
	var equipname=$("#servicingImpManage-seach #search-equipname").val();
	var sappdate=$("#servicingImpManage-seach #search-sappdate").val();
	var eappdate=$("#servicingImpManage-seach #search-eappdate").val();
	var sbackfiredate=$("#servicingImpManage-seach #search-sbackfiredate").val();
	var ebackfiredate=$("#servicingImpManage-seach #search-ebackfiredate").val();
	var params={
			equipname:equipname,
			sappdate:sappdate,
			eappdate:eappdate,
			sbackfiredate:sbackfiredate,
			ebackfiredate:ebackfiredate,
			pageSize:12,
			current:page
		}
	$("#servicingImpManage-list").load("../EquipServicingImplement/search.do",params);
}

function showImplementInfo(type){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?'新增设备维修单':"修改设备维修单",
        area:[ '680px', '860px' ],
        btn:type!=2?['保存','关闭']:"",
        content: $("#servicingImpManage-info"),
        success: function (layero, index){
			if(type==0){
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
        			success:function(data){
	
					}
				})
			}else{
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
					data:"id="+id,
        			url:"../EquipServicingApplication/getApplication.do",
        			success:function(data){
						var appinfo=data.application;
						$("#servicingImpManage-info #id").val(appinfo.id);
						$("#servicingImpManage-info #dept").html(appinfo.proposDept);
						$("#servicingImpManage-info #proposer").html(appinfo.proposerName);
						$("#servicingImpManage-info #application_time").val(data.applicationTime);
						$("#servicingImpManage-info #backfire_time").val(data.backfireTime);
						$("#servicingImpManage-info #equip-name").val(appinfo.equipName);
						$("#servicingImpManage-info #equip-id").val(appinfo.equipId);
						$("#servicingImpManage-info #modelnumber").html(appinfo.equipModelNumber);
						$("#servicingImpManage-info #location").html(appinfo.location);
						$("#servicingImpManage-info #remarks").val(appinfo.remarks);
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
				data:$("#servicingImpManage-info").serialize(),
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
        	$("#servicingImpManage-info").hide();        	
        }		
	})
}

function setServicingAppproposer(){
	
}

function setServicingEquip(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../equip/getequiptree.do",
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
			$("#servicingImpManage-info #equip-id").val(data.equip.id)
			$("#servicingImpManage-info #equip-name").val(data.equip.equipName);
			$("#servicingImpManage-info #modelnumber").html(data.equip.equipModelNumber);
			$("#servicingImpManage-info #location").html(data.equip.location);
			layer.close(layer.index)
		}
	})
}