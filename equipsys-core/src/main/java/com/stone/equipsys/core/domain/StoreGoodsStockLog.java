package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料明细存储纪录实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-07 23:00:22
 */
 public class StoreGoodsStockLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 库存变更单号*/
    private String orderCode;

    /*** 物料规格库存id*/
    private Long modelId;

    /*** 库存变更类型*/
    private Integer changeType;

    /*** 库存变更时间*/
    private Date changeTime;

    /*** 变更数量*/
    private Integer changeQuantity;

    /*** 仓位*/
    private Long storeId;

    /*** 剩余数量*/
    private Integer overplus;

    /*** */
    private String op;

    /*** */
    private String remarks;

    public StoreGoodsStockLog() {}
    
    /**
     * 
     * @param ordercode		库存变更编号
     * @param modelid		规格id
     * @param changetype	变更类型
     * @param changetime	变更时间
     * @param quantity		变更数量
     * @param storeid		仓库id
     * @param overplus		结存数量
     * @param op			操作人
     * @param remarks		备注 
     */
    public StoreGoodsStockLog(String ordercode,Long modelid,int changetype,Date changetime,int quantity,int overplus,String op,String remarks) {
    	this.setOrderCode(ordercode);
    	this.setModelId(modelid);
    	this.setChangeType(changetype);
    	this.setChangeTime(changetime);
    	this.setChangeQuantity(quantity);
    	this.setOverplus(overplus);
    	this.setOp(op);
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

    /*** 获取库存变更单号
    *
    * @return 库存变更单号
    */
    public String getOrderCode(){
        return orderCode;
    }

    /*** 设置库存变更单号
    * 
    * @param orderCode 要设置的库存变更单号
    */
    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }

    /*** 获取物料规格库存id
    *
    * @return 物料规格库存id
    */
    public Long getModelId(){
        return modelId;
    }

    /*** 设置物料规格库存id
    * 
    * @param modelId 要设置的物料规格库存id
    */
    public void setModelId(Long modelId){
        this.modelId = modelId;
    }

    /*** 获取库存变更类型
    *
    * @return 库存变更类型
    */
    public Integer getChangeType(){
        return changeType;
    }

    /*** 设置库存变更类型
    * 
    * @param changeType 要设置的库存变更类型  0:入库  1:出库
    */
    public void setChangeType(Integer changeType){
        this.changeType = changeType;
    }

    /*** 获取库存变更时间
    *
    * @return 库存变更时间
    */
    public Date getChangeTime(){
        return changeTime;
    }

    /*** 设置库存变更时间
    * 
    * @param changeTime 要设置的库存变更时间
    */
    public void setChangeTime(Date changeTime){
        this.changeTime = changeTime;
    }

    /*** 获取变更数量
    *
    * @return 变更数量
    */
    public Integer getChangeQuantity(){
        return changeQuantity;
    }

    /*** 设置变更数量
    * 
    * @param changeQuantity 要设置的变更数量
    */
    public void setChangeQuantity(Integer changeQuantity){
        this.changeQuantity = changeQuantity;
    }

    /*** 获取仓位
    *
    * @return 仓位
    */
    public Long getStoreId(){
        return storeId;
    }

    /*** 设置仓位
    * 
    * @param storeId 要设置的仓位
    */
    public void setStoreId(Long storeId){
        this.storeId = storeId;
    }

    /*** 获取剩余数量
    *
    * @return 剩余数量
    */
    public Integer getOverplus(){
        return overplus;
    }

    /*** 设置剩余数量
    * 
    * @param overplus 要设置的剩余数量
    */
    public void setOverplus(Integer overplus){
        this.overplus = overplus;
    }

    /*** 获取
    *
    * @return 
    */
    public String getOp(){
        return op;
    }

    /*** 设置
    * 
    * @param op 要设置的
    */
    public void setOp(String op){
        this.op = op;
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

}