package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.ArticleContent;
import cn.x2yu.blog.entity.ArticleContentExample;
import java.util.List;

public interface ArticleContentMapper {
    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    List<ArticleContent> selectByExampleWithBLOBs(ArticleContentExample example);

    List<ArticleContent> selectByExample(ArticleContentExample example);
}