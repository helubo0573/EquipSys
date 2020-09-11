package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipServicingApplication;

public class EquipServicingApplicationModel extends EquipServicingApplication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**设备名称*/
	private String equipName;
	/**使用地点*/
	private String location;
	/**设备型号*/
	private String equipModelNumber;
	/**申请人名称*/
	private String proposerName;
	/**申请部门*/
	private String proposDept;
	
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEquipModelNumber() {
		return equipModelNumber;
	}
	public void setEquipModelNumber(String equipModelNumber) {
		this.equipModelNumber = equipModelNumber;
	}
	public String getProposerName() {
		return proposerName;
	}
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	public String getProposDept() {
		return proposDept;
	}
	public void setProposDept(String proposDept) {
		this.proposDept = proposDept;
	}
	
	
}
