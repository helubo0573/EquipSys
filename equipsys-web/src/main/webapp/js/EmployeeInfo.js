/**
 * 
 */
function showEmployeeInfo(type,empid){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==1?'员工信息维护':'新员工入职',
        area:[ '540px', '365px' ],
        btn:['保存','关闭'],
        content: $("#empinfo-form"),
        success: function (layero, index){
        	if(type==1){
        		$.ajax({
        			contenType:'application/json',
					Type:'POST',
					dataType:'json',
					data:"id="+empid,
					url:"../employee/findEmpById.do",
					success:function(data){
						if(data.employee!=null){
							var emp=data.employee;
							$("#empinfo-id").val(emp.id)
							$("#empinfo-deptid").val(emp.deptid)
							$("#empinfo-postid").val(emp.postid)
							$("#empinfo-name").val(emp.empName)
							$('input:radio[name=empinfo-sex]')[emp.empSex].checked = true;
							$("#empinfo-dept").val(emp.deptname);
							$("#empinfo-post").val(emp.postname);
							$("#empinfo-mobil").val(emp.empMobil);
							$("#empinfo-indate").val(data.indate);
							$("#empinfo-remarks").val(emp.empRemarks);							
						}
					}
        		})	
        	}
        },
        yes:function(index){
        	saveEmployee(index)
        },
        end: function(index, layero){
        	clearEmployeeInfo()
        	$("#empinfo-form").hide();        	
        }
	})
}

function saveEmployee(index){
	if(checkEmpInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			data:createEmpData(),
			url:"../employee/save.do",
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg)
					layer.close(index)
				}
			}
		})
	}
}

function checkEmpInfo(){
	if($("#empinfo-name").val()==""){
		layer.msg("员工姓名不能为空");
		return false;
	}
	if($("input:radio[name=empinfo-sex]:checked").val()==null){
		layer.msg("请选择员工性别");
		return false;
	}
	if($("#empinfo-post").val()==""){
		layer.msg("清选择员工岗位");
		return false;
	}
	if($("#empinfo-indate").val()==""){
		layer.msg("入职时间不能为空")
		return false;
	}
	return true;
}
function createEmpData(){
	var data=$("#empinfo-form").serialize();
	console.log(data);
	return data;
}
function getEmployeePage(pagenum){
	var param={
			empname:$("#search-name").val(),
			deptname:$("#search-dept").val(),
			postname:$("#search-post").val(),
			pageSize:12,
			current:pagenum
	}
	$("#emp-listdiv").load("../employee/search.do",param);
}

function deleteEmployee(id,name){
	layer.open({
		type:0,
		title:"员工离职",
		btn:["确定","取消"],
		content:"是否确定将员工: "+name+" 进行离职操作?",
		yes:function(){
			$.ajax({
				contenType:'application/json',
				Type:'POST',
				dataType:'json',
				data:"id="+id,
				url:"../employee/delete.do",
				success:function(data){
					layer.msg(data.msg)
					getEmployeePage(1)
				}
			})
		}
	})
}
function showdepttree(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../dept/getdepttree.do",
		data:"type=1",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '400px', '500px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="posttree" class="ztree"></ul>',
                btn:['确定','取消'],
                success: function (layero, index) {
                	var setting = {
            			callback:{
    			        	onClick:clickPostNode
    			        }
                	};
                	var ztree=$.fn.zTree.init($("#posttree"), setting, JSON.parse(data.depttree));
                }
			})
			
		}
	})
}
function clickPostNode(event, treeId, treeNode){
	if(treeNode.depttype==1){
		$("#empinfo-post").val(treeNode.name)
		$("#empinfo-postid").val(treeNode.id)
		var parent=treeNode.getParentNode();
		$("#empinfo-dept").val(parent.name)
		$("#empinfo-deptid").val(parent.id)
		layer.close(layer.index)
	}
}

function clearEmployeeInfo(){
	$("#empinfo-form input[type!=radio],textarea,select").val("");
	$("input:radio[name='empinfo-sex']:checked").prop("checked",false);
}