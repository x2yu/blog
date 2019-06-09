package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.entity.CommentExample;
import java.util.List;

public interface CommentMapper {
    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);
}