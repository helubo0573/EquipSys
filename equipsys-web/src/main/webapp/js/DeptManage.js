 
function InsertDept(){
	if(checkDeptInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../dept/savedept.do",
			data:createDeptData(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					getDeptTree("#deptinfo-tree");
					clearDeptInfo();
					changeBtnState(true)
					layer.close(index);
				}
			}
		})
	}
}

function UpdateDept(){
	if(checkDeptInfo()){
		layer.open({
			type:0,
			title:"修改部门信息",
			btn:["确定","取消"],
			content:"确定修改此信息吗？",
			yes:function(){
				$.ajax({
					contenType:'application/json',
					Type:'POST',
					dataType:'json',
					url:"../dept/savedept.do",
					data:createDeptData(),
					success:function(data){
						layer.msg(data.msg)
						if(data.code==200){
							getDeptTree("#deptinfo-tree");
							clearDeptInfo();
							changeBtnState(true)
						}
					}
				})
			}
		})
	}
}

function DeleteDept(){
	layer.open({
		type:0,
		title:"删除",
		btn:["确定","取消"],
		content:"确定删除此部门或岗位信息吗?",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"id="+$("#dept-id").val(),
				url:"../dept/deletedept.do",
				success:function(data){
					layer.msg(data.msg)
				}
			})
		}
	})
}

function ResetDept(){
	clearDeptInfo()
	changeBtnState(true)
}

function clearDeptInfo(){
	$("#org-info input[type!=radio],textarea,select").val("");
	$("input:radio[name='dept-type']:checked").prop("checked",false);
	$("input:radio[name=dept-type]").attr("disabled",false);
}

function changeBtnState(state){
	$("#dept-btn #update-btn").attr("disabled",state);
	$("#dept-btn #delete-btn").attr("disabled",state);
}
/**
 *	生成data参数
 * @returns
 */
function createDeptData(){
	var id=$("#dept-id").val();
	var type=$("input[name='dept-type']:checked").val();
	var deptname=$("#dept-name").val();
	var parentid=$("#dept-parent").attr("data-parent")!=""?$("#dept-parent").attr("data-parent"):0;
	var remarks=$("#dept-remarks").val();
	var param="id="+id+"&type="+type+"&deptname="+deptname+"&parentid="+parentid+"&remarks="+remarks;
	return param;
}

function checkDeptInfo(){
	if($("input[name='dept-type']:checked").val()==null){
		layer.msg("请选择部门类型!")
		return false;
	}
	if($("#dept-name").val()==""){
		layer.msg("请填写部门名称");
		return false;
	}
	if($("input:radio[name='dept-type']:checked").val()==1 && $("#dept-parent").attr("data-parent")==""){
		layer.msg("岗位必须选择上级部门")
		return false;
	}
	return true;
}
function checkDeptType(pId){
	var depttype=$("input:radio[name='dept-type']:checked").val();
	console.log(pId)
	if(pId!=0){
		if(depttype==0){
			layer.msg("不能选择一个二级部门作为另一个部门的上级")
			return false;
		}else{
			$("#depttype-dept").attr("disabled",true)
		}
	}else{
		$("#depttype-dept").attr("disabled",false)
	}
	return true;
}

/**
 * 
 */
function getDeptTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../dept/getdepttree.do",
		success:function(data){
			var setting={
					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			            idKey : "id",  // 节点数据中保存唯一标识的属性名称
			            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
			            rootPId : null  // 根节点id
			        },
			        callback:{
			        	onClick:clickNode
			        }
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data.depttree));
		}
	})
}

/**
 * 部门树点击事件
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function clickNode(event, treeId, treeNode){
	var parentnode=treeNode.getParentNode();
	$("#dept-id").val(treeNode.id);
	$("input:radio[name=dept-type]")[treeNode.depttype].checked=true;
	$("#dept-name").val(treeNode.name);
	if(parentnode!=null){
		$("#dept-parent").val(parentnode.name);
		$("#dept-parent").attr("data-parent",parentnode.id);
	}
	$("input:radio[name=dept-type]").attr("disabled",true);
	$("#dept-remarks").val(treeNode.title)
	changeBtnState(false)
}

function shouwParentDeptTree(){
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
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	$.fn.zTree.init($("#parentdepttree"), {}, JSON.parse(data.parentdepttree));
                },yes:function(index,layero){
                	var treeObj = $.fn.zTree.getZTreeObj("parentdepttree");
                	var node=treeObj.getSelectedNodes();
                	if(checkDeptType(node[0].pId)){
	                	$("#dept-parent").val(node[0].name);
	                	$("#dept-parent").attr("data-parent",node[0].id);
	                	layer.close(index)
                	}
                },btn2:function(index,layero){
                	$("#dept-parent").val("")
                	$("#dept-parent").attr("data-parent","");
                	$("#depttype-dept").attr("disabled",false)
                	$("input:radio[name='dept-type']:checked").prop("checked",false);
                	layer.close(index)
                },btn3:function(index){
                	layer.close(index)
                }
			})
		}
	})
}