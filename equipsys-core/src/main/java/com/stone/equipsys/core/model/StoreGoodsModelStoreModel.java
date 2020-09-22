package com.stone.equipsys.core.model;

import com.stone.equipsys.core.domain.StoreGoodsModelStore;

public class StoreGoodsModelStoreModel extends StoreGoodsModelStore{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String storeName;

	public String getStorename() {
		return storeName;
	}

	public void setStorename(String storename) {
		this.storeName = storename;
	}
}
