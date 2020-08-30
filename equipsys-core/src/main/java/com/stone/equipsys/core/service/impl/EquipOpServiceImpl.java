package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipOpMapper;
import com.stone.equipsys.core.model.EquipOpModel;
import com.stone.equipsys.core.domain.EquipOp;
import com.stone.equipsys.core.service.EquipOpService;


/**
 * 	设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:24:08
 */
 
@Service("equipOpService")
public class EquipOpServiceImpl extends BaseServiceImpl<EquipOp, Long> implements EquipOpService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipOpServiceImpl.class);
   
    @Resource
    private EquipOpMapper equipOpMapper;

	@Override
	public BaseMapper<EquipOp, Long> getMapper() {
		return equipOpMapper;
	}

	@Override
	public boolean saveOpToList(List<Integer> oplist, int equipid) {
		try {
			equipOpMapper.deleteOpByEquipId(equipid);
			if(oplist!=null) {
				for(Integer opid:oplist) {
					equipOpMapper.save(new EquipOp(equipid,opid));
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HashMap<String, String> getOpByEquipid(int id) {
		List<EquipOpModel> oplist=equipOpMapper.getOpByEquipid(id);
		String opid="";
		String opname="";
		HashMap<String,String> opmap=new HashMap<String,String>();
		for(EquipOpModel op:oplist) {
			opname+=op.getOpname()+",";
			opid+=op.getOpId()+",";
		}
		opmap.put("opid", opid.length()>0?opid.substring(0, opid.length()-1):opid);
		opmap.put("opname", opname.length()>0?opname.substring(0, opname.length()-1):opname);
		return opmap;
	}

	@Override
	public void deleteOpByEquipId(int id) {
		equipOpMapper.deleteOpByEquipId(id);
	}
	
}