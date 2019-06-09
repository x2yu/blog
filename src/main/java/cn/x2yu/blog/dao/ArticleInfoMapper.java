package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.ArticleInfo;
import cn.x2yu.blog.entity.ArticleInfoExample;
import java.util.List;

public interface ArticleInfoMapper {
    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);
}