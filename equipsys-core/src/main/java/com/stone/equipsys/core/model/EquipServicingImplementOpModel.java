package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipServicingImplementOp;

public class EquipServicingImplementOpModel extends EquipServicingImplementOp{

	private static final long serialVersionUID = 1L;
	
	private int opId;
	private String opname;

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public int getOpId() {
		return opId;
	}

	public void setOpId(int opId) {
		this.opId = opId;
	} 
}
