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
		}
	})
}

/**弹出设备信息维护层 **/
function showEquipInfo(type){
	$("#show-type").val(type)
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:'设备信息',
        area:[ '660px', '430px' ],
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
        					$("#subequip-name").val(equip.equipName);
        					$("#subequip-code").val(equip.equipCode);
        					$('input:radio[name=subequip-level]')[equip.equipLevel].checked = true;
        					$("#subequip-parent").val(equip.parentequipName);
        					$("#subequip-parent").attr("data-parent",equip.parentId);
        					$("#subequip-enabledate").val(data.enabledate);
        					$("#subequip-supplier").val(equip.supplier);
        					$("#subequip-attrdept").val(equip.attrDept);
        					$("#subequip-location").val(equip.location);
        					$("#subequip-op").val(data.opstr.opname)
        					$("#subequip-op").attr("data-emp",data.opstr.opid)
        					$("#subequip-mp").val(data.mpstr.mpname)
        					$("#subequip-mp").attr("data-emp",data.mpstr.mpid)
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
	$("#equipparent-label").html(parentnode!=null?parentnode.name:"");
	$("#equiplocation-label").html(equip.location!=null?equip.location:"");
	$("#equipsupplier-label").html(equip.supplier!=null?equip.supplier:"");
	$("#equipdept-label").html(getDeptName(equip.attrDept));
	$("#equipop-label").html(op.opname);
	$("#equipmp-label").html(mp.mpname);
}


/**
 * 建立设备信息数据字串
 * @returns
 */
function createEquipData(){
	var type=$("#show-type").val()
	var id=type==1?$("#equipid-hd").val():"";
	var name=$("#subequip-name").val();
	var code=$("#subequip-code").val();
	var level=$("input[name='subequip-level']:checked").val();
	var parent=$("#subequip-parent").attr("data-parent");
	var enabledate=$("#subequip-enabledate").val();
	var supplier=$("#subequip-supplier").val();
	var attrdept=$("#subequip-attrdept").val();
	var location=$("#subequip-location").val();
	var op=$("#subequip-op").attr("data-emp");
	var mp=$("#subequip-mp").attr("data-emp");
	var param="id="+id+"&name="+name+"&code="+code+"&level="+level+"&parent="+parent+"&enabledate="+enabledate+"&supplier="+supplier+"&attrdept="+attrdept+"&location="+location+"&op="+op+"&mp="+mp;
	return param;
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
	if($('input:radio[name=subequip-level]:checked').val()==null){
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
	if($("input[name='subequip-level']:checked").val()!=0){
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
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="equipeparenttree" class="ztree" style="color: #555"></ul>',
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	$.fn.zTree.init($("#equipeparenttree"), {}, JSON.parse(data.equiptree));
                },yes:function(index,layero){
                	var treeObj = $.fn.zTree.getZTreeObj("equipeparenttree");
                	var node=treeObj.getSelectedNodes();
                	var level=$("input[name='subequip-level']:checked").val();
                	if(node[0].level-level<=0){
                		var tlevel=node[0].level+1;
                		$('input:radio[name=subequip-level]')[tlevel].checked = true;
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
	$("input:radio[name='subequip-level']:checked").prop("checked",false);
	$("input:radio[name='subequip-level']").removeAttr("disabled");
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
}

/**配件管理 */

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
				data:"type=4",
				url:"../storestockgoodsinfo/goodsdetailjson.do",
				success:function(data){
					var setting={
				        callback:{
				        	onClick:clickGoodsModelNumber
		        		}
					};
					$.fn.zTree.init($("#setparts-form #equipsparts-tree"), setting, JSON.parse(data));
				}
			})
		},
		yes:function(index){
        	savePartsInfo(index);
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
	var index=$("#setparts-form #partslist-table tr").length;
	var thtml="<tr><input type='hidden' name='model"+index+"' value='"+treeNode.id+"'>"+
				"<td width='100px'>"+goodstypenode.name+"</td>"+
				"<td width='145px'>"+goodsnode.name+"</td>"+
				"<td width='150px'>"+treeNode.name+"</td>"+
				"<td  width'70px'><input name='quantity"+index+"' class='form-control needing' style='height:20px;' value='1'></td>"+
				"<td><i class='layui-icon point' title='删除'  onclick='removeindex(this)'>&#xe616</i></td></tr>"
	return thtml;
}
function removeindex(e){
	layer.open({
		type:0,
		title:"删除",
		btn:["确定","取消"],
		content:"确定撤销增加此配件吗？?",
		yes:function(){
			$(e).parent().parent().remove();
			layer.close(layer.index)
		}
	})
}
function insertParts(){
	
}

function savePartsInfo(index){
	
}
function clearPartsInfo(){
	
}