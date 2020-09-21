/**
 * 生成设备树
 * @param e
 * @returns
 */
function getEquipTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		data:"type=4",
		url:"../equip/getequiptree.do",
		success:function(data){
			var setting={
					simpleData : {
		            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
		            idKey : "id",  // 节点数据中保存唯一标识的属性名称
		            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
		            rootPId : null  // 根节点id
		        },
		        callback:{
		        	onClick:clickEquipNode
        		}
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data.equiptree));
			fuzzySearch('equipinfo-tree','#equipinfo-search #sreach-btn',null,true);
		}
	})
}

/**弹出设备信息维护层 **/
function showEquipInfo(type){
	//$("#show-type").val(type)
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:'设备信息',
        area:[ '660px', '500px' ],
        btn:['保存','关闭'],
        content: $("#equipinfo-form"),
        success: function (layero, index){
        	if(type==1){
        		var equipid=$("#equipid-hd").val()
        		$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
        			data:"id="+equipid,
        			url:"../equip/getequipinfo.do",
        			success:function(data){
        				var equip=data.equip;
        				if(equip!=null){
							console.log(equipid)
							$("#equipinfo-form #equip-id").val(equipid);
        					$("#equipinfo-form #subequip-name").val(equip.equipName);
        					$("#equipinfo-form #subequip-code").val(equip.equipCode);
							$("#equipinfo-form #subequip-equipnumber").val(equip.equipModelNumber);
        					$("#equipinfo-form input:radio[name=level]")[equip.equipLevel].checked = true;
        					$("#equipinfo-form #subequip-parent").val(equip.parentequipName);
        					$("#equipinfo-form #subequip-parent").attr("data-parent",equip.parentId);
        					$("#equipinfo-form #subequip-enabledate").val(data.enabledate);
        					$("#equipinfo-form #subequip-supplier").val(equip.supplier);
							$("#equipinfo-form #subequip-suppliernumber").val(equip.supplierNumber);
        					$("#equipinfo-form #subequip-attrdept").val(equip.attrDept);
        					$("#equipinfo-form #subequip-location").val(equip.location);
							$("#equipinfo-form #equipinfo-remarks").val(equip.remarks);
        					$("#equipinfo-form #subequip-op").val(data.opstr.opname)
        					$("#equipinfo-form #subequip-op").attr("data-emp",data.opstr.opid)
        					$("#equipinfo-form #subequip-mp").val(data.mpstr.mpname)
        					$("#equipinfo-form #subequip-mp").attr("data-emp",data.mpstr.mpid)
        				}
        			}
        		})
        	}
        },
        yes:function(index){
        	saveEquipInfo(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearSubEquipInfo();
        	$("#equipinfo-form").hide();        	
        }
	})
}
/**
 * 设备信息树点击事件
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function clickEquipNode(event, treeId, treeNode){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		data:"id="+treeNode.id,
		url:"../equip/getequipinfo.do",
		success:function(data){
			if(data.equip!=null){
				$("#equipid-hd").val(data.equip.id)
				setEquipInfoToInfo(data.equip,treeNode,data.opstr,data.mpstr,data.enabledate);
				changeBtnState(false)
				$("#equipsystem-manageparts").removeAttr("disabled");
				$("#custompart-btn").removeAttr("disabled");
				getEquipPartsList(1)
			}
		}
	})
}
/**
 * 显示设备信息
 * @param equip
 * @param node
 * @param op
 * @param mp
 * @returns
 */
function setEquipInfoToInfo(equip,node,op,mp,enabledate){
	$("#equipname-label").html(equip.equipName)
	switch (equip.equipLevel) {
	case 0:
		$("#equiplevel-label").html("系统级")
		break;
	case 1:
		$("#equiplevel-label").html("模组级")
		break;
	case 2:
		$("#equiplevel-label").html("设备级")
		break;
	}
	var parentnode=node.getParentNode();
	$("#equipcode-label").html(equip.equipCode!=null?equip.equipCode:"");
	$("#equipenabledate-label").html(enabledate);
	$("#equipmodelnumber-label").html(equip.equipModelNumber!=null?equip.equipModelNumber:"");
	$("#equipparent-label").html(parentnode!=null?parentnode.name:"");
	$("#equiplocation-label").html(equip.location!=null?equip.location:"");
	$("#equipsupplier-label").html(equip.supplier!=null?equip.supplier:"");
	$("#equipsuppliernumber-label").html(equip.supplierNumber!=null?equip.supplierNumber:"");
	$("#equipdept-label").html(getDeptName(equip.attrDept));
	$("#equipop-label").html(op.opname);
	$("#equipmp-label").html(mp.mpname);
	$("#remarks-label").html(equip.remarks!=null?equip.remarks:"");
}



