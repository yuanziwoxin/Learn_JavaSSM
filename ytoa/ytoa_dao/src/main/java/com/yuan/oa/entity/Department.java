package com.yuan.oa.entity;

/**
 * Department 部门类
 */
public class Department implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String dname;//部门编号
	private String name;//部门名称
	private String address;//部门地址

	/** setter and getter method */
	public void setDname(String dname){
		this.dname = dname;
	}
	public String getDname(){
		return this.dname;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}

}