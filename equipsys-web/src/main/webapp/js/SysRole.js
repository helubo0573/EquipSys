/**
 * 
 */


function getRoleTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../system/getRoleTree.do",
		success:function(data){
			var setting={
					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			            idKey : "id",  // 节点数据中保存唯一标识的属性名称
			            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
			            rootPId : null  // 根节点id
			        },
			        callback:{
			        	onClick:getRoleInfo
			        }
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data.roletree));
		}
	})
}

function insertRole(){
	if(checkRoleInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../system/saveRole.do",
			data:createRoleData(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					getRoleTree("#role-tree");
					clearRoleInfo();
					changeBtnState(true)
				}
			}
		})
	}
}



function updateRole(){
	if(checkRoleInfo()){
		layer.open({
			type:0,
			title:"修改角色信息",
			btn:["确定","取消"],
			content:"确定修改此信息吗？",
			yes:function(){
				$.ajax({
					contenType:'application/json',
					Type:'POST',
					dataType:'json',
					url:"../system/saveRole.do",
					data:createRoleData(),
					success:function(data){
						layer.msg(data.msg)
						if(data.code==200){
							getRoleTree("#role-tree");
							clearRoleInfo();
							changeBtnState(true)
						}
					}
				})
			}
		})
	}
}

function deleteRole(){
	layer.open({
		type:0,
		title:"删除角色信息",
		btn:["确定","取消"],
		content:"确定删除此角色吗？",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/deleteRole.do",
				data:"id="+$("#roleid-info").val(),
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getRoleTree("#role-tree");
						clearRoleInfo();
						changeBtnState(true)
					}
				}
			})
		}
	})
}

function resetRole(){
	clearRoleInfo();
}

function setRoleTMenu(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../system/getRoleMenu.do",
		data:"roleid="+$("#roleid-info").val(),
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="menutree" class="ztree"></ul>',
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	var setting = {
            				check: {
            					enable: true,
            				    chkStyle : "checkbox",
            				    chkboxType: { "N": "s", "Y": "p" },
            				}
            		    };
                	var ztree=$.fn.zTree.init($("#menutree"), setting, JSON.parse(data.menutree));
                	var rmlist=data.rmlist;
                	$.each(rmlist,function(index,rm){
                		console.log(rm.menuId)
                		var nodeList=ztree.getNodesByParam("id", rm.menuId, null);
                		ztree.checkNode(nodeList[0],true,true);
                	})
                },
                yes:function(index){
                	var zTree = $.fn.zTree.getZTreeObj("menutree");
                	var nodes = zTree.getCheckedNodes(true);
                	var nstr="";
                	for (var i=0, l=nodes.length; i<l; i++) {
                		nstr+=nodes[i].id + ","
        			}
                	$.ajax({
                		contenType:'application/json',
                		Type:'POST',
                		async:false,
                		dataType:'json',
                		url:"../system/setRoleMenu.do",
                		data:"id="+$("#roleid-info").val()+"&nodes="+nstr.substring(0,nstr.length-1),
                		success:function(data){
                			if(data.code==200){
                				layer.msg(data.msg)
                				layer.close(index)
                			}
                		}
                	})
                },
                btn2:function(index){
					/*$(obj).val("")
					$(obj).attr("data-emp","");
                	layer.close(index)*/
                }
			})
		}
	})
}

function createRoleData(){
	var data=$("#roleinfo-form").serialize();
	return data;
}

function checkRoleInfo(){
	if($("#rolename-info").val()==""){
		layer.msg("角色名称不能为空")
		return false;
	}
	return true;
}
function getRoleInfo(event, treeId, treeNode){
	if(treeNode.isrole){
		var pnode=treeNode.getParentNode();
		console.log(treeNode.name+"-"+treeNode.id+"-"+pnode.name+"-"+pnode.name+"-"+treeNode.remarks)
		$("#roleid-info").val(treeNode.id);
		$("#deptid-info").val(pnode.id);
		$("#rolename-info").val(treeNode.name);
		$("#roledept-info").val(pnode.id==0?"":pnode.name);
		$("#roleremarks-info").val(treeNode.remarks);
		changeBtnState(false)
	}
}

$("#roleinfo-form #roledept-info").click(function(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../dept/getparentdepttree.do",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="parentdepttree" class="ztree" style="color: #555"></ul>',
                btn:['清空'],
                success: function (layero, index) {
                	var setting={
                			callback:{
                				onClick:clickDeptNode
                		}
                	}
                	$.fn.zTree.init($("#parentdepttree"), setting, JSON.parse(data.parentdepttree));
                },yes:function(index,layero){
                	$("#deptid-info").val("")
                	$("#roledept-info").val("")
                	layer.close(index)
                }
			})
		}
	})
})

function clickDeptNode(event, treeId, treeNode){
	$("#deptid-info").val(treeNode.id)
	$("#roledept-info").val(treeNode.name)
	layer.close(layer.index)
}

function clearRoleInfo(){
	$("#roleinfo-form input[type!=radio],textarea").val("");
}

function changeBtnState(state){
	$("#role-btn #update-btn").attr("disabled",state);
	$("#role-btn #delete-btn").attr("disabled",state);
	$("#role-btn #rolemenu-btn").attr("disabled",state);
}