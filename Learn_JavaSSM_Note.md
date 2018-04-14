---
title: Java SSM框架整合笔记
---

# Java SSM框架整合笔记

@(Java SSM)

## 1. Spring的IOC的底层实现原理

### （1）OCP原则

即open-close原则：对程序扩展是open的，对修改原码是close的，即尽量做到不修改程序的源代码，实现对程序的扩展。

### （2）控制反转（IOC）

**控制反转**（Inversion of Control），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。

实现控制反转的两种常用方式：
- **依赖注入**（Dependency Injection，简称**DI**）：**被动**地接受对象，在类A的实例创建过程中即创建了依赖的B对象，通过类型或名称来判断不同的对象注入到不同的属性中；依赖注入的实现方式：
   -  基于接口
   -  基于set方法
   -  基于构造函数
   -  基于注解
- **依赖查找**（Dependency Lookup）：**主动**索取相应类型的对象，获得依赖对象的时间也可以在代码中自由控制。

通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体，将其所依赖的对象的引用传递给它。也可以说，依赖被注入到对象中。

## 2. Spring Bean管理

### （1）三种实例化Bean的方式

1. 使用**类构造器**实例化（默认无参数）

applicationContext.xml
```xml
    <!--第一种：使用无参数构造器的方式-->
    <bean id="bean1" class="com.imooc.ioc.demo2.Bean1"/>
```
Bean1.java
```java
public class Bean1 {
    public Bean1(){
        System.out.println("Bean1被实例化....");
    }
}
```
2. 使用**静态工厂方法**实例化（简单工厂模式）

applicationContext.xml
```xml
 <!--第二种：使用静态工厂方法的方式-->
    <!--注意要加factory-method，即工厂中的方式 -->
    <bean id="bean2" class="com.imooc.ioc.demo2.Bean2Factory" factory-method="createBean2"/>
```
Bean2.java
```java
/**
 * Bean的三种实例化方式之：使用静态工厂方法的方式
 */
public class Bean2 {

}
```
Bean2Factory.java
```java
/**
 * Bean2的静态工厂
 */
public class Bean2Factory {
    //与实例工厂方法实例Bean的方式的主要差别就是：这里的static
    public static Bean2 createBean2(){ //这里的名字可以随便取（这里取的是createBean2）
        System.out.println("Bean2被实例化了....");
        return new Bean2();//返回第二个实例
    }
}
```
3. 使用**实例工厂方法**实例化（工厂方法模式）


applicationContext.xml
```xml
    <!--第三种：使用实例工厂方法的方式-->
    <bean id="bean3Factory" class="com.imooc.ioc.demo2.Bean3Factory"/>
    <bean id="bean3" factory-bean="bean3Factory" factory-method="createBean3"/>
```
Bean3.java
```java
public class Bean3 {

}
```
Bean3Factory.java
```java
/**
 * Bean3的实例工厂
 */
public class Bean3Factory {
    public Bean3 createBean3(){
        System.out.println("Bean3被实例化了....");
        return new Bean3();
    }
}
```
测试Bean的3种实例化方式的代码

SpringBeanTest.java
```java
package com.imooc.ioc.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {
    /**
     * 测试三种实例化Bean的方法之：使用无参数构造器的方式
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean1 bean1= (Bean1) applicationContext.getBean("bean1");
        //在获得类的时候就执行了该类的构造函数

    }

    /**
     * 测试三种实例化Bean的方法之：使用静态工厂方法的方式
     */
    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean2 bean2= (Bean2) applicationContext.getBean("bean2");

    }

    /**
     * 测试三种实例化Bean的方法之：使用实例工厂方法的方式
     */
    @Test
    public void demo3(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean3 bean3= (Bean3) applicationContext.getBean("bean3");
    }
}
```

### （2）Bean的配置
- id和name
  - 一般情况下，装配一个Bean时，通过制定一个id属性作为Bean的名称；
  - id属性在IOC容器中必须是**唯一的**；
  - 如果Bean的名称中**含特殊字符**，就需要使用**name**属性（**此时不能使用id属性**）；
- class
 - class用于设置一个类的完全路径名称，主要作用是**IOC容器生成类的实例**；

### （3）Bean的作用域

|类别|说明|
|:-------:|:-----------:|
|**singleton**|在SpringIOC容器中仅存在一个Bean实例，Bean以**单实例**的方式存在|
|**prototype**|每次调用getBean()都会返回一个**新的的实例**|
|**request**|每次**HTTP**请求都会创建一个新的Bean，该作用域**仅适用于WebApplictionContext环境**|
|**session**|**同一个HTTP Session共享一个Bean**，**不同的HTTP Session使用不同的Bean**。该作用域**仅适用于WebApplicationContext环境**|

例：
（1）缺省scope属性，表示Bean的作用域为默认的singleton，即单实例的方式；
```xml
<bean id="person" class="com.imooc.ioc.demo3.Person"/>
```
（2）作用域为prototype；
```xml
<bean id="person" class="com.imooc.ioc.demo3.Person" scope="prototype">
```
### （4）Bean的生命周期（难点）

1. instantiate  bean （对象实例化）；
2. populate properties（封装属性）；
3. 如果Bean实现BeanNameAware，执行setBeanName； 
4. 如果Bean实现BeanFactoryAware或者ApplicationContextAware，设置工厂 setBeanFactory或者上下文对象setApplicationContext；
5. 如果存在类实现BeanPostProcessor（后处理Bean），执行postProcessorBeforeInitialization；
6. 如果Bean实现InitializingBean，执行afterPropertiesSet；
7. 调用 < bean init-method="init"> 指定初始化方法init，初始化Bean对象；
8. 如果存在类实现BeanPostProcessor（处理Bean），执行postProcessAfterInitialization;
9. **执行业务处理**;
10. 如果Bean实现DisposableBean，执行destroy;（销毁Spring）
11. 调用< bean destory-method="customerDestroy" > 指定销毁方法；
