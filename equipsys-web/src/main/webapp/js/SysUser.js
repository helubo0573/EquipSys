/**
 * 
 */
function getUserPage(pagenum){
	var param={
			username:$("#username-search").val(),
			employee:$("#userempname-search").val(),
			pageSize:12,
			current:pagenum
	}
	$("#sysuser-listdiv").load("../system/searchUser.do",param);
}

function showSysUserInfo(type,id){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==1?'用户信息修改':'新建用户',
        area:[ '540px', '265px' ],
        btn:['保存','关闭'],
        content: $("#sysuser-form"),
        success: function (layero, index){
        	if(type==1){
        		$.ajax({
        			contenType:'application/json',
					Type:'POST',
					dataType:'json',
					data:"id="+id,
					url:"../system/findUserById.do",
					success:function(data){
						if(data.user!=null){
							var user=data.user;
							$("#userinfo-id").val(user.id)
							$("#userinfo-name").val(user.username);
							$("#userinfo-empname").val(user.empname)
							$("#userinfo-empid").val(user.employeeId)
						}
					}
        		})	
        	}
        },
        yes:function(index){
        	saveUserInfo(index)
        },
        end: function(index, layero){
        	clearSysUserInfo()
        	$("#sysuser-form").hide();        	
        }
	})
}
function saveUserInfo(index){
	if(checkUserInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			data:createUserData(),
			url:"../system/saveUser.do",
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg)
					getUserPage(1);
					layer.close(index)
				}else{
					layer.msg(data.msg)
				}
			}
		})
	}
}
function checkUserInfo(){
	if($("#userinfo-name").val()==""){
		layer.msg("用户名不能为空");
		return false;
	}
	if($("#userinfo-empid").val()==""){
		layer.msg("清选择用户所关联的员工");
		return false;
	}
	return true;
}
function createUserData(){
	var data=$("#sysuser-form").serialize();
	return data;
}
function deleteSysUser(id){
	layer.open({
		type:0,
		title:"删除用户信息",
		btn:["确定","取消"],
		content:"确定删除此用户吗？",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/deleteUser.do",
				data:"id="+id,
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getUserPage(1);
					}
				}
			})
		}
	})
}

function resetPassword(id){
	layer.open({
		type:0,
		title:"重置密码",
		btn:["确定","取消"],
		content:"是否重置用户密码？",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/resetPassword.do",
				data:"id="+id,
				success:function(data){
					layer.msg(data.msg)
				}
			})
		}
	})
}

function setUserRole(id){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../system/getUserRole.do",
		data:"id="+id,
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="userroletree" class="ztree"></ul>',
                btn:['确定','清空','取消'],
                success: function (layero, index) {
                	var setting = {
            				check: {
            					enable: true,
            				    chkStyle : "checkbox"
            				}
            		    };
                	var ztree=$.fn.zTree.init($("#userroletree"), setting, JSON.parse(data.roletree));
                	var userrolelist=data.userrole;
                	$.each(userrolelist,function(index,role){
                		var nodeList=ztree.getNodesByParam("id", role.roleId, null);
                		ztree.checkNode(nodeList[0],true,true);
                	})
                },
				yes:function(index){
	            	var zTree = $.fn.zTree.getZTreeObj("userroletree");
	            	var nodes = zTree.getCheckedNodes(true);
	            	var nstr="";
	            	for (var i=0, l=nodes.length; i<l; i++) {
	            		if(!nodes[i].isParent)	nstr+=nodes[i].id + ","
	    			}
	            	$.ajax({
	            		contenType:'application/json',
	            		Type:'POST',
	            		async:false,
	            		dataType:'json',
	            		url:"../system/setUserRole.do",
	            		data:"id="+id+"&rolestr="+nstr.substring(0,nstr.length-1),
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

function clockUser(id,type){
	layer.open({
		type:0,
		title:"锁定用户",
		btn:["确定","取消"],
		content:type==1?"是否锁定此用户？":"是否解锁此用户",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/clockUser.do",
				data:"id="+id+"&type="+type,
				success:function(data){
					if(data.code==200){
						layer.msg(data.msg)
						getUserPage(1);
					}
				}
			})
		}
	})
}

function clearSysUserInfo(){
	$("#empinfo-form input").val("");
}

function showEmpTree(){
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
                content: '<ul id="emptree" class="ztree"></ul>',
                btn:['确定','取消'],
                success: function (layero, index) {
                	var setting = {
            			callback:{
    			        	onClick:clickEmpNode
    			        }
                	};
                	var ztree=$.fn.zTree.init($("#emptree"), setting, JSON.parse(data.emptree));
                }
			})
			
		}
	})
}

function clickEmpNode(event, treeId, treeNode){
	if(treeNode.isemp){
		$("#userinfo-empid").val(treeNode.id)
		$("#userinfo-empname").val(treeNode.name)
		layer.close(layer.index)
	}
}

function tobeadmin(id,type){
	layer.open({
		type:0,
		title:"锁定用户",
		btn:["确定","取消"],
		content:type==1?"是否解除此用户系统管理员身份？":"是否设定此用户为系统管理员？",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				url:"../system/setAdmin.do",
				data:"id="+id+"&type="+type,
				success:function(data){
					if(data.code==200){
						layer.msg(data.msg)
						getUserPage(1);
					}
				}
			})
		}
	})
}