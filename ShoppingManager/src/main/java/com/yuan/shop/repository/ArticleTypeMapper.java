package com.yuan.shop.repository;

import com.yuan.shop.bean.Article;
import com.yuan.shop.bean.ArticleType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleTypeMapper 数据访问类(持久层接口)
 */
@Repository("articleTypeMapper")
public interface ArticleTypeMapper {
    /**
     * 根据类型编号获取商品类型信息
     * @return
     */
    @Select("select * from ec_article_type where code=#{code}")
    ArticleType getArticleTypeByCode(String code);
    /**
     * 获取所有的商品种类
     * @return
     */
    @Select("select * from ec_article_type")
    List<ArticleType> getArticleTypes();

    /**
     * 获取商品的一级类型信息
     * @return
     * 注意：code这个字段包括一级类型和二级类型，一级字段长度为4个数字，
     * 所以可以通过length(code)=4这个限制条件找出一级类型
     */
    @Select("select * from ec_article_type where length(code)=4")
    List<ArticleType> getFirstArticleTypes();

    /**
     * 根据商品的二级类型查询商品的二级类型
     * @param typeCode
     * @param i
     * @return
     */
    @Select("select * from ec_article_type where code like #{typeCode} and length(code)=#{len}")
    List<ArticleType> getSecondArticleTypes(@Param("typeCode") String typeCode,@Param("len") int i);
}