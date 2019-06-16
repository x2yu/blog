package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.CommentMapper;
import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }
}
