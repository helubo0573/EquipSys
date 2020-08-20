package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-07 12:19:20
 */
 public class EquipMp implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 备设id*/
    private Integer equipId;

    /*** 修维人员id*/
    private Integer mpId;


    public EquipMp() {};
    public EquipMp(int equipid,int mpid) {
    	this.setEquipId(equipid);
    	this.setMpId(mpid);
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

    /*** 获取修维人员id
    *
    * @return 修维人员id
    */
    public Integer getMpId(){
        return mpId;
    }

    /*** 设置修维人员id
    * 
    * @param mpId 要设置的修维人员id
    */
    public void setMpId(Integer mpId){
        this.mpId = mpId;
    }

}