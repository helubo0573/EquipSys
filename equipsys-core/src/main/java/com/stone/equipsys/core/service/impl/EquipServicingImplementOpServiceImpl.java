package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingImplementOpMapper;
import com.stone.equipsys.core.model.EquipOpModel;
import com.stone.equipsys.core.model.EquipServicingImplementOpModel;
import com.stone.equipsys.core.domain.EquipOp;
import com.stone.equipsys.core.domain.EquipServicingImplementOp;
import com.stone.equipsys.core.service.EquipServicingImplementOpService;


/**
 * 设备维修人员记录表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:27:40
 */
 
@Service("equipServicingImplementOpService")
public class EquipServicingImplementOpServiceImpl extends BaseServiceImpl<EquipServicingImplementOp, Long> implements EquipServicingImplementOpService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipServicingImplementOpServiceImpl.class);
   
    @Resource
    private EquipServicingImplementOpMapper equipServicingImplementOpMapper;

	@Override
	public BaseMapper<EquipServicingImplementOp, Long> getMapper() {
		return equipServicingImplementOpMapper;
	}

	@Override
	public HashMap<String, String> getOpByImplementId(Long id) {
		List<EquipServicingImplementOpModel> oplist=equipServicingImplementOpMapper.getOpByImplementid(id);
		String opid="";
		String opname="";
		HashMap<String,String> opmap=new HashMap<String,String>();
		for(EquipServicingImplementOpModel op:oplist) {
			opname+=op.getOpname()+",";
			opid+=op.getEmployeeId()+",";
		}
		opmap.put("opid", opid.length()>0?opid.substring(0, opid.length()-1):opid);
		opmap.put("opname", opname.length()>0?opname.substring(0, opname.length()-1):opname);
		return opmap;
	}

	@Override
	public boolean saveOpToList(List<Integer> oplist, Long id) {
		try {
			equipServicingImplementOpMapper.deleteOpByImplementId(id);
			if(oplist!=null) {
				for(Integer opid:oplist) {
					System.out.println("opid:"+opid);
					equipServicingImplementOpMapper.save(new EquipServicingImplementOp(id,opid));
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}