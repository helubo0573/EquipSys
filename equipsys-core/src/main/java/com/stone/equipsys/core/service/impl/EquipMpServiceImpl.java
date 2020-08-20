package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipMpMapper;
import com.stone.equipsys.core.mapper.EquipOpMapper;
import com.stone.equipsys.core.model.EquipMpModel;
import com.stone.equipsys.core.domain.EquipMp;
import com.stone.equipsys.core.domain.EquipOp;
import com.stone.equipsys.core.service.EquipMpService;


/**
 * 设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-07 12:19:20
 */
 
@Service("equipMpService")
public class EquipMpServiceImpl extends BaseServiceImpl<EquipMp, Long> implements EquipMpService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipMpServiceImpl.class);
   
    @Resource
    private EquipMpMapper equipMpMapper;
    
	@Override
	public BaseMapper<EquipMp, Long> getMapper() {
		return equipMpMapper;
	}

	@Override
	public boolean saveMpToList(List<Integer> mplist, int equipid) {
		try {
			equipMpMapper.deleteMpByEquipId(equipid);
			if(mplist!=null) {
				for(Integer empid:mplist) {
					equipMpMapper.save(new EquipMp(equipid,empid));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void deleteMpByEquipId(int equipId) {
		equipMpMapper.deleteMpByEquipId(equipId);
		
	}

	@Override
	public HashMap<String, String> getMpByEquipid(int id) {
		List<EquipMpModel> mplist=equipMpMapper.getMpByEquipid(id);
		String mpid="";
		String mpname="";
		HashMap<String, String> mpmap=new HashMap<String,String>();
		for(EquipMpModel mp:mplist) {
			mpname+=mp.getMpname()+",";
			mpid+=mp.getMpId()+",";
		}
		mpmap.put("mpid", mpid.length()>0?mpid.substring(0, mpid.length()-1):mpid);
		mpmap.put("mpname", mpname.length()>0?mpname.substring(0, mpname.length()-1):mpname);
		return mpmap;
	}
	
}