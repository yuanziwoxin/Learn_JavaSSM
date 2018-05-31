package com.yuan.oa.entity;

/**
 * ClaimVoucherItem 报销单明细类
 */
public class ClaimVoucherItem implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;//报销明细编号
	private int claimVoucherId;//报销单编号
	private String item;//费用类别
	private double amount;//金额
	private String comment;//描述

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
	public void setItem(String item){
		this.item = item;
	}
	public String getItem(){
		return this.item;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAmount(){
		return this.amount;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}

}