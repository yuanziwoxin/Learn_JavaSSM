package com.yuan.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * ClaimVoucher 报销单类
 */
public class ClaimVoucher implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;//报销单编号
	private String cause;//事由
	private String createSn;//创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date createTime;//创建时间
	private String nextDealSn;//待处理人
	private double totalAmount;//总金额
	private String status;//状态

	//另外要定义创建人和待处理人这两个用户对象
	private Employee creater;
	private Employee nextDealer;

	public Employee getCreater() {
		return creater;
	}

	public void setCreater(Employee creater) {
		this.creater = creater;
	}

	public Employee getNextDealer() {
		return nextDealer;
	}

	public void setNextDealer(Employee nextDealer) {
		this.nextDealer = nextDealer;
	}


	/** setter and getter method */
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setCause(String cause){
		this.cause = cause;
	}
	public String getCause(){
		return this.cause;
	}
	public void setCreateSn(String createSn){
		this.createSn = createSn;
	}
	public String getCreateSn(){
		return this.createSn;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	public void setNextDealSn(String nextDealSn){
		this.nextDealSn = nextDealSn;
	}
	public String getNextDealSn(){
		return this.nextDealSn;
	}
	public void setTotalAmount(double totalAmount){
		this.totalAmount = totalAmount;
	}
	public double getTotalAmount(){
		return this.totalAmount;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}

}