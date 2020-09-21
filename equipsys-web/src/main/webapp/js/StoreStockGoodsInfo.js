/**
 */
function getStockGoodsTree(e){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../storestockgoodsinfo/search.do",
		success:function(data){
			var setting={
/*					simpleData : {
			            enable : true, // 设置启用简单数据格式[{id, pId, name}, {id, pId, name}]   
			        },*/
			        callback:{
			        	onClick:clickStockGoodsNode
			        }
			};
			$.fn.zTree.init($(e), setting, JSON.parse(data));
			fuzzySearch('stockgoods-tree','#stockgoods-search #sreach-btn',null,true);
		}
	})
}

function clickStockGoodsNode(event, treeId, treeNode){
	var parentNode=treeNode.getParentNode();
	if(parentNode!=null){
		$("#storegoods-form #storegoods-id").val(treeNode.id);
		$("#storegoods-form #goodstype-id").val(parentNode.id);
		$("#storegoodsinfo-table #goodsname-label").text(treeNode.name);
		$("#storegoodsinfo-table #goodstypename-label").text(parentNode.name);
		$("#storegoodsinfo-table #remarks-label").text(treeNode.remarks);
		$("#goodsmodelnumberinfo-form #goods-id").val(treeNode.id)
		getModelNumberList(1)
		$("#addmodelnumber").attr("disabled",false);
	}else{
		$("#addmodelnumber").attr("disabled",true);
	}
}

function showGoodsType(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../goodstype/searchfortree.do",
		success:function(data){
			var DataNodes=JSON.parse(data);
			var setting={
			        callback:{
			        	onClick:getGoodsTypeInfo,
			        }
			};
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '280px', '400px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="typeztree" class="ztree"></ul>',
                success: function (layero, index) {
                	$.fn.zTree.init($("#typeztree"), setting, DataNodes);
                }
			})
		}
	})
}

function getGoodsTypeInfo(event, treeId, treeNode){
	$("#goodsmodelnumberinfo-form #stockgoods-id").val(treeNode.id)
	$("#storegoods-form #goodstype-id").val(treeNode.id);
	$("#storegoods-form #goodstypename").val(treeNode.name);
	layer.close(layer.index)
}

function showStockGoodsInfo(type){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:'库存物料信息',
        area:[ '660px', '280px' ],
        btn:['保存','关闭'],
        content: $("#storegoods-form"),
        success: function (layero, index){
        	$("#storegoods-form").attr("data-type",type);
        	if(type=="1"){
        		$("#storegoods-form #goodsname").val($("#storegoodsinfo-table #goodsname-label").text())
        		$("#storegoods-form #goodstypename").val($("#storegoodsinfo-table #goodstypename-label").text())
        		$("#storegoods-form #remarks").val($("#storegoodsinfo-table #remarks-label").text())
        	}
        },
        yes:function(index){
        	saveStoreGoods(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearStoreGoodsData();
        	$("#storegoods-form").hide();        	
        }
	})
}

function saveStoreGoods(index){
	if(checkStoreGoodsData()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			data:createStoreGoodsData(),
			url:"../storestockgoodsinfo/save.do",
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg);
					clearStoreGoodsData();
					layer.close(index);
					getStockGoodsTree("#stockgoods-tree")
				}
			}
		})
	}
}

function deleteStockGoodsInfo(){
	layer.msg("因删除功能存在逻辑关系，故延后开发此功能")
}
function createStoreGoodsData(){
	return $("#storegoods-form").attr("data-type")=="1"?$("#storegoods-form").serialize():"goodsname="+$("#storegoods-form #goodsname").val()+"&typeid="+$("#storegoods-form #goodstype-id").val()+"&remarks="+$("#storegoods-form #remarks").val();
}

function checkStoreGoodsData(){
	if($("#storegoods-form #goodsname").val()==""){
		layer.msg("请填写物料名称");
		return false;
	}
	if($("#storegoods-form #goodstypename").val()==""){
		layer.msg("请选择物料类型")
		return false;		
	}
	return true;
}

function clearStoreGoodsData(){
	$("#storegoods-form input,textarea").val("");
}


