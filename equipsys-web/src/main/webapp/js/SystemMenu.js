/**
 * 新增菜单资源
 */
function InsertMenu(){
	if(CheckMenuInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../system/savemenu.do",
			data:CreateMenuData(),
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg);
					ResetMenu();
					reLoadData();
					InsertBtnState()
				}
			}
		})
	}
}
$("input[name='menu-type']").click(function(){
	changeinputstate();
})

function changeinputstate(){
	var menutype=$("input[name='menu-type']:checked").val();
	console.log(menutype)
	$("#menu-info #menu-parent").removeAttr("disabled");
	$("#menu-info #menu-url").removeAttr("disabled");
	$("#menu-info #menu-perms").removeAttr("disabled");
	switch (menutype) {
	case "0":
		$("#menu-parent").val("");
		$("#menu-parent").attr("disabled","disabled");
		$("#menu-url").val("");
		$("#menu-url").attr("disabled","disabled");
		$("#menu-perms").val("");
		$("#menu-perms").attr("disabled","disabled");
		break;
	case "1":
		$("#menu-perms").val("");
		$("#menu-perms").attr("disabled","disabled");
		break;
	case "2":
		$("#menu-url").val("");
		$("#menu-url").attr("disabled","disabled");
		break;
	}
}
/**
 * 点击选择上级菜单
 * @returns
 */
function selectparent(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../system/selectparent.do",
		success:function(data){
			var DataNodes=JSON.parse(data.menutree);
			var setting={
					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			            idKey : "id",  // 节点数据中保存唯一标识的属性名称
			            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
			            rootPId : null  // 根节点id
			        },
			        callback:{
			        	onClick:getParentInfo,
			        }
			};
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '280px', '400px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="zztree" class="ztree"></ul>',
                success: function (layero, index) {
                	$.fn.zTree.init($("#zztree"), setting, DataNodes);
                }
			})
		}
	})
}
/**
 * 修改产品类型信息
 * @returns
 */
function UpdateMenu(){
	if(CheckMenuInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../system/savemenu.do",
			data:CreateMenuData(),
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg);
					ResetMenu();
					reLoadData();
				}
			}
		})
	}
}
/**
 * 删除产品类型信息
 * @returns
 */
function DeleteMenu(){
	var id=$("#menu-info #menu-id").val();
	layer.confirm("是否删除菜单资源:"+$("#menu-info #menu-name").val()+"?",{
		btn:["是","否"],
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/deletemenu.do",
				data:"id="+id,
				success:function(data){
					if(data.code==200){
						layer.msg(data.msg)
						ResetMenu();
						reLoadData();
					}else{
						layer.msg(data.msg)
					}
				}
			})			
		}
	})
}

/**
 * 获取菜单树
 */
function getMenuTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../system/getmenutree.do",
		success:function(data){
			var setting={
					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			            idKey : "id",  // 节点数据中保存唯一标识的属性名称
			            pIdKey : "parent",  // 节点数据中保存其父节点唯一标识的属性名称
			            rootPId : null  // 根节点id
			        },
			        callback:{
			        	onClick:getMenuInfo
			        }
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data.menutree));
		}
	})
}

function ResetMenu(){
	$("#menu-info input[type!=radio],textarea,select").val("");
	InsertBtnState()
}
/**
 * 输入校验
 */
function CheckMenuInfo(){
	var menutype=$("input[name='menu-type']:checked").val()
	if(menutype>=0){
		if(menutype!=0){
			if($("#menu-parent").val()==""){
				layer.msg("上级资源名未选")
				return false;
			}
			if(menutype==1 && $("#menu-url").val()==""){
				layer.msg("URL未填写")
				return false;
			}
			if(menutype==2 && $("#menu-perms").val()==""){
				layer.msg("权限标识未填写")
				return false;
			}
		}
	}else{
		layer.msg("请选择资源类型");
		return false;
	}
	if($("#menu-info #menu-name").val()==""){
		layer.msg("资源名称不能为空!");
		return false;
	}
	return true;
}
/**
 * 点击获取节点数据
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function getParentInfo(event, treeId, treeNode){
	$("#parent-id").val(treeNode.id)
	$("#menu-parent").val(treeNode.name)
	layer.close(layer.index)
}

/**
 * 生成json数据
 * @returns
 */
function CreateMenuData(){
	var typeid=$("#menu-info #menu-id").val();
	var menutype=$("input[name='menu-type']:checked").val();
	var menuname=$("#menu-info #menu-name").val();
	var menuparent=$("#menu-info #parent-id").val();
	var menuurl=$("#menu-info #menu-url").val();
	var menuperms=$("#menu-info #menu-perms").val();
	var menuorder=$("#menu-info #menu-order").val();
	var menuicon=$("#menu-info #menu-icon").val();
	var menuremarks=$("#menu-info #menu-remarks").val();
	var param="id="+typeid+"&type="+menutype+"&name="+menuname+"&parent="+menuparent+"&url="+menuurl+"&perms="+menuperms+"&order="+menuorder+"&icon="+menuicon+"&remarks="+menuremarks;
	return param;
}
function Changestate(){
	$("#menu-info #ftype").val("-1");
	$("#menu-btn > .btn").attr('disabled', true);
}
function InsertBtnState(){
	Changestate();
	$("#menu-btn > #insert-btn").attr('disabled', false);
	$("#menu-btn > #reset-btn").attr('disabled', false);
}
function UpdateBtnState(){
	Changestate();
	$("#menu-btn > #update-btn").attr('disabled', false);
	$("#menu-btn > #delete-btn").attr('disabled', false);
	$("#menu-btn > #reset-btn").attr('disabled', false);
}

/**
 * 获取点击的节点菜单信息并显示在信息窗中
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function getMenuInfo(event, treeId, treeNode){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		data:"id="+treeNode.id,
		dataType:'json',
		url:"../system/getmenuinfo.do",
		success:function(data){
			if(data.menu!=null){
				var menu=data.menu;
				$("#menu-id").val(menu.id);
				$("#parent-id").val(menu.parentId);
				$("input[name='menu-type'][value="+menu.menuType+"]").attr("checked",true);
				$("#menu-name").val(menu.menuName);
				$("#menu-parent").val("");
				$("#menu-url").val(menu.url)
				$("#menu-perms").val(menu.perms);
				$("#menu-order").val(menu.menuOrder);
				$("#menu-icon").val(menu.menuIcon);
				$("#menu-remarks").val(menu.remarks);
			}
			UpdateBtnState()
			changeinputstate()
		}
	})
}

function reLoadData(){
	getMenuTree("#type-tree")
}
