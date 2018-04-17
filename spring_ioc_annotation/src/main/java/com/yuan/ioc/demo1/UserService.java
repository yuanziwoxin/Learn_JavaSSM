package com.yuan.ioc.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring的Bean管理的注解方式
 *  *传统方式是在XML中配置<bean id="" class=""></bean>
 */

@Service("userService")
public class UserService {
    //普通属性值设置用value注解
    @Value("红烧排骨")
    private String food;

    //对象值设置用Autowired和Qualifier注解组合使用，或者使用Resource注解
    /*@Autowired
    @Qualifier("userDao")*/
    @Resource(name = "userDao")
    private UserDao userDao;
    public String  sayHello(String name){
        return "Hello,"+name;
    }

    public void eat(){
        System.out.println("Eat:"+food);
    }

    public void save(){
        System.out.println("UserService中的save方法....");
        userDao.save();//对象已经通过前面的注解注入进来了，因此可以使用userDao中的方法；
    }

}
