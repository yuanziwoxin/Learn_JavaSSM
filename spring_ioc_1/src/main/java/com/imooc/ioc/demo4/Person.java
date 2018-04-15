package com.imooc.ioc.demo4;

public class Person {

    private String name;
    private int age;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    private Cat cat;

    //注意：在使用setter方法进行属性注入的时候不能设置构造函数
    /*public Person(String name, int age) {
        this.name = name;
        this.age=age;
    }
   */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cat=" + cat +
                '}';
    }
}
