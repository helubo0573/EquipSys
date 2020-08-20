package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EmployeeInfo;

public class EmployeeInfoModel extends EmployeeInfo{

	private static final long serialVersionUID = 1L;
	
	private Long deptid;
	
	private String deptname;
	
	private Long postid;
	
	private String postname;

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Long getPostid() {
		return postid;
	}

	public void setPostid(Long postid) {
		this.postid = postid;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

}