function showGoodsModelNumberInfo(type,id){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?"新增物料规格信息":"修改物料规格信息",
        area:[ '520px', '410px' ],
        btn:['保存','关闭'],
        content: $("#goodsmodelnumberinfo-form"),
        success: function (layero, index){
			$("#goodsmodelnumberinfo-form #goods-id").val($("#storegoods-form #storegoods-id").val())
        	$("#goodsmodelnumberinfo-form #stockgoodsname").text($("#storegoods-info #goodsname-label").text())
        	$("#goodsmodelnumberinfo-form #goodstypename").text($("#storegoods-info #goodstypename-label").text())
			if(type!=0){
				$.ajax({
					contenType:'application/json',
					Type:'POST',
					dataType:'json',
					data:"id="+id,
					url:"../modelnumber/getDetailInfo.do",
					success:function(data){
						if(data!=null){
							$("#goodsmodelnumberinfo-form #id").val(data.model.id)
							$("#goodsmodelnumberinfo-form #goods-id").val(data.model.goodsId)
							$("#goodsmodelnumberinfo-form #modelname").val(data.model.modelNumberName)
							$("#goodsmodelnumberinfo-form #unit").val(data.model.unit)
							$("#goodsmodelnumberinfo-form #remarks").val(data.model.remarks)
							
						}
					}
				})
			}
        },
        yes:function(index){
        	saveStockGoodsModelNumberInfo(index);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearStockGoodsModelNumberInfo();
        	$("#goodsmodelnumberinfo-form").hide();
        }
	})
}

function saveStockGoodsModelNumberInfo(index){
	if(checkStockGoodsModelNumberInfo()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			data:createrStockGoodsModelNumberInfo(),
			url:"../modelnumber/save.do",
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg);
					clearGoodsModelInfo();
					layer.close(index);
					//getStockGoodsTree("#stockgoods-tree")
					getModelNumberList(1)
				}
			}
		})
	}
}

function getModelNumberList(pagenum){
	var gid=$("#storegoods-form #storegoods-id").val();
	var param={
			goodsid:gid,
			pagenum:pagenum,
			pageSize:8
	}
	$("#modelnumber-list").load("../modelnumber/search.do",param)
}
function checkStockGoodsModelNumberInfo(){
	if($("#goodsmodelnumberinfo-form #modelname").val()==""){
		layer.msg("请填写物料规格名称");
		return false;
	}
	if($("#goodsmodelnumberinfo-form #storename").val()==""){
		layer.msg("请选择库存仓位");
		return false;
	}
	if($("#goodsmodelnumberinfo-form #unit").val()==""){
		layer.msg("请填写库存单位");
		return false;
	}
	return true;
}

