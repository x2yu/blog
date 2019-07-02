package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.ArticleCategory;
import cn.x2yu.blog.entity.ArticleCategoryExample;
import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    List<ArticleCategory> selectByExample(ArticleCategoryExample example);

    ArticleCategory selectByPrimaryKey(Long id);

    ArticleCategory selectByArticleId(Long id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByArticleIdSelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}