/**
 * 建立设备信息数据字串
 * @returns
 */
function createEquipData(){
	var parent=$("#subequip-parent").attr("data-parent");
	var op=$("#subequip-op").attr("data-emp");
	var mp=$("#subequip-mp").attr("data-emp");
	return $("#equipinfo-form").serialize()+"&op="+op+"&mp="+mp+"&parent="+parent;
}
/**
 * 信息合法性校验
 * @returns
 */
function checkEquipInfo(){
	if($("#subequip-name").val()==""){
		layer.msg("设备名称不能为空");
		return false;
	}
	if($("#subequip-code").val()==""){
		layer.msg("设备编号不能为空");
		return false;
	}
	if($('input:radio[name=level]:checked').val()==null){
		layer.msg("选择设备等级")
		return false;
	}
	
	if($("#subequip-enabledate").val()==""){
		layer.msg("启用日期不能为空");
		return false;
	}
	if($("#subequip-attrdept").val()=="-1"){
		layer.msg("请选择所属部门")
		return false;
	}
	if($("input[name='level']:checked").val()!=0){
		if($("#subequip-parent").attr("data-parent")==""){
			layer.msg("设备级别不是系统级时清选择所属设备")
		}
	}
	return true;
}

/**
 * 保存设备信息
 * @param index
 * @returns
 */
function saveEquipInfo(index){
	if(checkEquipInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../equip/saveequip.do",
			data:createEquipData(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					getEquipTree("#equipinfo-tree");
					clearEquipInfo();
					clearSubEquipInfo();
					changeBtnState(true)
					layer.close(index);
				}
			}
		})
	}
}
/**
 * 删除设备
 * @returns
 */
function deleteEquipInfo(){
	var treeObj = $.fn.zTree.getZTreeObj("equipinfo-tree");
	var node=treeObj.getSelectedNodes();
	var cnode=node[0].children;
}

/**
 * 设备信息维护层中弹出父设备选择层
 * @returns
 */
function showEquipparentTree(){
	var id=$("#show-type").val()==1?$("#equipid-hd").val():"";//当id不为空时表示为修改设备父设备，则不查询出该设备及下级设备
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../equip/getequiptree.do",
		data:"type=2&id="+id,	//type=2表示设备类型要小于2，就是不查询设备级,
		success:function(data){
			var htmlstr="<div style='padding:10px'>"+
					"<div id='equipeparenttree-search' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='partstree-search' class='form-control' style='width: 150px;' placeholder='输入设备名称搜索'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='equipeparenttree' class='ztree' style='color: #555'></ul>"+
				"</div>"
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: htmlstr,
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	$.fn.zTree.init($("#equipeparenttree"), {}, JSON.parse(data.equiptree));
					fuzzySearch('equipeparenttree','#equipeparenttree-search #sreach-btn',null,true);
                },yes:function(index,layero){
                	var treeObj = $.fn.zTree.getZTreeObj("equipeparenttree");
                	var node=treeObj.getSelectedNodes();
                	var level=$("input[name='level']:checked").val();
                	if(node[0].level-level<=0){
                		var tlevel=node[0].level+1;
                		$('input:radio[name=level]')[tlevel].checked = true;
                	}
                	$("#subequip-parent").val(node[0].name);
                	$("#subequip-parent").attr("data-parent",node[0].id);
                	var node = node[0].getParentNode();
                	$("#subequiplv-flagship").attr("disabled","disabled");
                	if(node!=null){
                		$("#subequiplv-model").attr("disabled","disabled");
                	}
                	layer.close(index)
                },btn2:function(index,layero){
                	$("#subequip-parent").val("无")
                	$("#subequip-parent").attr("data-parent","");
                	$("#subequiplv-flagship").removeAttr("disabled");
                	$("#subequiplv-model").removeAttr("disabled");
                	layer.close(index)
                },btn3:function(index){
                	layer.close(index)
                }
			})
		}
	})
}
/**
 * 实现员工树菜单
 * @param m
 * @returns
 */
