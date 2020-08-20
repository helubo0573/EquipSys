package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-05 22:59:03
 */
 public class EmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 工员名称*/
    private String empName;

    /*** 别性*/
    private Integer empSex;

    /*** 部门id*/
    private Integer empDept;

    /*** 岗位id*/
    private Integer empPost;

    /*** 手机号码*/
    private String empMobil;

    /*** 档建时间*/
    private Date empDate;

    /*** 岗位状态： -1:离职*/
    private Integer empStatus;

    /*** 注备*/
    private String empRemarks;

    public EmployeeInfo() {
    	
    }
    public EmployeeInfo(String id,String name,String sex,String dept,String post,String mobil,String date,String remarks) {
    	if(!"".equals(id) && id!=null)	this.setId(Long.parseLong(id));
    	this.setEmpName(name!=null && !"".equals(name)?name:"");
    	if(sex!=null && !"".equals(sex)) {
    		try {	this.setEmpSex(Integer.parseInt(sex));	} catch (Exception e) {	this.setEmpSex(0);	}
    	}
    	if(dept!=null && !"".equals(dept)) {
    		try	{	this.setEmpDept(Integer.parseInt(dept));	}catch (Exception e) {	}
    	}
    	if(post!=null && !"".equals(post)) {
    		try	{	this.setEmpPost(Integer.parseInt(post));	}catch (Exception e) {	}
    	}
    	this.setEmpMobil(mobil!=null && !"".equals(mobil)?mobil:"");
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if(date!=null && !"".equals(date)) {
    		try {
				this.setEmpDate(sdf.parse(date));
			} catch (Exception e) {
				this.setEmpDate(getEmpDate());
			}
    	}
    	this.setEmpStatus(0);
    	this.setEmpRemarks(remarks!=null && !"".equals(remarks)?remarks:"");
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

    /*** 获取工员名称
    *
    * @return 工员名称
    */
    public String getEmpName(){
        return empName;
    }

    /*** 设置工员名称
    * 
    * @param empName 要设置的工员名称
    */
    public void setEmpName(String empName){
        this.empName = empName;
    }

    /*** 获取别性
    *
    * @return 别性
    */
    public Integer getEmpSex(){
        return empSex;
    }

    /*** 设置别性
    * 
    * @param empSex 要设置的别性
    */
    public void setEmpSex(Integer empSex){
        this.empSex = empSex;
    }

    /*** 获取部门id
    *
    * @return 部门id
    */
    public Integer getEmpDept(){
        return empDept;
    }

    /*** 设置部门id
    * 
    * @param empDept 要设置的部门id
    */
    public void setEmpDept(Integer empDept){
        this.empDept = empDept;
    }

    /*** 获取岗位id
    *
    * @return 岗位id
    */
    public Integer getEmpPost(){
        return empPost;
    }

    /*** 设置岗位id
    * 
    * @param empPost 要设置的岗位id
    */
    public void setEmpPost(Integer empPost){
        this.empPost = empPost;
    }

    /*** 获取手机号码
    *
    * @return 手机号码
    */
    public String getEmpMobil(){
        return empMobil;
    }

    /*** 设置手机号码
    * 
    * @param empMobil 要设置的手机号码
    */
    public void setEmpMobil(String empMobil){
        this.empMobil = empMobil;
    }

    /*** 获取档建时间
    *
    * @return 档建时间
    */
    public Date getEmpDate(){
        return empDate;
    }

    /*** 设置档建时间
    * 
    * @param empDate 要设置的档建时间
    */
    public void setEmpDate(Date empDate){
        this.empDate = empDate;
    }

    /*** 获取岗位状态： -1:离职
    *
    * @return 岗位状态： -1:离职
    */
    public Integer getEmpStatus(){
        return empStatus;
    }

    /*** 设置岗位状态： -1:离职
    * 
    * @param empStatus 要设置的岗位状态： -1:离职
    */
    public void setEmpStatus(Integer empStatus){
        this.empStatus = empStatus;
    }

    /*** 获取注备
    *
    * @return 注备
    */
    public String getEmpRemarks(){
        return empRemarks;
    }

    /*** 设置注备
    * 
    * @param empRemarks 要设置的注备
    */
    public void setEmpRemarks(String empRemarks){
        this.empRemarks = empRemarks;
    }

}