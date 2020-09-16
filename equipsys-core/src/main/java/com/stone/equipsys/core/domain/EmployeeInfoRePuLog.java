package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工奖惩记录实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-16 11:56:52
 */
 public class EmployeeInfoRePuLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** */
    private Integer employeeId;

    /*** */
    private String projectType;

    /*** */
    private String projectExplain;

    /*** */
    private Integer amount;


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
    public Integer getEmployeeId(){
        return employeeId;
    }

    /*** 设置
    * 
    * @param employeeId 要设置的
    */
    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    /*** 获取
    *
    * @return 
    */
    public String getProjectType(){
        return projectType;
    }

    /*** 设置
    * 
    * @param projectType 要设置的
    */
    public void setProjectType(String projectType){
        this.projectType = projectType;
    }

    /*** 获取
    *
    * @return 
    */
    public String getProjectExplain(){
        return projectExplain;
    }

    /*** 设置
    * 
    * @param projectExplain 要设置的
    */
    public void setProjectExplain(String projectExplain){
        this.projectExplain = projectExplain;
    }

    /*** 获取
    *
    * @return 
    */
    public Integer getAmount(){
        return amount;
    }

    /*** 设置
    * 
    * @param amount 要设置的
    */
    public void setAmount(Integer amount){
        this.amount = amount;
    }

}