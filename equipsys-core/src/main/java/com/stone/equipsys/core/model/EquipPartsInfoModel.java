package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.EquipPartsInfo;

public class EquipPartsInfoModel extends EquipPartsInfo{

	private static final long serialVersionUID = 1L;

	private String modelName;
	
	private String goodsName;
	
	private String typeName;
	
	private int storeQuantity;
	
	private Long goodstypeId;
	
	private Long goodsId;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(int storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public Long getGoodstypeId() {
		return goodstypeId;
	}

	public void setGoodstypeId(Long goodstypeId) {
		this.goodstypeId = goodstypeId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
