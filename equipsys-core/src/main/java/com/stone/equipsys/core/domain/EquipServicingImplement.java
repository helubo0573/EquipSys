package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备维修信息管理表实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 10:20:39
 */
 public class EquipServicingImplement implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;
    
    /***维修申请人*/
    private Long proposerId;
    
    /*** 设备编号*/
    private Long equipId;
    
    /*** 申请单id*/
    private Long applicationId;

    /*** 维修单位*/
    private Integer servicingDept;

    /*** 维修开始时间*/
    private Date servicingTimeStart;

    /*** 维修结束时间*/
    private Date servicingTimeEnd;

    /*** 故障描述*/
    private String failureBewrite;

    /*** 故障原因分析*/
    private String failureCause;

    /*** 维修情况描述*/
    private String servicingCause;

    /*** 维修结果 0:接受申请 1:维修完成 2:维修失败*/
    private Integer servicingStatus;

    /*** 维修负责人确认*/
    private Integer servicemanConfirm;

    /*** 设备操作人验收*/
    private Integer equipOpConfirm;

    /*** 车间主任确认*/
    private Integer departmentManagerConfirm;


    public EquipServicingImplement() {};
    
    public EquipServicingImplement(Long id,Long proposerId,Long equipId,Long applicationId,int servicingDept,Date servicingTimeStart,Date servicingTimeEnd,String failureBewrite,
    		String failureCause,String servicingCause,int servicingStatus,int servicemanConfirm,int equipOpConfirm,int departmentManagerConfirm) {
    	if(id!=0)	this.setId(id);
    	if(proposerId!=0) this.setProposerId(proposerId);
    	if(equipId!=0)	this.setEquipId(equipId);
    	if(applicationId!=0)	this.setApplicationId(applicationId);
    	if(servicingDept!=0)	this.setServicingDept(servicingDept);
    	if(servicingTimeStart!=null)	this.setServicingTimeStart(servicingTimeStart);
    	if(servicingTimeEnd!=null)		this.setServicingTimeEnd(servicingTimeEnd);
    	this.setFailureBewrite(failureBewrite);
    	this.setFailureCause(failureCause);
    	this.setServicingCause(servicingCause);
    	this.setServicingStatus(servicingStatus);
    	this.setServicemanConfirm(servicemanConfirm);
    	this.setEquipOpConfirm(equipOpConfirm);
    	this.setDepartmentManagerConfirm(departmentManagerConfirm);
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

    /*** 获取申请单id
    *
    * @return 申请单id
    */
    public Long getApplicationId(){
        return applicationId;
    }

    /*** 设置申请单id
    * 
    * @param applicationId 要设置的申请单id
    */
    public void setApplicationId(Long applicationId){
        this.applicationId = applicationId;
    }

    /*** 获取维修单位
    *
    * @return 维修单位
    */
    public Integer getServicingDept(){
        return servicingDept;
    }

    /*** 设置维修单位
    * 
    * @param servicingDept 要设置的维修单位
    */
    public void setServicingDept(Integer servicingDept){
        this.servicingDept = servicingDept;
    }

    /*** 获取维修开始时间
    *
    * @return 维修开始时间
    */
    public Date getServicingTimeStart(){
        return servicingTimeStart;
    }

    /*** 设置维修开始时间
    * 
    * @param servicingTimeStart 要设置的维修开始时间
    */
    public void setServicingTimeStart(Date servicingTimeStart){
        this.servicingTimeStart = servicingTimeStart;
    }

    /*** 获取维修结束时间
    *
    * @return 维修结束时间
    */
    public Date getServicingTimeEnd(){
        return servicingTimeEnd;
    }

    /*** 设置维修结束时间
    * 
    * @param servicingTimeEnd 要设置的维修结束时间
    */
    public void setServicingTimeEnd(Date servicingTimeEnd){
        this.servicingTimeEnd = servicingTimeEnd;
    }

    /*** 获取故障描述
    *
    * @return 故障描述
    */
    public String getFailureBewrite(){
        return failureBewrite;
    }

    /*** 设置故障描述
    * 
    * @param failureBewrite 要设置的故障描述
    */
    public void setFailureBewrite(String failureBewrite){
        this.failureBewrite = failureBewrite;
    }

    /*** 获取故障原因分析
    *
    * @return 故障原因分析
    */
    public String getFailureCause(){
        return failureCause;
    }

    /*** 设置故障原因分析
    * 
    * @param failureCause 要设置的故障原因分析
    */
    public void setFailureCause(String failureCause){
        this.failureCause = failureCause;
    }

    /*** 获取维修情况描述
    *
    * @return 维修情况描述
    */
    public String getServicingCause(){
        return servicingCause;
    }

    /*** 设置维修情况描述
    * 
    * @param servicingCause 要设置的维修情况描述
    */
    public void setServicingCause(String servicingCause){
        this.servicingCause = servicingCause;
    }

    /*** 获取维修结果 0:接受申请 1:维修完成 2:维修失败
    *
    * @return 维修结果 0:接受申请 1:维修完成 2:维修失败
    */
    public Integer getServicingStatus(){
        return servicingStatus;
    }

    /*** 设置维修结果 0:接受申请 1:维修完成 2:维修失败
    * 
    * @param servicingStatus 要设置的维修结果 0:接受申请 1:维修完成 2:维修失败
    */
    public void setServicingStatus(Integer servicingStatus){
        this.servicingStatus = servicingStatus;
    }

    /*** 获取维修负责人确认
    *
    * @return 维修负责人确认
    */
    public Integer getServicemanConfirm(){
        return servicemanConfirm;
    }

    /*** 设置维修负责人确认
    * 
    * @param servicemanConfirm 要设置的维修负责人确认
    */
    public void setServicemanConfirm(Integer servicemanConfirm){
        this.servicemanConfirm = servicemanConfirm;
    }

    /*** 获取设备操作人验收
    *
    * @return 设备操作人验收
    */
    public Integer getEquipOpConfirm(){
        return equipOpConfirm;
    }

    /*** 设置设备操作人验收
    * 
    * @param equipOpConfirm 要设置的设备操作人验收
    */
    public void setEquipOpConfirm(Integer equipOpConfirm){
        this.equipOpConfirm = equipOpConfirm;
    }

    /*** 获取车间主任确认
    *
    * @return 车间主任确认
    */
    public Integer getDepartmentManagerConfirm(){
        return departmentManagerConfirm;
    }

    /*** 设置车间主任确认
    * 
    * @param departmentManagerConfirm 要设置的车间主任确认
    */
    public void setDepartmentManagerConfirm(Integer departmentManagerConfirm){
        this.departmentManagerConfirm = departmentManagerConfirm;
    }

	public Long getEquipId() {
		return equipId;
	}

	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}

	public Long getProposerId() {
		return proposerId;
	}

	public void setProposerId(Long proposerId) {
		this.proposerId = proposerId;
	}

}