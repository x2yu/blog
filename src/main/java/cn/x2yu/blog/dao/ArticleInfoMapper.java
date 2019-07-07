package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.ArticleInfo;
import cn.x2yu.blog.entity.ArticleInfoExample;
import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    ArticleInfo selectByPrimaryKey(Long id);

    ArticleInfo selectByArticleName(String title);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
}