package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:24:08
 */
 public class EquipOp implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 备设id*/
    private Integer equipId;

    /*** 操作员id*/
    private Integer opId;


    public EquipOp() {}
    public EquipOp(int equipid, Integer opid) {
    	this.setEquipId(equipid);
    	this.setOpId(opid);
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

    /*** 获取备设id
    *
    * @return 备设id
    */
    public Integer getEquipId(){
        return equipId;
    }

    /*** 设置备设id
    * 
    * @param equipId 要设置的备设id
    */
    public void setEquipId(Integer equipId){
        this.equipId = equipId;
    }

    /*** 获取操作员id
    *
    * @return 操作员id
    */
    public Integer getOpId(){
        return opId;
    }

    /*** 设置操作员id
    * 
    * @param opId 要设置的操作员id
    */
    public void setOpId(Integer opId){
        this.opId = opId;
    }

}