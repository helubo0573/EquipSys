package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料库存映射表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-21 23:21:54
 */
 public class StoreGoodsModelStore implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 物料规格id*/
    private Long modelId;

    /*** 仓位id*/
    private Long storeId;


    public StoreGoodsModelStore() {}
    
    public StoreGoodsModelStore(Long modelid,Long storeid) {
    	this.setModelId(modelid);
    	this.setStoreId(storeid);
    }
    /*** 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /*** 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /*** 获取物料规格id
    *
    * @return 物料规格id
    */
    public Long getModelId(){
        return modelId;
    }

    /*** 设置物料规格id
    * 
    * @param modelId 要设置的物料规格id
    */
    public void setModelId(Long modelId){
        this.modelId = modelId;
    }

    /*** 获取仓位id
    *
    * @return 仓位id
    */
    public Long getStoreId(){
        return storeId;
    }

    /*** 设置仓位id
    * 
    * @param storeId 要设置的仓位id
    */
    public void setStoreId(Long storeId){
        this.storeId = storeId;
    }

}