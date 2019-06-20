package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.CommentMapper;
import cn.x2yu.blog.dto.CommentDto;
import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.entity.CommentExample;
import cn.x2yu.blog.service.CommentService;
import cn.x2yu.blog.util.FormatFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CommentDto> listAllComment()throws Exception{

        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_by desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);

        List<CommentDto>commentDtoList = new ArrayList<>();

        for(int i=0;i<commentList.size();i++){
            Long id = commentList.get(i).getId();
            String name = commentList.get(i).getName();
            String email = commentList.get(i).getEmail();
            String content = commentList.get(i).getContent();

            String newDate = formatFile.formatCommentDate(commentList.get(i).getCreate_by());
            String create_by = newDate;

            String ip = commentList.get(i).getIp();

            CommentDto commentDto = new CommentDto();
            commentDto.setId(id);
            commentDto.setName(name);
            commentDto.setEmail(email);
            commentDto.setContent(content);
            commentDto.setCreate_by(create_by);
            commentDto.setIp(ip);

            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }
}
