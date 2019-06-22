package cn.x2yu.blog.service;

import cn.x2yu.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> listAllComment();

}
