package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备配件信息实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-25 12:14:22
 */
 public class EquipPartsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 设备id*/
    private Long equipId;

    /*** 对应的库存物料id*/
    private Long goodsModelId;

    /*** 设备名称*/
    private String partsName;

    /*** 数量*/
    private Integer quantity;
    public EquipPartsInfo() {}
    
    public EquipPartsInfo(Long id,Long equipid,Long goodsmodelid,String partsname,int quantity) {
    	if(id!=0) this.setId(id);
    	this.setEquipId(equipid);
    	this.setGoodsModelId(goodsmodelid);
    	this.setPartsName(partsname);
    	this.setQuantity(quantity);
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

    /*** 获取设备id
    *
    * @return 设备id
    */
    public Long getEquipId(){
        return equipId;
    }

    /*** 设置设备id
    * 
    * @param equipId 要设置的设备id
    */
    public void setEquipId(Long equipId){
        this.equipId = equipId;
    }

    /*** 获取对应的库存物料id
    *
    * @return 对应的库存物料id
    */
    public Long getGoodsModelId(){
        return goodsModelId;
    }

    /*** 设置对应的库存物料id
    * 
    * @param goodsModelId 要设置的对应的库存物料id
    */
    public void setGoodsModelId(Long goodsModelId){
        this.goodsModelId = goodsModelId;
    }

    /*** 获取设备名称
    *
    * @return 设备名称
    */
    public String getPartsName(){
        return partsName;
    }

    /*** 设置设备名称
    * 
    * @param partsName 要设置的设备名称
    */
    public void setPartsName(String partsName){
        this.partsName = partsName;
    }

    /*** 获取数量
    *
    * @return 数量
    */
    public Integer getQuantity(){
        return quantity;
    }

    /*** 设置数量
    * 
    * @param quantity 要设置的数量
    */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

}