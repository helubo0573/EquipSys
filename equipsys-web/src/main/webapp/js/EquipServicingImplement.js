/**
 * 
 */
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
	//var params="equipname="+equipname+"&sappdate="+sappdate+"&eappdate="+eappdate+"&sbackfiredate="+sbackfiredate+"&ebackfiredate="+ebackfiredate+"&pageSize=12&current="+page;
	$("#servicingImpManage-list").load("../EquipServicingImplement/search.do",params);
}

function showImplementInfo(type){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?'新增设备维修单':"修改设备维修单",
        area:[ '540px', '460px' ],
        btn:type!=2?['保存','关闭']:"",
        content: $("#servicingImpManage-info"),
        success: function (layero, index){
			if(type==0){
				$.ajax({
        			contenType:'application/json',
        			Type:'POST',
        			dataType:'json',
        			url:"../employee/getEmployeeBySession.do",
        			success:function(data){
						$("#servicingImpManage-info #dept").html(data.deptname);
						$("#servicingImpManage-info #proposer").html(data.empname);
						$("#servicingImpManage-info #dept-id").html(data.deptid);
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
        	clearApplicationInfo();
        	$("#servicingImpManage-info").hide();        	
        }		
	})
}