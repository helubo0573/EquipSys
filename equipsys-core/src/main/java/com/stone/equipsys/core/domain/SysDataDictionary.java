package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 	数据字典实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-19 23:35:18
 */
 public class SysDataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 数据编码*/
    private String dataCode;

    /*** 数据编号*/
    private Integer dataNumber;

    /*** 数据名称*/
    private String dataName;


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

    /*** 获取数据编码
    *
    * @return 数据编码
    */
    public String getDataCode(){
        return dataCode;
    }

    /*** 设置数据编码
    * 
    * @param dataCode 要设置的数据编码
    */
    public void setDataCode(String dataCode){
        this.dataCode = dataCode;
    }

    /*** 获取数据编号
    *
    * @return 数据编号
    */
    public Integer getDataNumber(){
        return dataNumber;
    }

    /*** 设置数据编号
    * 
    * @param dataNumber 要设置的数据编号
    */
    public void setDataNumber(Integer dataNumber){
        this.dataNumber = dataNumber;
    }

    /*** 获取数据名称
    *
    * @return 数据名称
    */
    public String getDataName(){
        return dataName;
    }

    /*** 设置数据名称
    * 
    * @param dataName 要设置的数据名称
    */
    public void setDataName(String dataName){
        this.dataName = dataName;
    }

}