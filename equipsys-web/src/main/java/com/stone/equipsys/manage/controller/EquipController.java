package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.domain.EquipInfo;
import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.mapper.EquipInfoMapper;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.model.EquipInfoModel;
import com.stone.equipsys.core.service.EquipInfoService;
import com.stone.equipsys.core.service.EquipMpService;
import com.stone.equipsys.core.service.EquipOpService;
import com.stone.equipsys.core.service.OrgDeptService;
import com.stone.equipsys.manage.TreeUtil.EquipUtile;
@Controller
@RequiresPermissions("sys:admin")
@RequestMapping("equip")
public class EquipController {

	@Resource
	private OrgDeptService DeptService;
	@Resource
	private EquipOpService EquipOpService;
	@Resource
	private EquipMpService EquipMpService;
	@Resource
	private EquipInfoService EquipServoce;
	@Resource
	private OrgDeptMapper deptmapper; 
	@Resource
	private EquipInfoMapper equipmapper;
	
	@RequestMapping("/manageequip")
	public String toEquipPage(HttpServletRequest request) {
		HashMap<String, Object> deptparam=new HashMap<String, Object>();
		deptparam.put("deptType", 0);
		List<OrgDept> deptlist=deptmapper.listSelective(deptparam);
		request.setAttribute("deptlist", deptlist);
		return PathConstant.EquipInfoManage;
	}
	@RequestMapping("/saveequip")
	@RequiresPermissions("equip:info:save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "id",defaultValue = "0")int id,
			@RequestParam(value = "name")String name,
			@RequestParam(value = "code")String code,
			@RequestParam(value = "equipnumber")String equipnumber,
			@RequestParam(value = "parent",defaultValue = "0")int parent,
			@RequestParam(value = "level")int level,
			@RequestParam(value = "enabledate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date enabledate,
			@RequestParam(value = "attrdept")int attrdept,
			@RequestParam(value = "supplier")String supplier,
			@RequestParam(value = "suppliernumber")String suppliernumber,
			@RequestParam(value = "location")String location,
			@RequestParam(value = "remarks")String remarks,
			@RequestParam(value = "op")String op,
			@RequestParam(value = "mp")String mp) {
		EquipInfo equip=new EquipInfo(id,name,code,equipnumber,parent,level,enabledate,attrdept,supplier,suppliernumber,location,remarks);
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			if(id==0) {
				id=EquipServoce.insertReturnId(equip);
			}else {
				id=EquipServoce.updateRetuenId(equip);
			}
			EquipOpService.saveOpToList(op.length()>0?StringUtil.convertStringToIntegerList(op, ","):null, id);
			EquipMpService.saveMpToList(mp.length()>0?StringUtil.convertStringToIntegerList(mp, ","):null, id);
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} catch (Exception e) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);

	}

	@RequestMapping("/getequiptree")
	public void search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="type",defaultValue = "4")int type,
			@RequestParam(value = "dept",defaultValue = "0")int deptid)
			throws UnsupportedEncodingException, IOException {
		String id=request.getParameter("id");
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("maxlevel", type);
		map.put("logicalState", false);
		if(deptid!=0) map.put("attrDept", deptid);
		if(!"".equals(id) && id!=null) {
			map.put("debarid", Integer.parseInt(id));
		}
		String treejson=JSONObject.toJSONString(EquipUtile.setTreeIcon(EquipUtile.createEquipTree(equipmapper.listSelective(map))));
		Map<String, Object> res = new HashMap<String, Object>();
		if(treejson!=null) {
			res.put("equiptree", treejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/delete")
	@RequiresPermissions("equip:info:delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			EquipServoce.deleteEquipById(id);
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/getequipinfo")
	public void getEquipInfo(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long equipid) {
		Map<String,Object> res=new HashMap<String,Object>();
		try {
			EquipInfoModel equip=EquipServoce.getEquipExtInfoById(equipid);
			int eid=Integer.parseInt(String.valueOf(equip.getId()));
			HashMap<String,String> opmap=EquipOpService.getOpByEquipid(eid);
			HashMap<String,String> mpmap=EquipMpService.getMpByEquipid(eid);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			res.put("equip", equip);
			res.put("opstr", opmap);
			res.put("mpstr", mpmap);
			res.put("enabledate", sdf.format(equip.getEnableDate()));
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
