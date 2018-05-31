package com.yuan.oa.entity;

/**
 * Employee 员工类
 */
public class Employee implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String ename;//员工编号
	private String password;//密码
	private String name;//员工姓名
	private String departmentName;//部门编号
	private String post;//职务
	private Department department;//部门对象

	/** setter and getter method */
	public void setEname(String ename){
		this.ename = ename;
	}
	public String getEname(){
		return this.ename;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public String getDepartmentName(){
		return this.departmentName;
	}
	public void setPost(String post){
		this.post = post;
	}
	public String getPost(){
		return this.post;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}