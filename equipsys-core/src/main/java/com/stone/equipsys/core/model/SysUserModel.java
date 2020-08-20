package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.SysUser;

public class SysUserModel extends SysUser{
	
	private static final long serialVersionUID = 1L;
	private String empname;
	
	private String deptname;
	
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getdeptname() {
		return deptname;
	}
	public void setdeptname(String deptName) {
		deptname = deptName;
	}

}
