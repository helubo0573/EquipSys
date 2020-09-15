package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备维修零件耗用表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:28:19
 */
 public class Equipservicingimplementparts implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 维修单id*/
    private Long implementId;

    /*** 配件id*/
    private Long partsId;

    /*** 配件材料名称*/
    private String partsName;

    /*** 使用类型  0:更换  1:消耗*/
    private Integer useType;


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

    /*** 获取维修单id
    *
    * @return 维修单id
    */
    public Long getImplementId(){
        return implementId;
    }

    /*** 设置维修单id
    * 
    * @param implementId 要设置的维修单id
    */
    public void setImplementId(Long implementId){
        this.implementId = implementId;
    }

    /*** 获取配件id
    *
    * @return 配件id
    */
    public Long getPartsId(){
        return partsId;
    }

    /*** 设置配件id
    * 
    * @param partsId 要设置的配件id
    */
    public void setPartsId(Long partsId){
        this.partsId = partsId;
    }

    /*** 获取配件材料名称
    *
    * @return 配件材料名称
    */
    public String getPartsName(){
        return partsName;
    }

    /*** 设置配件材料名称
    * 
    * @param partsName 要设置的配件材料名称
    */
    public void setPartsName(String partsName){
        this.partsName = partsName;
    }

    /*** 获取使用类型  0:更换  1:消耗
    *
    * @return 使用类型  0:更换  1:消耗
    */
    public Integer getUseType(){
        return useType;
    }

    /*** 设置使用类型  0:更换  1:消耗
    * 
    * @param useType 要设置的使用类型  0:更换  1:消耗
    */
    public void setUseType(Integer useType){
        this.useType = useType;
    }

}