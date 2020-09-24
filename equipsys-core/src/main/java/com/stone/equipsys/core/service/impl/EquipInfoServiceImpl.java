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
import com.stone.equipsys.core.mapper.EquipMpMapper;
import com.stone.equipsys.core.mapper.EquipOpMapper;
import com.stone.equipsys.core.mapper.EquipPartsInfoMapper;
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
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
    @Resource
    private EquipPartsInfoMapper equipPartsInfoMapper;
    @Resource
    private EquipMpMapper equipMpMapper;
    @Resource
    private EquipOpMapper equipOpMapper;
    @Resource
	private EquipServicingApplicationMapper equipServicingApplicationMapper;
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
		int cequipnum=equipInfoMapper.countByParentid(eid);	//查询设备的下级设备
		int partsnum=equipPartsInfoMapper.countByEquipId(eid);//查询设备的配件信息
		int mpnum=equipMpMapper.countByEquipid(eid);//查询设备的修理人员
		int opnum=equipOpMapper.countByEquipid(eid);//查询设备的操作人员
		int equipservicingappnum=equipServicingApplicationMapper.countByEquipId(eid);
		if(cequipnum+partsnum+mpnum+opnum+equipservicingappnum>0) {//如果存在外键数据则逻辑删除
			equipInfoMapper.LogicalDeletionById(eid);
		}else {//否则物理删除
			equipInfoMapper.deleteById(eid);
		}
	}
	
	
	
}