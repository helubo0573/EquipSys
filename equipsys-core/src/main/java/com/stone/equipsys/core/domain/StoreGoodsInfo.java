package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-27 12:17:18
 */
 public class StoreGoodsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 物料名称*/
    private String goodsName;

    /*** 物料类型*/
    private Long goodsType;

    /*** */
    private String remarks;

    public StoreGoodsInfo() {}
    
    public StoreGoodsInfo(Long id,String goodsname,Long goodstype,String remarks) {
    	if(id!=0) this.setId(id);
    	this.setGoodsName(goodsname);
    	this.setGoodsType(goodstype);
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

    /*** 获取物料名称
    *
    * @return 物料名称
    */
    public String getGoodsName(){
        return goodsName;
    }

    /*** 设置物料名称
    * 
    * @param goodsName 要设置的物料名称
    */
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    /*** 获取物料类型
    *
    * @return 物料类型
    */
    public Long getGoodsType(){
        return goodsType;
    }

    /*** 设置物料类型
    * 
    * @param goodsType 要设置的物料类型
    */
    public void setGoodsType(Long goodsType){
        this.goodsType = goodsType;
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