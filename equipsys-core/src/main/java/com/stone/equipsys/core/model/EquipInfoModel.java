package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipInfo;

public class EquipInfoModel extends EquipInfo{
	
	private static final long serialVersionUID = 1L;
	
	private String deptName;
	
	private String parentequipName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentequipName() {
		return parentequipName;
	}

	public void setParentequipName(String parentequipName) {
		this.parentequipName = parentequipName;
	}

}
