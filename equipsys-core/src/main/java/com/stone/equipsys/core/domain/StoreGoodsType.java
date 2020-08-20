package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存物料类型表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-18 08:59:33
 */
 public class StoreGoodsType implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 类型名称*/
    private String typeName;

    /*** 速查编码*/
    private String quickCode;

    private int typeOrder;
    /*** 注备*/
    private String remarks;

    public StoreGoodsType() {}
    
    public StoreGoodsType(Long id,String typename,String quickcode,int order,String remarks) {
    	if(id!=0) this.setId(id);
    	this.setTypeName(typename);
    	this.setQuickCode(quickcode);
    	this.setTypeOrder(order);
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

    /*** 获取类型名称
    *
    * @return 类型名称
    */
    public String getTypeName(){
        return typeName;
    }

    /*** 设置类型名称
    * 
    * @param typeName 要设置的类型名称
    */
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    /*** 获取速查编码
    *
    * @return 速查编码
    */
    public String getQuickCode(){
        return quickCode;
    }

    /*** 设置速查编码
    * 
    * @param quickCode 要设置的速查编码
    */
    public void setQuickCode(String quickCode){
        this.quickCode = quickCode;
    }

    /*** 获取注备
    *
    * @return 注备
    */
    public String getRemarks(){
        return remarks;
    }

    /*** 设置注备
    * 
    * @param remarks 要设置的注备
    */
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

	public int getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}

}