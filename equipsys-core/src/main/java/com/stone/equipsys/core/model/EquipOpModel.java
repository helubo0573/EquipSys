package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipOp;

public class EquipOpModel extends EquipOp{
	
	private static final long serialVersionUID = 1L;
	private String opname;

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	} 
}
