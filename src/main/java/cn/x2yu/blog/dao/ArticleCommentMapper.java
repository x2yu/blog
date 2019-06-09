package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.ArticleComment;
import cn.x2yu.blog.entity.ArticleCommentExample;
import java.util.List;

public interface ArticleCommentMapper {
    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    List<ArticleComment> selectByExample(ArticleCommentExample example);
}