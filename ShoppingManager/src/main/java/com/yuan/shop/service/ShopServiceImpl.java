package com.yuan.shop.service;

import com.yuan.shop.bean.Article;
import com.yuan.shop.bean.ArticleType;
import com.yuan.shop.bean.Pager;
import com.yuan.shop.bean.User;
import com.yuan.shop.repository.ArticleMapper;
import com.yuan.shop.repository.ArticleTypeMapper;
import com.yuan.shop.repository.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

    //获取持久层接口（数据访问层接口）
    @Resource(name = "articleTypeMapper")
    private ArticleTypeMapper articleTypeMapper;

    @Resource(name = "articleMapper")
    private ArticleMapper articleMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public List<ArticleType> getArticleTypes() {
        return articleTypeMapper.getArticleTypes();
    }

    @Override
    public Map<String, Object> login(String loginName, String password) {

        Map<String,Object> results=new HashMap<>();

        //如果获取的参数（登录名和密码）为空
        if (String.valueOf(loginName).isEmpty()||String.valueOf(password).isEmpty())
        {
            results.put("code",0);
            results.put("msg","登录名或密码为空");
        }
        else //若参数不为空
        {
            User user=userMapper.getUserByloginName(loginName);
            if (user!=null) //如果存在该登录名
            {
                if (user.getPassword().equals(password))//密码匹配正确
                {
                    results.put("code",1);
                    results.put("msg",user);//将查询到的用户对象封装到Map集合中
                }
                else  //密码匹配错误
                {
                    results.put("code",2);
                    results.put("msg","密码错误！");
                }
            }
            else //不存在该登录名
            {
                results.put("code",3);
                results.put("msg","登录名不存在");
            }
        }
        return results;
    }

    @Override
    public List<ArticleType> loadFirstArticleTypes() {
        List<ArticleType> firstArticleTypes=null;
        firstArticleTypes=articleTypeMapper.getFirstArticleTypes();
        return firstArticleTypes;
    }

    @Override
    public List<Article> SearchArticles(String typeCode, String secondType, String title, Pager pager) {
        //查询当前条件下有多少条数据
        int totalCount=articleMapper.count(typeCode,secondType,title);//获取总记录数
        pager.setTotalCount(totalCount);//设置总记录数
        return articleMapper.getAllArticles(typeCode,secondType,title,pager);
    }

    @Override
    public List<ArticleType> loadSecondArticleTypes(String typeCode, int i) {
        //注意：i表示二级类型编号的长度，这里typeCode+"%"是为了实现数据库的模糊查询
        return articleTypeMapper.getSecondArticleTypes(typeCode+"%",i);
    }

    @Override
    public void deleteById(String id) {
        articleMapper.deleteById(id);
    }

    @Override
    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void saveArticle(Article article) {
        article.setCreateDate(new Date());//设置添加商品时间
        articleMapper.addArticle(article);
    }

}