function showEmpTree(m,obj){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../employee/getemployeetree.do",
		data:"type="+m,
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="equipemptree" class="ztree"></ul>',
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	var setting = {
            				check: {
            					enable: true,
            				    chkStyle : "checkbox"
            				}
            		    };
                	var ztree=$.fn.zTree.init($("#equipemptree"), setting, JSON.parse(data.emptree));
                	var ids=$(obj).attr("data-emp").split(',');
                	var names=$(obj).val().split(',');
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
                	var zTree = $.fn.zTree.getZTreeObj("equipemptree");
                	var nodes = zTree.getCheckedNodes(true),
                	v = "";
                	k="";
        			for (var i=0, l=nodes.length; i<l; i++) {
        				v += nodes[i].name + ",";
        				k += nodes[i].id + ",";
        			}
        			if (v.length > 0 ) v = v.substring(0, v.length-1);
        			if (k.length > 0 ) k = k.substring(0, k.length-1);
                	$(obj).val(v);
                	$(obj).attr("data-emp",k);
                	layer.close(index)
                },
                btn2:function(index){
					$(obj).val("")
					$(obj).attr("data-emp","");
                	layer.close(index)
                }
			})
		}
	})
}
/**
 * 获取根据部门id获取部门名称
 * @param deptid
 * @returns
 */
function getDeptName(deptid){
	var deptname="";
	$("#subequip-attrdept option").each(function(){
		if($(this).val()==deptid)	deptname=$(this).html();
	})
	return deptname;
}
/**
 * 清空设备信息显示内容
 * @returns
 */
function clearEquipInfo(){
	$("#equip-info input,textarea,select").val("");
	$("#equip-info label").html("");
}
/**
 * 清空设备信息维护内容
 * @returns
 */
function clearSubEquipInfo(){
	$("#equipinfo-form input[type!=radio],textarea,select").val("");
	$("input:radio[name='level']:checked").prop("checked",false);
	$("input:radio[name='level']").removeAttr("disabled");
}

/**
 * 改变按钮状态 0为可用 1为不可用
 * @param type
 * @returns
 */
function changeBtnState(type){
	$("#equipsystem-updateequip").attr("disabled",type);
	$("#equipsystem-deleteequip").attr("disabled",type);
	$("#equipsystem-manageparts").attr("disabled",type)
	$("#custompart-btn").attr("disabled",type)
}

/**配件管理 */
function getEquipPartsList(pagenum){
	var goodstype=$("#search-div #search-modelid").val()
	var partsname=$("#search-div #search-partsname").val()
	var quantity=$("#search-div #search-quantity").val()
	var equipid=$("#equip-info #equipid-hd").val();
	var param={
			goodstype:goodstype,
			partsname:partsname,
			quantity:quantity,
			equipid:equipid,
			pageSize:6,
			current:pagenum
		}
	$("#subequip-list").load("../equipparts/search.do",param)
}

