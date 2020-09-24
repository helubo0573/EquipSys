package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 	物料规格表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:08:59
 */
 public class StoreGoodsModelNumberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 物料id*/
    private Long goodsId;

    /*** 型号*/
    private String modelNumberName;

    /*** 数量*/
    private Integer quantity;

    /*** 合计价格*/
    private float totalValue;

    /*** 单位*/
    private String unit;

    /*** */
    private String remarks;

    private Boolean logicalState;

    public StoreGoodsModelNumberInfo() {}
    
    public StoreGoodsModelNumberInfo(Long id,Long goodsid,String modelname,int quantity,float total,String unit,String remarks) {
    	if(id>0) this.setId(id);
    	this.setGoodsId(goodsid);
    	this.setModelNumberName(modelname);
    	this.setQuantity(quantity);
    	this.setTotalValue(total);
    	this.setUnit(unit);
    	this.setRemarks(remarks);
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

    /*** 获取物料id
    *
    * @return 物料id
    */
    public Long getGoodsId(){
        return goodsId;
    }

    /*** 设置物料id
    * 
    * @param goodsId 要设置的物料id
    */
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }

    /*** 获取型号
    *
    * @return 型号
    */
    public String getModelNumberName(){
        return modelNumberName;
    }

    /*** 设置型号
    * 
    * @param modelNumberName 要设置的型号
    */
    public void setModelNumberName(String modelNumberName){
        this.modelNumberName = modelNumberName;
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

    /*** 获取合计价格
    *
    * @return 合计价格
    */
    public float getTotalValue(){
        return totalValue;
    }

    /*** 设置合计价格
    * 
    * @param totalValue 要设置的合计价格
    */
    public void setTotalValue(float totalValue){
        this.totalValue = totalValue;
    }

    /*** 获取单位
    *
    * @return 单位
    */
    public String getUnit(){
        return unit;
    }

    /*** 设置单位
    * 
    * @param unit 要设置的单位
    */
    public void setUnit(String unit){
        this.unit = unit;
    }

    /*** 获取
    *
    * @return 
    */
    public String getRemarks(){
        return remarks;
    }

    /*** 设置
    * 
    * @param remarks 要设置的
    */
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

	public Boolean getLogicalState() {
		return logicalState;
	}

	public void setLogicalState(Boolean logicalState) {
		this.logicalState = logicalState;
	}

}