function clearGoodsModelInfo(){
	$("#goodsmodelnumberinfo-form input,textarea").val("");
}
/**选择仓位 */
function setstore(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../storeinfo/search.do",
		success:function(data){
			layer.open({
				type: 1,
                skin: 'layui-layer-demo', //样式类名
                anim: 2,
                area : [ '280px', '400px' ],
                shadeClose: true, //开启遮罩关闭
                content: '<ul id="storeztree" class="ztree"></ul>',
				btn:['确定','清空','取消'],
                success: function (layero, index) {
					var DataNodes=JSON.parse(data);
					var setting={
						check: {
	    					enable: true,
	    				    chkStyle : "checkbox",
							chkboxType: { "N": "", "Y": "" },
        				}
					};
                	var ztree=$.fn.zTree.init($("#storeztree"), setting, DataNodes);
					var stores=$("#goodsmodelnumberinfo-form #storeid").val().split(',');
                	var names=$("#goodsmodelnumberinfo-form #storename").val().split(',');
                	for(var i = 0;i<stores.length;i++){
                		var nodeList=ztree.getNodesByParam("name", names[i], null);
                		for( var n=0;n<nodeList.length; n++ ){
                			if(nodeList[n].id==ids[i]){
                				ztree.checkNode(nodeList[n],true,true);
                			}
                		}
                	}
                },
				yes:function(index){
                	var zTree = $.fn.zTree.getZTreeObj("storeztree");
                	var nodes = zTree.getCheckedNodes(true),
                	v = "";
                	k="";
        			for (var i=0, l=nodes.length; i<l; i++) {
        				v += nodes[i].name + ",";
        				k += nodes[i].id + ",";
        			}
        			if (v.length > 0 ) v = v.substring(0, v.length-1);
        			if (k.length > 0 ) k = k.substring(0, k.length-1);
                	$("#goodsmodelnumberinfo-form #storeid").val(k);
                	$("#goodsmodelnumberinfo-form #storename").val(v);
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

/*function getStoreInfo(event, treeId, treeNode){
	$("#goodsmodelnumberinfo-form #storename").val(treeNode.name)
	$("#goodsmodelnumberinfo-form #storeid").val(treeNode.id)
	layer.close(layer.index)
}*/
function createrStockGoodsModelNumberInfo(){
	return $("#goodsmodelnumberinfo-form").serialize()
}
function clearStockGoodsModelNumberInfo(){
	$("#goodsmodelnumberinfo-form input,textarea").val("");
}


/**
 * 库存变更窗口
 * @param type	变更类型 0:入库 1:出库
 * @returns
 */
function showChangeStorage(type,id,name,unit){
	layer.open({
		type:1,
        skin:'layui-layer-demo', //样式类名
        anim:2,
        shade: 0.3,
        title:type==0?"入库":"出库",
        area:[ '520px', '460px' ],
        btn:['保存','关闭'],
        content: $("#ChangeStorage-form"),
        success: function (layero, index){
			$("#ChangeStorage-form #changedate").html(type==0?"入库时间":"出库时间");
			$("#ChangeStorage-form #changetype").html(type==0?"入库类型":"出库类型");
			$("#ChangeStorage-form #type").val(type);
			$("#ChangeStorage-form #modelid").val(id);
			$("#ChangeStorage-form #goodsname").html($("#storegoods-info #goodsname-label").html());
			$("#ChangeStorage-form #goodstype").html($("#storegoods-info #goodstypename-label").html());
			$("#ChangeStorage-form #mnname").html(name);
			$("#ChangeStorage-form #unit").html(unit)
			getChangeType(type)
        	if(type==0){
				$("#ChangeStorage-form .outcome").hide()
				$("#ChangeStorage-form .income").show()
			}else{
				$("#ChangeStorage-form .outcome").show()
				$("#ChangeStorage-form .income").hide()
			}
        },
        yes:function(index){
        	sumitChangeStorageInfo(index,type);
        },
        btn2:function(index){
        	layer.close(index);
        },
        end: function(index, layero){
        	clearChangeStorageInfo();
        	$("#ChangeStorage-form").hide();
        }
	})
}

function sumitChangeStorageInfo(index,type){
	if(checkChangeStorageInfo(type)){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			data:createChangeStorageInfo(),
			url:type==0?"../changestorage/income.do":"../changestorage/outcome.do",
			success:function(data){
				if(data.code==200){
					layer.msg(data.msg);
					clearChangeStorageInfo();
					layer.close(index);
					getModelNumberList(1);
				}
			}
		})
	}
}

function totalprice(){
	var price=$("#ChangeStorage-form #price").val()
	var quantity=$("#ChangeStorage-form #quantity").val()
	console.log(price+"---"+quantity)
	$("#ChangeStorage-form #total").val(price*quantity)
}

function createChangeStorageInfo(){
	return $("#ChangeStorage-form").serialize()
}

function clearChangeStorageInfo(){
	$("#ChangeStorage-form input[id!=changedate-input],textarea").val("");
	$("#ChangeStorage-form #changetype-select").empty();
}

function checkChangeStorageInfo(type){
	$("#ChangeStorage-form ")
	if($("#ChangeStorage-form changedate-input").val()==""){
		layer.msg(type==0?"请选择入库日期":"请选择出库日期");
		return false;
	}
	if($("#ChangeStorage-form #changetype-select").val()=="-1"){
		layer.msg(type==0?"请选择入库类型":"请选择出库类型");
		return false;
	}
	if($("#ChangeStorage-form #quantity").val()==""){
		layer.msg(type==0?"请填写入库数量":"请填写出库数量");
		return false;
	}
	if(type==0){
		if($("#ChangeStorage-form #price").val()==""){
			layer.msg("请填写入库单价");
			return false;
		}
	}else{
		if($("#ChangeStorage-form #employeename").val()==""){
			layer.msg("请选择领料人");
			return false;
		}
	}
	return true;
}
/**获取变更类型*/
function getChangeType(type){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		data:type==0?"code=income":"code=outcome",
		url:"../dictionary/list.do",
		success:function(data){
			$("#ChangeStorage-form #changetype-select").append("<option value='-1'>请选择类型</option>")
			if(data!=null){
				$.each(data,function(index,type){
					$("#ChangeStorage-form #changetype-select").append("<option value='"+type.dataNumber+"'>"+type.dataName+"</option>")
				})
			}
		}
	})
}

function showUnPostEmpTree(){
	$.ajax({
		contenType:'application/json',
		Type:'POST',
		dataType:'json',
		url:"../employee/getUnPostEmployeeTree.do",
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
    			        	onClick:setEmployee
    			        }
                	};
                	var ztree=$.fn.zTree.init($("#emptree"), setting, JSON.parse(data.emptree));
                }
			})
			
		}
	})
}
function setEmployee(event, treeId, treeNode){
	if(treeNode.isemp){
		$("#ChangeStorage-form #employeeid").val(treeNode.id)
		$("#ChangeStorage-form #employeename").val(treeNode.name)
		var parentnode=treeNode.getParentNode();
		$("#ChangeStorage-form #deptid").val(parentnode.id)
		$("#ChangeStorage-form #deptname").val(parentnode.name)
		layer.close(layer.index)
	}
}
/**
 * 查看库存变更记录
 * @returns
 */
function queryChangeStorageLog(){
	
}