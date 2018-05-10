package com.yuan.shop.repository;

import com.yuan.shop.bean.Article;
import com.yuan.shop.bean.Pager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleMapper 数据访问类
 */
@Repository("articleMapper")
public interface ArticleMapper {

    /**
     * 根据相关条件查询动态查询所有商品
     * @param typeCode 一级类型编号
     * @param secondType 二级类型编号
     * @param title 搜索框输入的搜索的标题
     * @param pager 分页数据对象
     * @return
     */
    List<Article> getAllArticles(@Param("typeCode") String typeCode,
                                 @Param("secondType") String secondType,
                                 @Param("title") String title,
                                 @Param("pager") Pager pager);

    /**
     * 查询所有的记录数
     * @param typeCode 一级类型编号
     * @param secondType 二级类型编号
     * @param title 搜索框输入的标题
     * @return
     */
    int count(@Param("typeCode") String typeCode,
              @Param("secondType") String secondType,
              @Param("title") String title);

    /**
     * 删除商品（下架商品）
     * @param id
     * SQL语句较较简单，所以可以直接使用Delete注解实现
     */
    @Delete("delete from ec_article where id=#{id}")
    void deleteById(String id);

    /**
     * 根据id查询商品
     * @param id
     * @return
     *
     * 注意：因为数据库中的有些字段（如时间的字段）和实体类的字段不相关联，
     * 所以需要通过ArticleMapper.xml文件中的resultMap标签进行关联
     * 这里通过ResultMap注解引入其resultMap中的映射关系
     */
    @Select("select * from ec_article where id=#{id}")
    @ResultMap("articleResultMap")
    Article getArticleById(String id);

    /**
     * 修改商品信息
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 添加商品信息
     * @param article
     */
    void addArticle(Article article);
}