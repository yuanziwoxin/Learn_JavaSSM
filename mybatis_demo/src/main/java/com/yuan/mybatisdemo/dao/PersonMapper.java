package com.yuan.mybatisdemo.dao;

import com.yuan.mybatisdemo.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PersonMapper {
    /**
     * 根据id删除用户
     * @param id
     */
    void deletePerson(Integer id);

    /**
     * 根据姓名和性别获取人员信息
     * @param name
     * @param gender
     * (1) mybatis默认传递多参数的方式
     */
//    Person getPersonByNameAndGender(String name, String gender);

    /**
     *
     * @param person
     * @return
     * (2) 通过javaBean的方式传递多参数
     */
   // Person getPersonByNameAndGender(Person person);

    /**
     *
     * @param param
     * @return
     * (3)通过Map的方式传递多参数
     */
//    Person getPersonByNameAndGender(Map<String,Object> param);

    /**
     *
     * @param username
     * @param gender
     * @return
     * (4) 通过注解的方式传递方式
     */
    Person getPersonByNameAndGender(@Param("username") String username,@Param("gender") String gender);

    /**
     * 参数为Collection接口
     * @param collection
     * @return
     */
    //Person getPersonByCollection(Collection collection);

    /**
     * 参数为数组
     * @param ids
     * @return
     */
    Person getPersonByCollection(int [] ids);

    /**
     * 测试foreach迭代，根据多个id批量查询
     * @param ids
     * @return
     */
    List<Person> getPersonsByIds(int [] ids);

    /**
     * 批量增加人员信息(利用MySQL数据库对批量插入的两种方式)
     * @param list
     * @return
     */
    int addPersons(@Param("persons") List<Person> list);

    /**
     * 批量插入人员信息（利用基于SqlSession的ExecutorType进行批量插入）
     * @param person
     * @return
     */
    int addPerson(Person person);

    /**
     * 查询所有人员信息
     * @return
     */
    List<Person> getAllPersons();
}
