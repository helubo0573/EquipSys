package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存物料类型表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-11 09:53:39
 */
 public class StoreInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** */
    private String stroeName;

    /*** 父id*/
    private Long parentId;

    /*** */
    private Integer storeOrder;

    /*** 注备*/
    private String remarks;

    public StoreInfo() {	}
    
    public StoreInfo(Long id, Long parentid, String storename, int order, String remarks) {
		if(id!=-1) this.setId(id);
		this.setStroeName(storename);
		this.setParentId(parentid);
		this.setStoreOrder(order);
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

    /*** 获取
    *
    * @return 
    */
    public String getStroeName(){
        return stroeName;
    }

    /*** 设置
    * 
    * @param stroeName 要设置的
    */
    public void setStroeName(String stroeName){
        this.stroeName = stroeName;
    }

    /*** 获取父id
    *
    * @return 父id
    */
    public Long getParentId(){
        return parentId;
    }

    /*** 设置父id
    * 
    * @param parentId 要设置的父id
    */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    /*** 获取
    *
    * @return 
    */
    public Integer getStoreOrder(){
        return storeOrder;
    }

    /*** 设置
    * 
    * @param storeOrder 要设置的
    */
    public void setStoreOrder(Integer storeOrder){
        this.storeOrder = storeOrder;
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

}