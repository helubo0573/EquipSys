/**
 * 	@author 用于动态生成树形菜单和查询框文本内容
 *	@param id		查询框divid
 *	@param inputid	查询输入框id
 * 	@param inputpr	查询输入框内容提示
 * 	@param treeid 	树形菜单id
 */
function CreateTreeSearchTool(id,inputid,inputpr,treeid){
	var Html=	"<div style='padding:10px'>"+
					"<div id='"+id+"' class='input-group input-group-sm' style='width: 180px;'>"+
	            		"<input type='text' id='"+inputid+"' class='form-control' style='width: 150px;' placeholder='"+inputpr+"'>"+
	            		"<span class='input-group-addon point btn' id='sreach-btn'>"+
	            			"<i class='layui-icon' style='font-size: 12px;'>&#xe615;</i>"+
	            		"</span>"+
	        		"</div>"+
					"<ul id='treeid"+treeid+"' class='ztree' style='color: #555'></ul>"+
				"</div>"
	return Html;
}