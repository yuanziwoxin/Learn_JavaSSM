package com.yuan.shop.action;

import com.yuan.shop.bean.ArticleType;
import com.yuan.shop.service.ShopService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getArticleTypes")
public class ArticleTypesServlet extends HttpServlet {

    /*
    注意这里不能使用注解的形式(如@Autowired、@Resource)对业务层对象进行注入，
    因为Servlet本身对象是没有交给Spring容器对象管理的
     */
//    @Resource
    private ShopService shopService = null;//定义业务层对象

    //在Servlet启用之前会掉用这个方法
    @Override
    public void init() throws ServletException {
        super.init();
        //在这个方法中获取Spring容器，然后从容器中得到业务层对象
        ServletContext servletContext=this.getServletContext();//首先获得ServletContext上下文对象
        //获取一个Web目录的上下文对象，这其实就是一个Spring容器对象
        WebApplicationContext webApplicationContext=WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //通过Spring容器得到这个bean（对象），即这个业务层对象
        shopService= (ShopService) webApplicationContext.getBean("shopService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        //查询所有的商品类型信息
        List<ArticleType> articleTypes= shopService.getArticleTypes();

        System.out.println(articleTypes);

    }
}
