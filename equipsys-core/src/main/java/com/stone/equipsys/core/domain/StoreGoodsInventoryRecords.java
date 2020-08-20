package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料明细存储纪录实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-06 23:02:50
 */
 public class StoreGoodsInventoryRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 规格id*/
    private Long modelId;

    /*** 入库时间*/
    private Date inDate;

    /*** 数量*/
    private Integer quantity;

    /*** 价单*/
    private float price;

    /*** */
    private String supplier;

    /*** */
    private String remarks;

    public StoreGoodsInventoryRecords() {}
    
    public StoreGoodsInventoryRecords(Long modelid,Date indate,int quantity,float price,String supplier,String remarks) {
    	this.setModelId(modelid);
    	this.setInDate(indate);
    	this.setQuantity(quantity);
    	this.setPrice(price);
    	this.setSupplier(supplier);
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

    /*** 获取规格id
    *
    * @return 规格id
    */
    public Long getModelId(){
        return modelId;
    }

    /*** 设置规格id
    * 
    * @param modelId 要设置的规格id
    */
    public void setModelId(Long modelId){
        this.modelId = modelId;
    }

    /*** 获取入库时间
    *
    * @return 入库时间
    */
    public Date getInDate(){
        return inDate;
    }

    /*** 设置入库时间
    * 
    * @param inDate 要设置的入库时间
    */
    public void setInDate(Date inDate){
        this.inDate = inDate;
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

    /*** 获取价单
    *
    * @return 价单
    */
    public float getPrice(){
        return price;
    }

    /*** 设置价单
    * 
    * @param price 要设置的价单
    */
    public void setPrice(float price){
        this.price = price;
    }

    /*** 获取
    *
    * @return 
    */
    public String getSupplier(){
        return supplier;
    }

    /*** 设置
    * 
    * @param supplier 要设置的
    */
    public void setSupplier(String supplier){
        this.supplier = supplier;
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