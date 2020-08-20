package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设备基础信息表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-07 15:04:05
 */
 public class EquipInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 设备名称*/
    private String equipName;

    /*** 备设编号*/
    private String equipCode;

    /*** 所属设备id*/
    private Integer parentId;

    /*** */
    private Integer equipLevel;

    /*** 启用日期*/
    private Date enableDate;

    /*** 所属部门id*/
    private Integer attrDept;

    /*** 供应商*/
    private String supplier;

    /*** 在所位置*/
    private String location;

    public EquipInfo() {}
    
    public EquipInfo(String id,String equipname,String equipcode,String parentid,String level,String enabledate,String attrdept,String supplier,String location) throws ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if(!"".equals(id) && id!=null)	this.setId(Long.parseLong(id));
    	if(!"".equals(parentid) && parentid!=null) //如果没有则表示为系统级，初始化为0
    		this.setParentId(Integer.parseInt(parentid));
    	else
    		this.setParentId(0);	
    	if(!"".equals(equipname) && equipname!=null)	this.setEquipName(equipname);
    	if(!"".equals(enabledate) && enabledate!=null) this.setEnableDate(sdf.parse(enabledate));
    	if(!"".equals(equipcode) && equipcode!=null)	this.setEquipCode(equipcode);
    	if(!"".equals(supplier) && supplier!=null)	this.setSupplier(supplier);
    	if(!"".equals(location) && location!=null)	this.setLocation(location);
    	if(!"".equals(attrdept) && attrdept!=null)	this.setAttrDept(Integer.parseInt(attrdept));
    	if(!"".equals(level) && level!=null)	this.setEquipLevel(Integer.parseInt(level));
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

    /*** 获取设备名称
    *
    * @return 设备名称
    */
    public String getEquipName(){
        return equipName;
    }

    /*** 设置设备名称
    * 
    * @param equipName 要设置的设备名称
    */
    public void setEquipName(String equipName){
        this.equipName = equipName;
    }

    /*** 获取备设编号
    *
    * @return 备设编号
    */
    public String getEquipCode(){
        return equipCode;
    }

    /*** 设置备设编号
    * 
    * @param equipCode 要设置的备设编号
    */
    public void setEquipCode(String equipCode){
        this.equipCode = equipCode;
    }

    /*** 获取所属设备id
    *
    * @return 所属设备id
    */
    public Integer getParentId(){
        return parentId;
    }

    /*** 设置所属设备id
    * 
    * @param parentId 要设置的所属设备id
    */
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    /*** 获取
    *
    * @return 
    */
    public Integer getEquipLevel(){
        return equipLevel;
    }

    /*** 设置
    * 
    * @param equipLevel 要设置的
    */
    public void setEquipLevel(Integer equipLevel){
        this.equipLevel = equipLevel;
    }

    /*** 获取启用日期
    *
    * @return 启用日期
    */
    public Date getEnableDate(){
        return enableDate;
    }

    /*** 设置启用日期
    * 
    * @param enableDate 要设置的启用日期
    */
    public void setEnableDate(Date enableDate){
        this.enableDate = enableDate;
    }

    /*** 获取所属部门id
    *
    * @return 所属部门id
    */
    public Integer getAttrDept(){
        return attrDept;
    }

    /*** 设置所属部门id
    * 
    * @param attrDept 要设置的所属部门id
    */
    public void setAttrDept(Integer attrDept){
        this.attrDept = attrDept;
    }

    /*** 获取供应商
    *
    * @return 供应商
    */
    public String getSupplier(){
        return supplier;
    }

    /*** 设置供应商
    * 
    * @param supplier 要设置的供应商
    */
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }

    /*** 获取在所位置
    *
    * @return 在所位置
    */
    public String getLocation(){
        return location;
    }

    /*** 设置在所位置
    * 
    * @param location 要设置的在所位置
    */
    public void setLocation(String location){
        this.location = location;
    }

}