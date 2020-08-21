/**库存变更*/
package com.stone.equipsys.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.domain.StoreGoodsInventoryRecords;
import com.stone.equipsys.core.mapper.StoreGoodsInventoryRecordsMapper;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.StoreGoodsInventoryRecordsService;

@Controller
public class StoreChangeStorageController {

	@Resource
	private StoreGoodsInventoryRecordsMapper InventoryRecordsMapper;
	@Resource
	private StoreGoodsInventoryRecordsService InventoryRecordsService; 
	/**
	 * 	入库
	 * @param response
	 * @param request
	 * @param modelid
	 * @param changedate
	 * @param quantity
	 * @param price
	 * @param supplier
	 * @param remarks
	 */
	@RequestMapping(value = "changestorage/income")
	public void GoodsModelNumberIncome(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "modelid")Long modelid,
			@RequestParam(value="changedate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date changedate ,
			@RequestParam(value="quantity")int quantity,
			@RequestParam(value="price")float price,
			@RequestParam(value="supplier")String supplier,
			@RequestParam(value="remarks")String remarks,
			@RequestParam(value="changetype")int changetype) {
		SysUserModel user=(SysUserModel) request.getSession().getAttribute("SysUser");
		StoreGoodsInventoryRecords records=new 	StoreGoodsInventoryRecords(modelid,changedate,quantity,price,supplier,remarks);
		boolean flag=InventoryRecordsService.incomeRecord(records, user,changetype);
		Map<String, Object> res = new HashMap<String, Object>();
		if(flag) {
			res.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "提交成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "提交失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	/**
	 * 	出库
	 * @param response
	 * @param request
	 * @param modelid
	 * @param changedate
	 * @param quantity
	 * @param deptid
	 * @param use
	 * @param remarks
	 */
	 
	@RequestMapping(value = "changestorage/outcome")
	public void GoodsModelNumberOutcome(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "modelid")Long modelid,
			@RequestParam(value="changedate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date changedate,
			@RequestParam(value="quantity")int quantity,
			@RequestParam(value="deptid")Long deptid,
			@RequestParam(value="use")String use,
			@RequestParam(value="remarks")String remarks,
			@RequestParam(value="changetype")int changetype) {
		SysUserModel user=(SysUserModel) request.getSession().getAttribute("SysUser");
		boolean flag=InventoryRecordsService.outcomeRecord(modelid, quantity, changetype, user,remarks);
		Map<String, Object> res = new HashMap<String, Object>();
		if(flag) {
			res.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "提交成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "提交失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
