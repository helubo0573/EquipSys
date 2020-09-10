package com.stone.equipsys.core.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipInfoMapper;
import com.stone.equipsys.core.model.EquipInfoModel;
import com.stone.equipsys.core.domain.EquipInfo;
import com.stone.equipsys.core.service.EquipInfoService;


/**
 * 	设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:08:34
 */
 
@Service("equipInfoService")
public class EquipInfoServiceImpl extends BaseServiceImpl<EquipInfo, Long> implements EquipInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipInfoServiceImpl.class);
   
    @Resource
    private EquipInfoMapper equipInfoMapper;

	@Override
	public BaseMapper<EquipInfo, Long> getMapper() {
		return equipInfoMapper;
	}

	@Override
	public int insertReturnId(EquipInfo equip) throws ParseException {
				equipInfoMapper.insertReturnId(equip);
		
		return Integer.parseInt(String.valueOf(equip.getId()));
	}

	@Override
	public int updateRetuenId(EquipInfo equip) throws ParseException {
				equipInfoMapper.update(equip);
				
		return Integer.parseInt(String.valueOf(equip.getId())) ;
	}

	@Override
	public List<EquipInfo> getEquip(int type) {
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("maxlevel", type);
		return equipInfoMapper.listSelective(map);
	}

	@Override
	public EquipInfoModel getEquipExtInfoById(Long id) {
		// TODO Auto-generated method stub
		return equipInfoMapper.getEquipExtInfoById(id);
	}

	@Override
	public void deleteEquipById(Long eid) {
		boolean state=true;
		while (state) {
			boolean s=equipInfoMapper.deleteEquipById(eid);
			
		}
	}
	
	
	
}