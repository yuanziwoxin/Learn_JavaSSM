package com.yuan.sm.global;

import com.yuan.sm.entity.Log;
import com.yuan.sm.entity.Staff;
import com.yuan.sm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService logService;


    /**
     * 添加操作日志
     * @param joinPoint 传入一个连接点对象（即那些被拦截的方法）
     */
    /*
       表示在方法执行完之后织入，织入到除SelfController类中有关用户登录和修改密码的方法和所有的方法名前缀为to的所有方法外的controller包中的所有方法
       (1)SelfController中的方法(除查看个人信息的方法)为登录相关的方法，属于登录日志；
       (2)一个操作都有toXXX.do和XXX.do两个方法，记录操作日志时只要在第二个方法完成后记录即可；
     */

    @After("execution(* com.yuan.sm.controller.*.*(..)) && !execution(* com.yuan.sm.controller.SelfController.login*(..))&& !execution(* com.yuan.sm.controller.SelfController.changePassword(..)) && !execution(* com.yuan.sm.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint){
        Log log=new Log();//实例化一个日志对象
        //通过连接点获得其拦截的目标对象（即目标类），然后获得其类，最后获得其类名
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());//设置日志中的操作的模块
        //通过连接点可以获得方法的签名对象，然后通过方法的签名对象获得方法名
        log.setOperation(joinPoint.getSignature().getName());//设置操作日志的操作（即方法名）
        /*
         * 获取HttpServletRequest对象
         * 这里通过连接点获取连接点（那些被拦截的方法）的参数的方法获取HttpServletRequest对象
         * 且HttpServletRequest对象是被拦截的方法的第一个参数
         * 例如：public void list(HttpServletRequest request, HttpServletResponse response)
         */
        HttpServletRequest request= (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session=request.getSession();//然后通过request对象获取session对象

        Object object=session.getAttribute("USER");//获取session中的对象
        Staff staff=(Staff) object;//强制转换为用户对象

        log.setOperator(staff.getName());//设置操作者的名字

        log.setResult("成功");//设置操作结果

        logService.addOperationlog(log);//添加操作日志
    }


    /**
     * 添加系统日志
     * @param joinPoint 传入一个连接点对象（即那些被拦截的方法）
     * @param e  表示抛出的异常
     *
     *       pointcut表示具体要拦截的方法
     *
     */
    /*
       表示在方法执行出现异常时，织入到除SelfController类中的方法（这属于登录日志）外controller包中的所有类和所有方法
     */
    @AfterThrowing (throwing="e",pointcut = "execution(* com.yuan.sm.controller.*.*(..)) && !execution(* com.yuan.sm.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint,Throwable e){
        Log log=new Log();//实例化一个日志对象
        //通过连接点获得其拦截的目标对象（即目标类），然后获得其类，最后获得其类名
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());//设置日志中的操作的模块
        //通过连接点可以获得方法的签名对象，然后通过方法的签名对象获得方法名
        log.setOperation(joinPoint.getSignature().getName());//设置操作日志的操作（即方法名）
        /*
         * 获取HttpServletRequest对象
         * 这里通过连接点获取连接点（那些被拦截的方法）的参数的方法获取HttpServletRequest对象
         * 且HttpServletRequest对象是被拦截的方法的第一个参数
         * 例如：public void list(HttpServletRequest request, HttpServletResponse response)
         */
        HttpServletRequest request= (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session=request.getSession();//然后通过request对象获取session对象

        Object object=session.getAttribute("USER");//获取session中的对象
        Staff staff=(Staff) object;//强制转换为用户对象

        log.setOperator(staff.getName());//设置操作者的名字

        log.setResult(e.getClass().getSimpleName());//设置系统出的错

        logService.addSystemlog(log);//添加系统日志
    }


    /**
     * 登录日志
     * @param joinPoint 传入一个连接点对象（即那些被拦截的方法）
     */
    /*
       表示在方法执行完之后织入，织入到除SelfController类中的方法和所有的方法名前缀为to的所有方法外的controller包中的所有方法
       (1)SelfController中的方法为登录相关的方法，属于登录日志；
       (2)一个操作都有toXXX.do和XXX.do两个方法，记录操作日志时只要在第二个方法完成后记录即可；
     */

    @After("execution(* com.yuan.sm.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint){
        log(joinPoint);
    }

    /**
     * 用户注销日志
     * @param joinPoint 传入一个连接点对象（即那些被拦截的方法）
     */
    //注销没有失败的说法（有些牵强），且有些值的获取只有在在注销方法之前才能获取（如注销的用户是哪个要注销前才知道），所以在用户注销方法之前织入；
    @Before("execution(* com.yuan.sm.controller.SelfController.loginout(..))")
    public void logoutLog(JoinPoint joinPoint){
        log(joinPoint);
    }

    //添加用户登录日志和注销日志的方法高度类似，所以可以把重合的代码独立出来，在需要时直接引用即可
    public void log(JoinPoint joinPoint){
        Log log=new Log();//实例化一个日志对象
        //通过连接点获得其拦截的目标对象（即目标类），然后获得其类，最后获得其类名
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());//设置日志中的操作的模块
        //通过连接点可以获得方法的签名对象，然后通过方法的签名对象获得方法名
        log.setOperation(joinPoint.getSignature().getName());//设置操作日志的操作（即方法名）
        /*
         * 获取HttpServletRequest对象
         * 这里通过连接点获取连接点（那些被拦截的方法）的参数的方法获取HttpServletRequest对象
         * 且HttpServletRequest对象是被拦截的方法的第一个参数
         * 例如：public void list(HttpServletRequest request, HttpServletResponse response)
         */
        HttpServletRequest request= (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session=request.getSession();//然后通过request对象获取session对象

        Object object=session.getAttribute("USER");//获取session中的对象
        if (object==null){
            String  account=request.getParameter("account");//登录失败则获取其输入框填写的账户
            log.setOperator(account);
            log.setResult("失败");
        }else{
            Staff staff=(Staff) object;//强制转换为用户对象

            log.setOperator(staff.getAccount());//设置登录者（注销者）的名字

            log.setResult("成功");//设置登录（注销）结果
        }
        logService.addLoginlog(log);//添加登录日志（包括注销）
    }

}
