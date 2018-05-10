package com.yuan.mybatisdemo.entity;

/**
 * Person实体类
 */
public class Person {
    private Integer id;//用户编号
    private String username;//姓名
    private String email;//邮件
    private String gender;//性别
    private Integer dept_id;//部门编号

    public Person(Integer id, String username, String email, String gender, Integer dept_id) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.dept_id = dept_id;
    }

    public Person(String username, String gender) {
        this.username = username;
        this.gender = gender;
    }

    public Person(String username, String email, String gender) {
        this.username = username;
        this.email = email;
        this.gender = gender;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dept_id=" + dept_id +
                '}';
    }
}
