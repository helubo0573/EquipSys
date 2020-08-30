package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 	设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-06 11:23:09
 */
 public class OrgPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 所在部门ID*/
    private Integer deptId;

    /*** 位岗名称*/
    private String postName;

    /*** 注备*/
    private String postRemarks;


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

    /*** 获取所在部门ID
    *
    * @return 所在部门ID
    */
    public Integer getDeptId(){
        return deptId;
    }

    /*** 设置所在部门ID
    * 
    * @param deptId 要设置的所在部门ID
    */
    public void setDeptId(Integer deptId){
        this.deptId = deptId;
    }

    /*** 获取位岗名称
    *
    * @return 位岗名称
    */
    public String getPostName(){
        return postName;
    }

    /*** 设置位岗名称
    * 
    * @param postName 要设置的位岗名称
    */
    public void setPostName(String postName){
        this.postName = postName;
    }

    /*** 获取注备
    *
    * @return 注备
    */
    public String getPostRemarks(){
        return postRemarks;
    }

    /*** 设置注备
    * 
    * @param postRemarks 要设置的注备
    */
    public void setPostRemarks(String postRemarks){
        this.postRemarks = postRemarks;
    }

}