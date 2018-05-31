package com.yuan.restful.entity;
//课程实体类
public class Course {
    private int id;//课程编号
    private String name;//课程名称
    private double price;//课程价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
