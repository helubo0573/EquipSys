/**获取设备维修单列表 */
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
/**显示维修单 */
function showImplementInfo(type){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?'新增设备维修单':"修改设备维修单",
        area:[ '760px', '860px' ],
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
        	clearImplementInfo();
        	$("#servicingImpManage-info").hide();        	
        }		
	})
}
/**清空维修单信息 */
function clearImplementInfo(){
	
}/**设置设备维修申请人 */
function setProposer(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../employee/getemployeetree.do",
		data:"type=1",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="proposertree" class="ztree"></ul>',
                btn:['确定','取消'],
                success: function (layero, index) {
                	var setting = {
            			callback:{
    			        	onClick:setProposerInfo
    			        }
                	};
                	$.fn.zTree.init($("#proposertree"), setting, JSON.parse(data.emptree));
                }
			})
			
		}
	})
}
/**设置申请人信息 */
function setProposerInfo(event, treeId, treeNode){
	if(treeNode.isemp){
		$("#servicingImpManage-info #proposer-hd").val(treeNode.id)
		$("#servicingImpManage-info #proposer").val(treeNode.name)
		var pnode=treeNode.getParentNode().getParentNode()
		console.log(pnode.name)
		$("#servicingImpManage-info #dept").html("部门:"+pnode.name)
		layer.close(layer.index)
	}
}
/**设置申请维修的设备 */
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
/**设置维修的设备信息 */
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
/**选择维修单位 */
function ServicingDept(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../dept/getparentdepttree.do",
		success:function(data){
			var htmlstr="<div style='padding:10px'>"+
					"<div id='ServicingDepttree-search' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='partstree-search' class='form-control' style='width: 150px;' placeholder='输入设备名称搜索'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='ServicingDepttree' class='ztree' style='color: #555'></ul>"+
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
				        	onClick:setServicingDept
		        		}
					}
                	$.fn.zTree.init($("#ServicingDepttree"), setting, JSON.parse(data.parentdepttree));
					fuzzySearch('ServicingDepttree','#ServicingDepttree-search #sreach-btn',null,true);
                }
			})
		}
	})
}
/**设置维修单位 */
function setServicingDept(event, treeId, treeNode){
	$("#servicingImpManage-info #op-dept").val(treeNode.name)
	$("#servicingImpManage-info #dept-id").val(treeNode.id)
	layer.close(layer.index)
}

/**显示维修组人员选择窗 */
function setTransactorInfo(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../employee/getemployeetree.do",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="Transactortree" class="ztree"></ul>',
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	var setting = {
            				check: {
            					enable: true,
            				    chkStyle : "checkbox"
            				}
            		    };
                	var ztree=$.fn.zTree.init($("#Transactortree"), setting, JSON.parse(data.emptree));
                	var ids=$("#servicingImpManage-info #Transactor-id").val().split(',');
                	var names=$("#servicingImpManage-info #setTransactor").val().split(',');
                	for(var i = 0;i<ids.length;i++){
                		var nodeList=ztree.getNodesByParam("name", names[i], null);
                		for( var n=0;n<nodeList.length; n++ ){
                			if(nodeList[n].id==ids[i]){
                				ztree.checkNode(nodeList[n],true,true);
                			}
                		}
                	}
                },
                yes:function(index){
                	var zTree = $.fn.zTree.getZTreeObj("Transactortree");
                	var nodes = zTree.getCheckedNodes(true),
                	v = "";
                	k="";
        			for (var i=0, l=nodes.length; i<l; i++) {
        				v += nodes[i].name + ",";
        				k += nodes[i].id + ",";
        			}
        			if (v.length > 0 ) v = v.substring(0, v.length-1);
        			if (k.length > 0 ) k = k.substring(0, k.length-1);
                	$("#servicingImpManage-info #setTransactor").val(v);
                	$("#servicingImpManage-info #Transactor-id").val(k);
                	layer.close(index)
                },
                btn2:function(index){
					$("#servicingImpManage-info #setTransactor").val("")
					$("#servicingImpManage-info #Transactor-id").val("");
                	layer.close(index)
                }
			})
		}
	})
}

function setConsumptionSpart(){
	layer.open({
		type:1,
        skin:'', //样式类名
        anim:2,
        shade: 0.3,
        title:'配件维护',
        area:[ '780px', '650px' ],
        btn:['保存','关闭'],
        content: $("#setConsumptionSpart-form"),
        success: function (layero, index){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"equipid=-1",
				url:"../storestockgoodsinfo/goodsdetailjson.do",
				success:function(data){
					$("#setConsumptionSpart-form #equipname").html($("#equipname-label").html());
					$("#setConsumptionSpart-form #parantequip").html($("#equipparent-label").html()!=""?$("#equipparent-label").html():"无");
					var setting={
				        callback:{
				        	onClick:clickGoodsModelNumber
		        		}
					};
					$.fn.zTree.init($("#setConsumptionSpart-form #equipsparts-tree"), setting, JSON.parse(data));
					fuzzySearch('equipsparts-tree','#setConsumptionSpart-form #sreach-btn',null,true);
				}
			})
		},
		yes:function(index){
        	insertParts(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearPartsInfo();
        	$("#setparts-form").hide();        	
        }
	})
}