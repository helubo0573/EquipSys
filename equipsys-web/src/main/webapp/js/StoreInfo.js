
function getStoreInfoTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../storeinfo/search.do",
		success:function(data){
			var setting={
					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			            idKey : "id",  // 节点数据中保存唯一标识的属性名称
			            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
			            rootPId : null  // 根节点id
			        },
			        callback:{
			        	onClick:getStoreInfo
			        }
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data));
		}
	})
}

function insertStoreInfo(){
	if(checkStoreInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../storeinfo/save.do",
			data:createStoreInfoData(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					getStoreInfoTree("#store-tree");
					clearStoreInfo();
					changeBtnState(true)
				}
			}
		})
	}
}



function updateStoreInfo(){
	if(checkStoreInfo()){
		layer.open({
			type:0,
			title:"仓库(位)信息修改",
			btn:["确定","取消"],
			content:"确定修改此信息吗？",
			yes:function(){
				$.ajax({
					contenType:'application/json',
					Type:'POST',
					dataType:'json',
					url:"../storeinfo/save.do",
					data:createStoreInfoData(),
					success:function(data){
						layer.msg(data.msg)
						if(data.code==200){
							getStoreInfoTree("#store-tree")
							clearRoleInfo();
							changeStoreInfoBtnState(true)
						}
					}
				})
			}
		})
	}
}

function deleteStoreInfo(){
	layer.open({
		type:0,
		title:"删除",
		btn:["确定","取消"],
		content:"确定删除此仓库(位)吗？",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../storeinfo/delete.do",
				data:"id="+$("#storeid-info").val(),
				success:function(data){
					if(data.code==200){
						getRoleTree("#role-tree");
						clearRoleInfo();
						changeStoreInfoBtnState(true)
						layer.msg(data.msg)
						layer.close(layer.index)
					}
				}
			})
		}
	})
}

function resetStoreInfo(){
	clearStoreInfo();
	changeStoreInfoBtnState(true)
}

function createStoreInfoData(){
	var data=$("#storeinfo-form").serialize();
	console.log(data)
	return data;
}

function checkStoreInfo(){
	if($("#storename-info").val()==""){
		layer.msg("仓库(位)名称不能为空")
		return false;
	}
	return true;
}

function getStoreInfo(event, treeId, treeNode){
	var pnode=treeNode.getParentNode();
	$("#storeid-info").val(treeNode.id);
	$("#storename-info").val(treeNode.name);
	$("#storeorder-info").val(treeNode.order)
	if(pnode!=null){
		$("#parentid-info").val(pnode.id);
		$("#parentstore-info").val(pnode.name);
	}
	$("#storeremarks-info").val(treeNode.remarks);
	changeStoreInfoBtnState(false)
}

$("#storeinfo-form #parentstore-info").click(function(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../storeinfo/search.do",
		data:"type=1",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="parentstore" class="ztree" style="color: #555"></ul>',
                btn:['清空'],
                success: function (layero, index) {
                	var setting={
                			callback:{
                				onClick:clickStoreNode
                		}
                	}
                	$.fn.zTree.init($("#parentstore"), setting, JSON.parse(data));
                },yes:function(index,layero){
                	$("#deptid-info").val("")
                	$("#roledept-info").val("")
                	layer.close(index)
                }
			})
		}
	})
})

function clickStoreNode(event, treeId, treeNode){
	$("#parentstore-info").val(treeNode.name);
	$("#parentid-info").val(treeNode.id);
	layer.close(layer.index)
}
function clearStoreInfo(){
	$("#storeinfo-form input[type!=radio],textarea").val("");
}

function changeStoreInfoBtnState(state){
	$("#store-btn #update-btn").attr("disabled",state);
	$("#store-btn #delete-btn").attr("disabled",state);
	$("#store-btn #rolemenu-btn").attr("disabled",state);
}