---
title: 使用DTO代码生成器生成实体类和持久层的方法
---

## 使用DTO代码生成器生成实体类和持久层的方法

### 1. 引入org.fkjava.dto-2.0.RELEASE.jar 
 - （1）先是进入：File –> Project Structure；
 - （2）再找到Modules->Dependencies
点击最右侧的绿色+号；
 - （3）选择JARs or directories..或Library...都可以；
 - （4）指定jar存放的路径就可以引入jar包了；

### 2.将dto.properties配置文件进行相应修改，并复制到resources资源目录下
```
dto.driverClass = com.mysql.jdbc.Driver
dto.jdbcUrl = jdbc:mysql://localhost:3306/ytoa
dto.user = root
dto.password = root
dto.package = dto
dto.mapper = dao
dto.tablePrefix =
```
**注：**

（1）dto.package表示生成的实体类存放在哪个包中；
（2）dto.mapper表示生成的持久层放在哪个包中；
（3）dto.tablePrefix表示数据库的表名的前缀，如“ec_user”表名的前缀是ec_,没有则不填写；

### 3. 写一个带主函数的类，放在包的外面，然后执行，生成的代码放在住工程的src目录下；
如TestMain.java
```java
package com.yuan.oa;


import org.fkjava.dto.support.DTOHelper;

/**
 * 用于使用DTO代码自动生成器自动生成实体类和持久层（节省时间）
 *
 * 注意：这里的DTO代码自动生成器是自定义的（并非官方），
 * 通过resources文件夹下的dto.properties连接数据库，
 * 是用来自动生成实体类和持久层代码的。
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        DTOHelper dtoHelper=new DTOHelper();//实例化自动生成器对象
        dtoHelper.createDto();//调用代码自动生成方法
    }
}
```

 



