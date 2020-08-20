package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户角色映射表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:02
 */
 public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 角色ID
    */
    private Integer roleId;

    public SysUserRole() {}
    
    public SysUserRole(Long userid,int roleid) {
    	this.setUserId(Integer.parseInt(String.valueOf(userid)));
    	this.setRoleId(roleid);
    }

    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户ID
    *
    * @return 用户ID
    */
    public Integer getUserId(){
        return userId;
    }

    /**
    * 设置用户ID
    * 
    * @param userId 要设置的用户ID
    */
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    /**
    * 获取角色ID
    *
    * @return 角色ID
    */
    public Integer getRoleId(){
        return roleId;
    }

    /**
    * 设置角色ID
    * 
    * @param roleId 要设置的角色ID
    */
    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

}