package com.stone.equipsys.core.model;

import java.util.Date;

import com.stone.equipsys.core.domain.EquipServicingImplement;

public class EquipServicingImplementDetailModel extends EquipServicingImplement{

	private static final long serialVersionUID = 1L;

	private Date applicationTime;
	
	private Date backfireTime;
	
	private String equipName;
	
	private String equipCode;
	
	private String location;
	
	private String employeeName;

	private String deptName;
	
	private String pdeptName;
	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Date getBackfireTime() {
		return backfireTime;
	}

	public void setBackfireTime(Date backfireTime) {
		this.backfireTime = backfireTime;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPdeptName() {
		return pdeptName;
	}

	public void setPdeptName(String pdeptName) {
		this.pdeptName = pdeptName;
	}
	
	
}
