package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 	系统用户表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-02 23:29:14
 */
 public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** */
    private String username;

    /*** */
    private String password;

    /*** */
    private Integer employeeId;

    /*** 1:正常 2:被锁定 3:逻辑删除*/
    private Integer staus;

    /*** */
    private Date loginTime;

    /*** */
    private Date createTime;

    /*** 否是为管理员*/
    private Integer isadmin;

    public SysUser() {}

    public SysUser(String id, String username, String empid) {
    	if(!"".equals(id) && id!=null) this.setId(Long.parseLong(id));
    	this.setUsername(username);
    	this.setEmployeeId(Integer.parseInt(empid));
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
    public String getUsername(){
        return username;
    }

    /*** 设置
    * 
    * @param username 要设置的
    */
    public void setUsername(String username){
        this.username = username;
    }

    /*** 获取
    *
    * @return 
    */
    public String getPassword(){
        return password;
    }

    /*** 设置
    * 
    * @param password 要设置的
    */
    public void setPassword(String password){
        this.password = password;
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

    /*** 获取1:正常 2:被锁定 3:逻辑删除
    *
    * @return 1:正常 2:被锁定 3:逻辑删除
    */
    public Integer getStaus(){
        return staus;
    }

    /*** 设置1:正常 2:被锁定 3:逻辑删除
    * 
    * @param staus 要设置的1:正常 2:被锁定 3:逻辑删除
    */
    public void setStaus(Integer staus){
        this.staus = staus;
    }

    /*** 获取
    *
    * @return 
    */
    public Date getLoginTime(){
        return loginTime;
    }

    /*** 设置
    * 
    * @param loginTime 要设置的
    */
    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

    /*** 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /*** 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /*** 获取否是为管理员
    *
    * @return 否是为管理员
    */
    public Integer getIsadmin(){
        return isadmin;
    }

    /*** 设置否是为管理员
    * 
    * @param isadmin 要设置的否是为管理员
    */
    public void setIsadmin(Integer isadmin){
        this.isadmin = isadmin;
    }

}