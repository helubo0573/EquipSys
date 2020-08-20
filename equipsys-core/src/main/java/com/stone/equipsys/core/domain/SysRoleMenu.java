package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色菜单映射表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:31
 */
 public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Integer id;

    /**
    * 角色ID
    */
    private Integer roleId;

    /**
    * 单菜ID
    */
    private Integer menuId;

    public SysRoleMenu() {}
    
    public SysRoleMenu(Long roleid,int menuid) {
    	this.setRoleId(Integer.parseInt(String.valueOf(roleid)));
    	this.setMenuId(menuid);
    }
    /**
    * 获取主键Id
    *
    * @return id
    */
    public Integer getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Integer id){
        this.id = id;
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

    /**
    * 获取单菜ID
    *
    * @return 单菜ID
    */
    public Integer getMenuId(){
        return menuId;
    }

    /**
    * 设置单菜ID
    * 
    * @param menuId 要设置的单菜ID
    */
    public void setMenuId(Integer menuId){
        this.menuId = menuId;
    }

}