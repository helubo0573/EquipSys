package com.stone.equipsys.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备维修申请实体
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-11 10:24:51
 */
 public class EquipServicingApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 主键Id*/
    private Long id;

    /*** 设备id*/
    private Long equipId;

    /*** 申请人id*/
    private Long proposer;

    /*** 申请维修时间*/
    private Date applicationTime;

    /*** 故障发生时间*/
    private Date backfireTime;

    /*** 故障简述*/
    private String remarks;

    private int status;
    
    public EquipServicingApplication() {
    	
    }
    
    public EquipServicingApplication(Long id,Long equipid,Long proposer,Date applicationtime,Date backfiretime,String remarks) {
    	if(id!=0)	this.setId(id);
    	this.setEquipId(equipid);
    	this.setProposer(proposer);
    	this.setApplicationTime(applicationtime);
    	this.setBackfireTime(backfiretime);
    	this.setRemarks(remarks);
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

    /*** 获取设备id
    *
    * @return 设备id
    */
    public Long getEquipId(){
        return equipId;
    }

    /*** 设置设备id
    * 
    * @param equipId 要设置的设备id
    */
    public void setEquipId(Long equipId){
        this.equipId = equipId;
    }

    /*** 获取申请人id
    *
    * @return 申请人id
    */
    public Long getProposer(){
        return proposer;
    }

    /*** 设置申请人id
    * 
    * @param proposer 要设置的申请人id
    */
    public void setProposer(Long proposer){
        this.proposer = proposer;
    }

    /*** 获取申请维修时间
    *
    * @return 申请维修时间
    */
    public Date getApplicationTime(){
        return applicationTime;
    }

    /*** 设置申请维修时间
    * 
    * @param applicationTime 要设置的申请维修时间
    */
    public void setApplicationTime(Date applicationTime){
        this.applicationTime = applicationTime;
    }

    /*** 获取故障发生时间
    *
    * @return 故障发生时间
    */
    public Date getBackfireTime(){
        return backfireTime;
    }

    /*** 设置故障发生时间
    * 
    * @param backfireTime 要设置的故障发生时间
    */
    public void setBackfireTime(Date backfireTime){
        this.backfireTime = backfireTime;
    }

    /*** 获取故障简述
    *
    * @return 故障简述
    */
    public String getRemarks(){
        return remarks;
    }

    /*** 设置故障简述
    * 
    * @param remarks 要设置的故障简述
    */
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}