package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-23 23:17:44
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

    /*** 入库参数 入库单价*/
    private float price;

    /*** 入库参数 供应商*/
    private String supplier;

    /*** 出库参数 领料人*/
    private Long employeeId;

    /*** 出库参数 领料部门*/
    private Long deptId;

    /*** 出库参数 用途*/
    private String useFor;

    /*** 剩余数量*/
    private Integer overplus;

    /*** 操作人*/
    private String op;

    /*** */
    private String remarks;

    /**
     * 	入库
     * @param ordercode
     * @param modelid
     * @param changetype
     * @param changetime
     * @param quantity
     * @param price
     * @param supplier
     * @param overplus
     * @param op
     * @param remarks
     */
    public StoreGoodsStockLog(String ordercode,Long modelid,int changetype,Date changetime,int quantity,float price,String supplier,int overplus,String op,String remarks) {
        this.setOrderCode(ordercode);
        this.setModelId(modelid);
        this.setChangeType(changetype);
        this.setChangeTime(changetime);
        this.setChangeQuantity(quantity);
        this.setPrice(price);
        this.setSupplier(supplier);
        this.setOverplus(overplus);
        this.setOp(op);
        this.setRemarks(remarks);
    }
    /**
     * 	 出库
     * @param ordercode
     * @param modelid
     * @param changetype
     * @param changetime
     * @param quantity
     * @param employeeid
     * @param deptid
     * @param use
     * @param overplus
     * @param op
     * @param remarks
     */
    public StoreGoodsStockLog(String ordercode,Long modelid,int changetype,Date changetime,int quantity,Long employeeid,Long deptid,String use,int overplus,String op,String remarks) {
        this.setOrderCode(ordercode);
        this.setModelId(modelid);
        this.setChangeType(changetype);
        this.setChangeTime(changetime);
        this.setChangeQuantity(quantity);
        this.setEmployeeId(employeeid);
        this.setDeptId(deptid);
        this.setUseFor(use);
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
    * @param changeType 要设置的库存变更类型
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

    /*** 获取入库参数 入库单价
    *
    * @return 入库参数 入库单价
    */
    public float getPrice(){
        return price;
    }

    /*** 设置入库参数 入库单价
    * 
    * @param price 要设置的入库参数 入库单价
    */
    public void setPrice(float price){
        this.price = price;
    }

    /*** 获取入库参数 供应商
    *
    * @return 入库参数 供应商
    */
    public String getSupplier(){
        return supplier;
    }

    /*** 设置入库参数 供应商
    * 
    * @param supplier 要设置的入库参数 供应商
    */
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }

    /*** 获取出库参数 领料人
    *
    * @return 出库参数 领料人
    */
    public Long getEmployeeId(){
        return employeeId;
    }

    /*** 设置出库参数 领料人
    * 
    * @param employeeId 要设置的出库参数 领料人
    */
    public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
    }

    /*** 获取出库参数 领料部门
    *
    * @return 出库参数 领料部门
    */
    public Long getDeptId(){
        return deptId;
    }

    /*** 设置出库参数 领料部门
    * 
    * @param deptId 要设置的出库参数 领料部门
    */
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }

    /*** 获取出库参数 用途
    *
    * @return 出库参数 用途
    */
    public String getUseFor(){
        return useFor;
    }

    /*** 设置出库参数 用途
    * 
    * @param useFor 要设置的出库参数 用途
    */
    public void setUseFor(String useFor){
        this.useFor = useFor;
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

    /*** 获取操作人
    *
    * @return 操作人
    */
    public String getOp(){
        return op;
    }

    /*** 设置操作人
    * 
    * @param op 要设置的操作人
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