function showEquipParts(){
	layer.open({
		type:1,
        skin:'', //样式类名
        anim:2,
        shade: 0.3,
        title:'配件维护',
        area:[ '780px', '650px' ],
        btn:['保存','关闭'],
        content: $("#setparts-form"),
        success: function (layero, index){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"equipid="+$("#equip-info #equipid-hd").val(),
				url:"../storestockgoodsinfo/goodsdetailjson.do",
				success:function(data){
					$("#setparts-form #equipname").html($("#equipname-label").html());
					$("#setparts-form #parantequip").html($("#equipparent-label").html()!=""?$("#equipparent-label").html():"无");
					var setting={
				        callback:{
				        	onClick:clickGoodsModelNumber
		        		}
					};
					$.fn.zTree.init($("#setparts-form #equipsparts-tree"), setting, JSON.parse(data));
					fuzzySearch('equipsparts-tree','#setparts-form #sreach-btn',null,true);
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
function clickGoodsModelNumber(event, treeId, treeNode){
	if(!treeNode.isParent){
		var flag=true;
		$("#setparts-form #partslist-table tr").each(function(){
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
			var trhtml=createindex(goodsnode,goodstypenode,treeNode);
			$("#setparts-form #partslist-table").append(trhtml)
		}
	}
}

function createindex(goodsnode,goodstypenode,treeNode){
	var thtml="<tr><input type='hidden' name='model' value='"+treeNode.id+"'>"+
				"<td width='100px'>"+goodstypenode.name+"</td>"+
				"<td width='145px'>"+goodsnode.name+"</td>"+
				"<td width='150px'>"+treeNode.name+"</td>"+
				"<td  width'70px'><input name='quantity' class='form-control needing' style='height:20px;' value='1'></td>"+
				"<td><i class='layui-icon point' title='删除'  onclick='removeindex(this)'>&#xe616</i></td></tr>"
	return thtml;
}
function removeindex(e){
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
function insertParts(index){
	var data=partslist();
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		data:"parts="+JSON.stringify(data),
		url:"../equipparts/insert.do",
		success:function(data){
			layer.msg(data.msg)
				if(data.code==200){
					getEquipPartsList(1)
					clearPartsInfo();
					layer.close(index);
				}
		}
	})
}

function partslist(){
	var modelid=$("#equip-info #equipid-hd").val();
	var data=[];
	$("#setparts-form #partslist-table tr:gt(0)").each(function(){
		var EquipPartsInfo={};
		EquipPartsInfo.equipId=modelid;
		EquipPartsInfo.goodsModelId=$(this).find("input[name=model]").val();
		EquipPartsInfo.partsName=$(this).children('td').eq(1).html()+"_"+$(this).children('td').eq(2).html();   //$(this).children('td').eq(0).html()+"_"+
		EquipPartsInfo.quantity=$(this).find("input[name=quantity]").val();
		data.push(EquipPartsInfo)
	})
	return data;
}
function clearPartsInfo(){
	$("#setparts-form input,textarea,select").val("");
	$("#setparts-form #partslist-table tr:gt(0)").remove();
	$("#setparts-form #equipname").html("");
	$("#setparts-form #parantequip").html("");
}

function showCustomPart(){
	layer.open({
		type:1,
        skin:'', //样式类名
        anim:2,
        shade: 0.3,
        title:'自定义配件',
        area:[ '300', '370px' ],
        btn:['保存','关闭'],
        content: $("#custompart-form"),
        success: function (layero, index){
			$("#custompart-form #equip-name").html($("#equipname-label").html())
			$("#custompart-form #equipid").val($("#equip-info #equipid-hd").val())
		},
		yes:function(index){
        	saveCustomPartInfo(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearCustomPartInfo();
        	$("#custompart-form").hide();        	
        }
	})
}

function updatePart(id,goodstypeId,typeName,partsName,storeQuantity,modelid){
	$("#custompart-form #partsid").val(id);
	$("#custompart-form #goodstypeid").val(goodstypeId);
	$("#custompart-form #goodstypename").val(typeName);
	$("#custompart-form #linkStockGoods").val(partsName);
	$("#custompart-form #partname").val(partsName);
	$("#custompart-form #quantity").val(storeQuantity);
	$("#custompart-form #model-id").val(modelid)
	showCustomPart();
}

function deletepart(partid){
	layer.open({
		type:0,
		title:"删除",
		btn:["确定","取消"],
		content:"确定删除此配件信息吗?",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"id="+partid,
				url:"../equipparts/delet.do",
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getEquipPartsList(1)
					}
				}
			})
		}
	})
}
function PartshowGoodsType(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../goodstype/searchfortree.do",
		success:function(data){
			var htmlstr="<div style='padding:10px'>"+
					"<div id='typeztree-search' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='partstree-search' class='form-control' style='width: 150px;' placeholder='输入设备名称搜索'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='typeztree' class='ztree'></ul>"+
				"</div>"
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '280px', '400px' ],
                shadeClose: true, //开启遮罩关闭
                content: htmlstr,
                success: function (layero, index) {
                	$.fn.zTree.init($("#typeztree"), { callback:{ onClick:PartgetGoodsTypeInfo} }, JSON.parse(data));
					fuzzySearch('typeztree','#typeztree-search #sreach-btn',null,true);
                }
			})
		}
	})
}
function PartgetGoodsTypeInfo(event, treeId, treeNode){
	$("#custompart-form #goodstypeid").val(treeNode.id)
	$("#custompart-form #goodstypename").val(treeNode.name);
	$("#custompart-form #model-id").val("")
	$("#custompart-form #linkStockGoods").val("");
	layer.close(layer.index)
}

