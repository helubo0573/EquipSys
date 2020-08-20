package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单资源表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:15:08
 */
 public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private int id;

    /**
    * 上级ID
    */
    private Integer parentId;

    /**
    * 资源名称
    */
    private String menuName;

    /**
    * 请求串
    */
    private String url;

    /**
    * 授权标识
    */
    private String perms;

    /**
    * 资源类型 0:目录 1:菜单 2:按钮
    */
    private Integer menuType;

    /**
    * 标图代码
    */
    private String menuIcon;

    /**
    * 排序编号
    */
    private Integer menuOrder;

    /*** 注备*/
    private String remarks;

    
    public SysMenu() {};

	public SysMenu(String id, String type, String name, String parent, String url, String perms, String order,
			String icon, String remarks) {
	    if(id!=null && !"".equals(id)) this.setId(Integer.parseInt(id)); else this.setId(0);
		if(type!=null && !"".equals(type)) this.setMenuType(Integer.parseInt(type));
		if(name!=null && !"".equals(name)) this.setMenuName(name);
		if(parent!=null && !"".equals(parent)) this.setParentId(Integer.parseInt(parent));
		if(url!=null && !"".equals(url)) this.setUrl(url);
		if(perms!=null && !"".equals(perms)) this.setPerms(perms);
		if(order!=null && !"".equals(order)) this.setMenuOrder(Integer.parseInt(order));
	}
	/**
    * 获取主键Id
    *
    * @return id
    */
    public int getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(int id){
        this.id = id;
    }

    /**
    * 获取上级ID
    *
    * @return 上级ID
    */
    public Integer getParentId(){
        return parentId;
    }

    /**
    * 设置上级ID
    * 
    * @param parentId 要设置的上级ID
    */
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    /**
    * 获取资源名称
    *
    * @return 资源名称
    */
    public String getMenuName(){
        return menuName;
    }

    /**
    * 设置资源名称
    * 
    * @param menuName 要设置的资源名称
    */
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    /**
    * 获取请求串
    *
    * @return 请求串
    */
    public String getUrl(){
        return url;
    }

    /**
    * 设置请求串
    * 
    * @param url 要设置的请求串
    */
    public void setUrl(String url){
        this.url = url;
    }

    /**
    * 获取授权标识
    *
    * @return 授权标识
    */
    public String getPerms(){
        return perms;
    }

    /**
    * 设置授权标识
    * 
    * @param perms 要设置的授权标识
    */
    public void setPerms(String perms){
        this.perms = perms;
    }

    /**
    * 获取资源类型 0:目录 1:菜单 2:按钮
    *
    * @return 资源类型 0:目录 1:菜单 2:按钮
    */
    public Integer getMenuType(){
        return menuType;
    }

    /**
    * 设置资源类型 0:目录 1:菜单 2:按钮
    * 
    * @param menuType 要设置的资源类型 0:目录 1:菜单 2:按钮
    */
    public void setMenuType(Integer menuType){
        this.menuType = menuType;
    }

    /**
    * 获取标图代码
    *
    * @return 标图代码
    */
    public String getMenuIcon(){
        return menuIcon;
    }

    /**
    * 设置标图代码
    * 
    * @param menuIcon 要设置的标图代码
    */
    public void setMenuIcon(String menuIcon){
        this.menuIcon = menuIcon;
    }

    /**
    * 获取排序编号
    *
    * @return 排序编号
    */
    public Integer getMenuOrder(){
        return menuOrder;
    }

    /**
    * 设置排序编号
    * 
    * @param menuOrder 要设置的排序编号
    */
    public void setMenuOrder(Integer menuOrder){
        this.menuOrder = menuOrder;
    }

    /**
    * 获取注备
    *
    * @return 注备
    */
    public String getRemarks(){
        return remarks;
    }

    /**
    * 设置注备
    * 
    * @param remarks 要设置的注备
    */
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

}