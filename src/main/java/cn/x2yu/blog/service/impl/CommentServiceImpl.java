package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.CommentMapper;
import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.entity.CommentExample;
import cn.x2yu.blog.service.CommentService;
import cn.x2yu.blog.util.FormatFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    FormatFile formatFile;
    /**
     * 添加一条留言
     * */
    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    /**
     * 删除一条留言
     * */
    @Override
    public void deleteComment(Long id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有留言
     * */
    @Override
    public List<Comment> listAllComment(){

        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_by desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);

        return commentList;
    }

}
