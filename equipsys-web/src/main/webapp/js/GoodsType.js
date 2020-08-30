function getGoodsTypeList(){
	$("#GoodsTypeList-div").load("../goodstype/search.do")
}

function insertGoodsType(){
	if(checkGoodsType()){
		$.ajax({
			contenType:'application/json',
			Type:'POST',
			dataType:'json',
			url:"../goodstype/save.do",
			data:createGoodsTypeData(),
			success:function(data){
				layer.msg(data.msg)
				if(data.code==200){
					clearGoodsType();
					changeGoodsTypeBtnState(true)
					getGoodsTypeList();
				}
			}
		})
	}
}
	
function updateGoodsType(){
	if(checkGoodsType()){
		layer.open({
			type:0,
			title:"物料类型修改",
			btn:["确定","取消"],
			content:"确定修改此信息吗？",
			yes:function(){
				$.ajax({
					contenType:'application/json',
					Type:'POST',
					dataType:'json',
					url:"../goodstype/save.do",
					data:createGoodsTypeData(),
					success:function(data){
						layer.msg(data.msg)
						if(data.code==200){
							clearGoodsType();
							changeGoodsTypeBtnState(true)
							getGoodsTypeList();
						}
					}
				})
			}
		})
	}
}

function deleteGoodsType(){
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
				url:"../goodstype/delete.do",
				data:"id="+$("#goodsid-info").val(),
				success:function(data){
					layer.msg(data.msg)
					if(data.code==200){
						getRoleTree("#role-tree");
						clearRoleInfo();
						getGoodsTypeList();
						changeGoodsTypeBtnState(true)
					}
				}
			})
		}
	})
}

function getGoodsTypeInfo(obj){
	$("#goodstypeinfo-id").val($(obj).val());
	$("#goodstypeinfo-name").val($(obj).text());
	$("#goodstypeinfo-order").val($(obj).attr("data-order"));
	$("#goodstypeinfo-quickcode").val($(obj).attr("data-quickcode"));
	$("#goodstypeinfo-remarks").val($(obj).attr("data-remarks"));
	changeGoodsTypeBtnState(false)
}

function resetGoodsType(){
	clearGoodsType();
	changeGoodsTypeBtnState(true)
}

function createGoodsTypeData(){
	var data=$("#goodstype-form").serialize();
	return data;
}

function checkGoodsType(){
	if($("#goodstypeinfo-name").val()==""){
		layer.msg("物料类型名称不能为空")
		return false;
	}
	return true;
}

/*$("#goodstype-form #goodstypename-info").on("input",function(){
	var tcode=makePy($(this).val());
	$("#goodstypeorder-quickcode").val(tcode.toLowerCase(tcode))
})*/
function clearGoodsType(){
	$("#goodstype-form input,textarea").val("");
}

function changeGoodsTypeBtnState(state){
	$("#goodstype-btn #insert-btn").attr("disabled",state==true?false:true);
	$("#goodstype-btn #update-btn").attr("disabled",state);
	$("#goodstype-btn #delete-btn").attr("disabled",state);
}