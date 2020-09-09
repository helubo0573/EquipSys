package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.common.util.Tree.TreeObject;
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
import com.stone.equipsys.manage.TreeUtil.EquipUtile.EquipObject;
@Controller
@RequiresPermissions("sys:admin")
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
	@RequestMapping(value="equip/manageequip")
	public String toEquipPage(HttpServletRequest request) {
		HashMap<String, Object> deptparam=new HashMap<String, Object>();
		deptparam.put("deptType", 0);
		List<OrgDept> deptlist=deptmapper.listSelective(deptparam);
		request.setAttribute("deptlist", deptlist);
		return PathConstant.EquipInfoManage;
	}
	@RequestMapping(value="equip/saveequip")
	@RequiresPermissions("equip:info:save")
	public void save(HttpServletResponse response, HttpServletRequest request) {
		String eid=request.getParameter("id");
		String op=request.getParameter("op");
		String mp=request.getParameter("mp");
		System.out.println("id="+eid);
		int id=0;
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			if("".equals(eid)) {
				id=EquipServoce.insertReturnId(request);
			}else {
				id=EquipServoce.updateRetuenId(request);
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

	@RequestMapping("equip/getequiptree")
	public void search(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {
		int type=Integer.parseInt(request.getParameter("type"));
		String id=request.getParameter("id");
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("maxlevel", type);
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

	@Transactional(propagation=Propagation.REQUIRED)
	@RequiresPermissions("equip:info:delete")
	public void delete(HttpServletResponse response, HttpServletRequest request) {
		int eid=Integer.parseInt(request.getParameter("id"));
		EquipMpService.deleteMpByEquipId(eid);
		EquipOpService.deleteOpByEquipId(eid);
		EquipServoce.deleteEquipById(Long.parseLong(request.getParameter("id")));
	}

	@SuppressWarnings("unused")
	@RequestMapping(value="equip/getequipinfo")
	public void getEquipInfo(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long equipid) {
		EquipInfoModel equip=EquipServoce.getEquipExtInfoById(equipid);
		int eid=Integer.parseInt(String.valueOf(equip.getId()));
		HashMap<String,String> opmap=EquipOpService.getOpByEquipid(eid);
		HashMap<String,String> mpmap=EquipMpService.getMpByEquipid(eid);
		Map<String,Object> res=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(equip!=null) {
			res.put("equip", equip);
			res.put("opstr", opmap);
			res.put("mpstr", mpmap);
			res.put("enabledate", sdf.format(equip.getEnableDate()));
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="equip/manageparts")
	@RequiresPermissions("equip:info:setparts")
	public void manageSetParts(HttpServletResponse response, HttpServletRequest request) {
		
	}
}
