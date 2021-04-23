<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript" src="../js/EquipServicingApplication.js?d=202009143"></script>
<script type="text/javascript" src="../plugins/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	getEquipServicingApplicationList();
});
function test(){
	$("#servicingAppManage-table").dataTable().fnDestroy();
	getEquipServicingApplicationList();
}
function getEquipServicingApplicationList(){
	var oTable = $('#servicingAppManage-table').dataTable( {
		"bLengthChange": false, //更改显示记录数选项
       	"iDisplayLength" : 10, //默认显示的记录数
        "bPaginate" : true, //是否显示（应用）分页器
        "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数
        "bSort" : true, //是否启动各个字段的排序功能
       /*  "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",//定义表格的显示方式 */
        "sPaginationType": "full_numbers",
        "bFilter" : false, //是否启动过滤、搜索功能
            "aoColumns" : [{
                "mData" : "applicationTime",	//列标识，和服务器返回数据中的属性名称对应
                "sTitle" : "申请时间",//列标题
                "sDefaultContent" : "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                "render":function(data, type, row){
                	return new Date(data).Format("yyyy-MM-dd");
                }
            }, {
                "mData" : "equipName",
                "sTitle" : "设备名称",
                "sWidth":"10%",//定义列宽度，以百分比表示
                "sDefaultContent" : ""
            }, {
                "mData" : "backfireTime",
                "sTitle" : "故障时间",
                "sDefaultContent" : "",
                "render":function(data, type, row){
                	return new Date(data).Format("yyyy-MM-dd");
                }
            }, {
                "mData" : "proposDept",
                "sTitle" : "部门",
                "sDefaultContent" : "",
                "bSortable":false	//此列不需要排序
            },  {
                "mData" : "status",
                "sTitle" : "状态",
                "sDefaultContent" : "",
            },{//倒数第一列
                "targets":-1,
                "sTitle" : "操作",
                "bSortable": false,
                render: function(data, type, row) {
                    var html ="<i class='lni lni-write' titel='修改申请单'></i>&nbsp;&nbsp;&nbsp;"+
                    	"<i class='lni lni-trash' titel='撤销申请单'></i>&nbsp;&nbsp;&nbsp;"+
                    	"<i class='lni lni-checkmark-circle'></i>&nbsp;&nbsp;&nbsp;"+
                    	"<i class='lni lni-windows'></i>"
                    return html;
                }
            }],
            "oLanguage": { //国际化配置
                "sProcessing" : "正在获取数据，请稍后...","sLengthMenu" : "显示 _MENU_ 条","sZeroRecords" : "没有您要搜索的内容","sInfo" : "从 _START_ 到  _END_ 条记录 总显示记录数为 _TOTAL_ 条","sInfoEmpty" : "记录数为0","sInfoFiltered" : "(共显示 _MAX_ 条数据)",  
                "sInfoPostFix" : "","oPaginate": {"sFirst" : "第一页","sPrevious" : "上一页","sNext" : "下一页","sLast" : "最后一页"  
                }
            },/** 修改状态值 */
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                if (aData.response_type == 'video')
                    $('td:eq(1)', nRow).html('视频回复');
                return nRow;
            },/** 向服务器传递的参数*/
            "fnServerParams": function ( aoData ) {
                aoData.push( 
                		{ "name": "equipname", "value": $("#search-equipname").val() }
                		//{ "name": "responseType", "value": $("#search-responseType").val() }
                		);
              } ,
             //请求url
            "sAjaxSource" : "../EquipServicingApplication/searchforjson.do",
                //服务器端，数据回调处理
            "fnServerData" : function(sSource, aDataSet, fnCallback) {
            	$.ajax({
                    "dataType" : 'json',
                    "type" : "post",
                    "url" : sSource,
                    "data" : aDataSet,
                    "success" : function(resp){
                    	fnCallback(resp);
                    }
                });
            }
    });
}
</script>
<body>
	<div class="card-title">
		<h4 class="mb-0">设备维修申请管理</h4>
	</div>
	<div class="table-responsive">
		<div class="form-row col-lg-12">
			<div class="form-group col-md-2">
				<div class="input-group input-group-sm">
					<div class="input-group-prepend"><span class="input-group-text">设备名称</span>
					</div>
					<input type="text" class="form-control border-left-0" id="search-equipname" placeholder="Last Name">
				</div>
			</div>
			<div class="form-group col-md-2">
				<div class="input-group input-group-sm">
					<div class="input-group-prepend"><span class="input-group-text">申请时间</span>
					</div>
					<input type="text" class="form-control border-left-0" placeholder="Last Name">
				</div>
			</div>
			<div class="form-group col-md-3">
				<button type="button" class="btn btn-info px-5 radius-25 btn-sm" id="EquipServicingApplication" onclick="test()">搜索</button>
			</div>
		</div>
		<div id="eqsrvapp-btndiv btn-div btn-Rdiv" class="form-row col-lg-12">
			<div class="form-group col-md-3">
				<button class="btn btn-success" onclick="showApplicationInfo('0')">新增设备维修单</button>
			</div>
		</div>
		<table id="servicingAppManage-table" class="table compact table-stripe table-hover table-striped table-bordered" style="width:100%"><thead></thead><tbody></tbody></table>
	</div>
	<%-- <div class="body-bdiv">
		<div class="title-div">
			设备维修申请管理
		</div>
		<div id="servicingAppManage-seach" class="box-div form-inline" style="overflow: hidden;text-align: left;">
			<div class="input-group input-group-sm col-lg-3">
		        <span class="th input-group-addon">设备名称</span>
		        <input class="form-control" id="search-equipname" placeholder="输入查询关键字">
		    </div>
		    <div class="input-group input-group-sm col-lg-4">
		        <span class="th input-group-addon">申请时间</span>
		        <input class="layui-input form-control point layedate date-search" id="search-sappdate" style="width: 95px" name="application_time" id="application_time" placeholder="查询开始时间" readonly>
		        <span class="th input-group-addon">-</span>
		        <input class="layui-input form-control point layedate date-search" id="search-eappdate" style="width: 95px" name="application_time" id="application_time" placeholder="查询结束时间" readonly>
		    </div>
		    <div class="input-group input-group-sm col-lg-4">
		        <span class="th input-group-addon">故障时间</span>
		        <input class="layui-input form-control point layedate date-search" id="search-sbackfiredate" style="width: 95px" name="application_time" id="application_time" placeholder="查询开始时间" readonly>
		        <span class="th input-group-addon">-</span>
		        <input class="layui-input form-control point layedate date-search" id="search-ebackfiredate" style="width: 95px" name="application_time" id="application_time" placeholder="查询结束时间" readonly>
		    </div>
		    <button class="search-btn btn btn-info right" style="height: 100%;" onclick="getEquipServicingApplicationList(1)">查询</button>
		</div>
		<div id="eqsrvapp-btndiv btn-div btn-Rdiv" style="margin:10px 0px;text-align: right;">
				<button class="btn btn-success" onclick="showApplicationInfo('0')">新增设备维修单</button>
		</div>
		<div id="servicingAppManage-list" class="box-div form-inline" style="height: 600px;">
			<%@include file="List.jsp" %>
		</div>
	</div>
	<form id="servicingAppManage-info" class="form-horizontal box-div" style="display: none;">
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="dept-id">
		<input type="hidden" id="equip-id" name="equipid">
		<table class="table table-bordered layeropen">
			<tr>
				<th width="80px;">申请部门</th><td><label id="dept"></label></td>
				<th width="80px;">申请人</th><td><label id="proposer"></label></td>
			</tr>
			<tr>
				<th>申请时间</th><td><input class="layui-input form-control point needing layedate" name="application_time" id="application_time" placeholder="yyyy-MM-dd" readonly></td>
				<th>故障时间</th><td><input class="layui-input form-control point needing layedate" name="backfire_time" id="backfire_time" placeholder="yyyy-MM-dd" readonly></td>
			</tr>
			<tr>
				<th>设备名称</th>
				<td colspan="3">
					<input class="form-control point" data-parent="" id="equip-name" readonly onclick="setServicingEquip()">
				</td>
			</tr>
			<tr>				
				<th>设备型号</th><td colspan="3"><label id="modelnumber"></label></td>
			</tr>
			<tr>
				<th>所在地点</th><td colspan="3"><label id="location"></label></td>
			</tr>
			<tr>
				<th>故障简述</th>
				<td colspan="3" style="vertical-align: middle;">
					<textarea class="form-control" id="remarks" name="remarks" rows="4" style="resize:none;"></textarea>
				</td>
			</tr>
		</table>
	</form> --%>
</body>
</html>