function PartsetStockGoods(){
	var htmlstr="<div style='padding:10px'>"+
					"<div id='PartLinkGoodsztree-search' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='partstree-search' class='form-control' style='width: 150px;' placeholder='输入配件名称搜索'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='PartLinkGoodsztree' class='ztree'></ul>"+
				"</div>"
	layer.open({
		type:1,
        skin:'', //样式类名
        anim:2,
        shade: 0.3,
        title:'关联库存物料',
        area:[ '300px', '500px' ],
        content: htmlstr,
        success: function (layero, index){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"equipid="+$("#equip-info #equipid-hd").val()+"&goodstype="+$("#custompart-form #goodstypeid").val(),
				url:"../storestockgoodsinfo/goodsdetailjson.do",
				success:function(data){
					var setting={
				        callback:{
				        	onClick:setpartinfolinkgoods
		        		}
					};
					$.fn.zTree.init($("#PartLinkGoodsztree"), setting, JSON.parse(data));
					fuzzySearch('PartLinkGoodsztree','#PartLinkGoodsztree-search #sreach-btn',null,true);
				}
			})
		},
        end: function(index, layero){
        	$("#setparts-form").hide();
        }
	})
}
function setpartinfolinkgoods(event, treeId, treeNode){
	var parentnode=treeNode.getParentNode();
	var pparentnode=parentnode.getParentNode()
	$("#custompart-form #model-id").val(treeNode.id)
	$("#custompart-form #linkStockGoods").val(parentnode.name+"_"+treeNode.name);
	$("#custompart-form #goodstypename").val(pparentnode.name)
	$("#custompart-form #goodstypeid").val(pparentnode.id)
	$("#custompart-form #partname").val(parentnode.name+"_"+treeNode.name)
	layer.close(layer.index)
}

function saveCustomPartInfo(index){
	if(custompart()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../equipparts/saveCustomPart.do",
			data:$("#custompart-form").serialize(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					getEquipPartsList(1)
					clearCustomPartInfo();
					layer.close(index);
				}
			}
		})
	}
}

function custompart(){
/*	if($("#custompart-form #goodstypename").val()==""){
		layer.msg("请选择配件的物料类型");
		return false;
	}*/
	
	if($("#custompart-form #partname").val()==""){
		layer.msg("请填写配件名称")
		return false;
	}
	
	if($("#custompart-form #partname").val()==""){
		layer.msg("请填写配件名称");
		return false;
	}
	
	if($("#custompart-form #quantity").val()==""){
		layer.msg("请填写设备的配件需求数量")
		return false;
	}
	return true;
}
function clearCustomPartInfo(){
	$("#custompart-form input").val("");
}
function partsseachgoods(){
	var param=$("#setparts-form #partstree-search").val();
	var treeObj = $.fn.zTree.getZTreeObj("equipsparts-tree");
	treeObj.expandAll(false);
	console.log("关闭")
	var nodes = treeObj.getNodesByParamFuzzy("name", param, null);
	if(nodes!=[]){
		for( var i=0, l=nodes.length; i<l; i++ ){
			//nodes[i].highlight = highlight;
			var node = nodes[i];
			console.log(node.isParent+"----"+!node.open)
			if(node.isParent && !node.open) {
				console.log("子项打开")
				treeObj.expandNode(node,true,false,null);
			}
			treeObj.updateNode(nodes[i]);
			while( node.getParentNode()!=null ){
				//if (node.getParentNode()==null) break;
				node = node.getParentNode();
				if (!node.open){
					console.log("节点打开");			
					treeObj.expandNode(node,true,false,null);
				} 
			}
		}
	}
}

function searchParts(){
	getEquipPartsList(1);
}