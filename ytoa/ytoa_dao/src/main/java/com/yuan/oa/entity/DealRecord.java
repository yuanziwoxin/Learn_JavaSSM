package com.yuan.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * DealRecord 处理记录类
 */
public class DealRecord implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;//处理记录编号
	private int claimVoucherId;//报销单编号
	private String dealSn;//处理人
	@DateTimeFormat (pattern = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date dealTime;//处理时间
	private String dealWay;//处理方式（类型）
	private String dealResult;//处理结果
	private String comment;//备注

	//另外声明处理人这个用户对象
	private Employee dealer;

	public Employee getDealer() {
		return dealer;
	}

	public void setDealer(Employee dealer) {
		this.dealer = dealer;
	}

	/** setter and getter method */
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setClaimVoucherId(int claimVoucherId){
		this.claimVoucherId = claimVoucherId;
	}
	public int getClaimVoucherId(){
		return this.claimVoucherId;
	}
	public void setDealSn(String dealSn){
		this.dealSn = dealSn;
	}
	public String getDealSn(){
		return this.dealSn;
	}
	public void setDealTime(java.util.Date dealTime){
		this.dealTime = dealTime;
	}
	public java.util.Date getDealTime(){
		return this.dealTime;
	}
	public void setDealWay(String dealWay){
		this.dealWay = dealWay;
	}
	public String getDealWay(){
		return this.dealWay;
	}
	public void setDealResult(String dealResult){
		this.dealResult = dealResult;
	}
	public String getDealResult(){
		return this.dealResult;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}

}