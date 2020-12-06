package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipServicingImplementParts;

public class EquipServicingImplementPartsModel extends EquipServicingImplementParts{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String modelNumber;
	
	private String goodsname;
	
	private String typeName;
	
	private String unit; 

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
