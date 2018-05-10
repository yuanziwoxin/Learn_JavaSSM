package com.yuan.shop;

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
