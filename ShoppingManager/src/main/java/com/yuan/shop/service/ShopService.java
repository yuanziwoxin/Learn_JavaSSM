package com.yuan.shop.service;

import com.yuan.shop.bean.Article;
import com.yuan.shop.bean.ArticleType;
import com.yuan.shop.bean.Pager;

import java.util.List;
import java.util.Map;

public interface ShopService {
    /**
     * 获取所有商品的种类
     * @return
     */
    List<ArticleType> getArticleTypes();

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    Map<String,Object> login(String loginName,String password);

    /**
     * 获取商品的一级类型信息
     * @return
     */
    List<ArticleType> loadFirstArticleTypes();

    /**
     * 查询所有的商品
     * @param typeCode 一级类型编号
     * @param secondType 二级类型编号
     * @param title 搜索框输入的查询的标题
     * @param pager 分页数据对象
     * @return
     */
    List<Article> SearchArticles(String typeCode,String secondType,String title,Pager pager);

    /**
     * 获取商品的二级类型
     * @param typeCode
     * @param i
     * @return
     */
    List<ArticleType> loadSecondArticleTypes(String typeCode, int i);

    /**
     * 删除商品
     * @param id
     */
    void deleteById(String id);

    /**
     * 预览商品
     * @param id
     * @return
     */
    Article getArticleById(String id);

    /**
     * 修改商品信息
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 保存商品信息
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 获取总记录数
     * @param typeCode 一级类型编号
     * @param secondType 二级类型编号
     * @param title 标题
     * @return
     */
//    int count(String typeCode, String secondType, String title);
}
