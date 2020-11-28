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
			pageSize:page,
			current:13
		}
	$("#servicingImpManage-list").load("../EquipServicingImplement/search.do",params);
}
/**显示维修单 */
function showImplementInfo(type,id){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?'新增设备维修单':"修改设备维修单",
        area:[ '760px', '860px' ],
        btn:type!=2?['保存','关闭']:['关闭'],
        content: $("#servicingImpManage-info"),
        success: function (layero, index){
			if(type!=0){
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
					data:"id="+id,
        			url:"../EquipServicingImplement/getDetailInfo.do",
        			success:function(data){
						if(data.code==200){
							var Implement=data.Implement;
							$("#servicingImpManage-info #id").val(Implement.id);
							$("#servicingImpManage-info #dept").html(Implement.proposDept);
							$("#servicingImpManage-info #proposer").html(Implement.proposerName);
							$("#servicingImpManage-info #application_time").val(data.applicationTime);
							$("#servicingImpManage-info #backfire_time").val(data.backfireTime);
							$("#servicingImpManage-info #equip-name").val(Implement.equipName);
							$("#servicingImpManage-info #equip-id").val(Implement.equipId);
							$("#servicingImpManage-info #modelnumber").html(Implement.equipModelNumber);
							$("#servicingImpManage-info #location").html(Implement.location);
							$("#servicingImpManage-info #remarks").val(Implement.remarks);
						}else{
							layer.msg(data.msg)
							layer.close(layer.index)
						}
					}
				})
			}
		},
		yes:function(index){
			if(type!=2){
				if(checkImplementInfo()){
					$.ajax({
						contenType:'application/json',
						Type:'POST',
						dataType:'json',
						url:"../EquipServicingImplement/save.do",
						data:$("#servicingImpManage-info").serialize()+"&parts="+JSON.stringify(Consumptionspartlist()),
						success:function(data){
							layer.msg(data.msg)
							if(data.code==200){
								getEquipServicingImplementList(1)
								clearImplementInfo();
								layer.close(index);
							}
						}
					})
				}
			}else{
				layer.close(index)
			}
		},
		end: function(index, layero){
        	clearImplementInfo();
        	$("#servicingImpManage-info").hide();        	
        }		
	})
}

function deleteImplement(id) {
	layer.open({
		type:0,
		title:"删除申请单",
		btn:["确定","取消"],
		content:"是否确定删除此维修单?",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../EquipServicingImplement/delete.do",
				data:"id="+id,
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getEquipServicingImplementList(1)
					}
				}
			})
		}
	})	
}

function Consumptionspartlist(){
	var data=[];
	$("#servicingImpManage-info #ConsumptionSpart tr:gt(0)").each(function(){
		var ConsumptionsPartsInfo={};
		ConsumptionsPartsInfo.partsId=$(this).find("#partid").val();
		ConsumptionsPartsInfo.partsName=$(this).children('td').eq(2).html();
		ConsumptionsPartsInfo.useQuantity=$(this).find("input[name=quantity]").val();
		console.log(ConsumptionsPartsInfo)
		data.push(ConsumptionsPartsInfo)
	})
	return data
}
/**维修单信息校验 */
function checkImplementInfo(){
	var flag=0;
	$("#servicingImpManage-info .needing").each(function(){
		if($(this).val()==""){
			layer.msg($(this).attr("data-name")+"不能为空");
			$(this).focus();
			flag+=1;
			return false;
		}
	})
	return flag>0?false:true;
}
/**清空维修单信息 */
function clearImplementInfo(){
	
}
/**设置设备维修申请人 */
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
                area : [ '250px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="proposertree" class="ztree"></ul>',
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
        title:'零配件及材料耗用维护',
        area:[ '880px', '650px' ],
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
					$("#setConsumptionSpart-form #equipname").html($("#servicingImpManage-info #equip-name").val());
					$("#setConsumptionSpart-form #equipmodel").html($("#servicingImpManage-info #modelnumber").html()!=""?$("#servicingImpManage-info #modelnumber").html():"无");
					var setting={
				        callback:{
				        	onClick:clickConsumptionSpart
		        		}
					};
					$.fn.zTree.init($("#setConsumptionSpart-form #ConsumptionSpart-tree"), setting, JSON.parse(data));
					fuzzySearch('ConsumptionSpart-tree','#setConsumptionSpart-form #sreach-btn',null,true);
				}
			})
			$("#servicingImpManage-info #ConsumptionSpart tr:gt(0)").each(function(){
			$("#setConsumptionSpart-form #partslist-table").append(this)
	})
		},
		yes:function(index){
        	setConsumptionSpartInfo(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	//clearPartsInfo();
        	$("#setConsumptionSpart-form").hide();
        }
	})
}

function clickConsumptionSpart(event, treeId, treeNode){
	if(!treeNode.isParent){
		var flag=true;
		$("#setConsumptionSpart-form #partslist-table tr").each(function(){
			if($(this).find(":input:first").val()==treeNode.id){
				layer.msg("此配件已选择");
				flag=false;
				console.log("break")
			}
			console.log("true")
		})
		if(flag){
			var goodsnode=treeNode.getParentNode();
			var goodstypenode=goodsnode.getParentNode();
			var trhtml=createConsumptionindex(goodsnode,goodstypenode,treeNode);
			$("#setConsumptionSpart-form #partslist-table").append(trhtml)
		}
	}
}

function createConsumptionindex(goodsnode,goodstypenode,treeNode){
	var thtml="<tr><input type='hidden' id='partid' value='"+treeNode.id+"'>"+
				"<td>"+goodstypenode.name+"</td>"+
				"<td>"+goodsnode.name+"</td>"+
				"<td>"+treeNode.name+"</td>"+
				"<td><input class='form-control needing' style='height:20px;' value='1'></td>"+
				"<td>"+treeNode.unit+"</td>"+
				"<td><i class='layui-icon point' title='删除'  onclick='removeConsumptionindex(this)'>&#xe616</i></td></tr>"
	return thtml;
}

function removeConsumptionindex(e){
	layer.open({
		type:0,
		title:"删除",
		btn:["确定","取消"],
		content:"确定撤销增加此配件吗?",
		yes:function(){
			$(e).parent().parent().remove();
			layer.close(layer.index)
		}
	})
}

function setConsumptionSpartInfo(index){
	$("#servicingImpManage-info #ConsumptionSpart tr:gt(0)").remove();
	$("#setConsumptionSpart-form #partslist-table tr:gt(0)").each(function(){
		$("#servicingImpManage-info #ConsumptionSpart").append(this)
	})
	layer.close(index)
}