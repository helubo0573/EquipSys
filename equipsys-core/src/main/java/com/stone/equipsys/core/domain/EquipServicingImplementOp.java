package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备维修人员记录表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:27:40
 */
 public class EquipServicingImplementOp implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 维修单id*/
    private Long implementId;

    /*** 维修人员id*/
    private Integer employeeId;

    /*** 是否为负责人 0:否 1:是*/
    private Integer chargePerson;

    public EquipServicingImplementOp() {}
    
    public EquipServicingImplementOp(Long implementid,int employeeid) {
    	this.setImplementId(implementid);
    	this.setEmployeeId(employeeid);
    	this.setchargePerson(0);
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

    /*** 获取维修人员id
    *
    * @return 维修人员id
    */
    public Integer getEmployeeId(){
        return employeeId;
    }

    /*** 设置维修人员id
    * 
    * @param employeeId 要设置的维修人员id
    */
    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    /*** 获取是否为负责人 0:否 1:是
    *
    * @return 是否为负责人 0:否 1:是
    */
    public Integer getchargePerson(){
        return chargePerson;
    }

    /*** 设置是否为负责人 0:否 1:是
    * 
    * @param leading 要设置的是否为负责人 0:否 1:是
    */
    public void setchargePerson(Integer chargePerson){
        this.chargePerson = chargePerson;
    }

}