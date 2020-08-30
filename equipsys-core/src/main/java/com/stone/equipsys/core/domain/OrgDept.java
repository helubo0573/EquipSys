package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 	设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-13 22:09:14
 */
 public class OrgDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 门部名称*/
    private String deptName;

    /*** */
    private Integer parentId;

    /*** 0:部门 1:岗位*/
    private Integer deptType;

    /*** 注备*/
    private String deptRemarks;

    public OrgDept() {	}
    
    public OrgDept(String id, String depttype,String deptname, String parentid,String remarks) {
		if(!"".equals(id) && id!=null) this.setId(Long.parseLong(id));
		if(!"".equals(deptname) && deptname!=null) this.setDeptName(deptname);
		if(!"".equals(parentid) && parentid!=null) this.setParentId(Integer.parseInt(parentid));
		if(!"".equals(depttype) && depttype!=null) this.setDeptType(Integer.parseInt(depttype));
		if(!"".equals(remarks) && remarks!=null) this.setDeptRemarks(remarks);
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

    /*** 获取门部名称
    *
    * @return 门部名称
    */
    public String getDeptName(){
        return deptName;
    }

    /*** 设置门部名称
    * 
    * @param deptName 要设置的门部名称
    */
    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    /*** 获取
    *
    * @return 
    */
    public Integer getParentId(){
        return parentId;
    }

    /*** 设置
    * 
    * @param parentId 要设置的
    */
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    /*** 获取0:部门 1:岗位
    *
    * @return 0:部门 1:岗位
    */
    public Integer getDeptType(){
        return deptType;
    }

    /*** 设置0:部门 1:岗位
    * 
    * @param deptType 要设置的0:部门 1:岗位
    */
    public void setDeptType(Integer deptType){
        this.deptType = deptType;
    }

    /*** 获取注备
    *
    * @return 注备
    */
    public String getDeptRemarks(){
        return deptRemarks;
    }

    /*** 设置注备
    * 
    * @param deptRemarks 要设置的注备
    */
    public void setDeptRemarks(String deptRemarks){
        this.deptRemarks = deptRemarks;
    }

}