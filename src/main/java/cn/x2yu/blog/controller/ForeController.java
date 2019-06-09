package cn.x2yu.blog.controller;

import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.entity.Comment;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器
 * */
@RestController
@RequestMapping("/api")
public class ForeController {

    /**
     * 获取所有文章
     * */
    @ApiOperation("获取所有文章")
    @GetMapping("articles/list")
    public List<ArticleDto> listAllArticleInfo(){
        return null;
    }


    /**
     * 获取一个分类下的所有文章
     * */
    @ApiOperation("获取一个分类下的所有文章")
    @GetMapping("articles/list/categories/{id}")
    public List<ArticleDto> listArticleByCategory(){
        return null;
    }

    /**
     * 获取指定id文章
     * */
    @ApiOperation("获取指定id文章")
    @GetMapping("articles/{id}")
    public ArticleDto getArticleById(){
        return null;
    }

    /**
     * 模糊查询获取文章
     * */
    @ApiOperation("模糊查询获取文章")
    @GetMapping("articles/search")
    public List<ArticleDto> getArticleBySearch(){
        return null;
    }

    /*-----------------分类api分割线------------------*/


    /**
     * 获取所有分类
     * */
    @ApiOperation("获取所有分类")
    @GetMapping("categories/list")
    public List<CategoryInfo> listAllCategoryInfo(){
        return null;
    }


    /*----------------留言评论api分割线--------------*/

    /**
     * 获取所有留言（留言板）
     * */
    @ApiOperation("获取所有留言")
    @GetMapping("comments/list")
    public List<Comment> listAllComment(){
        return null;
    }

    /**
     * 增加一条留言
     * */
    @ApiOperation("增加一条留言")
    @PostMapping("comments")
    public String addComment(){
        return null;
    }

    /**
     *通过文章id获取一篇文章的评论
     * */
    @ApiOperation("通过文章id获取一篇文章的评论")
    @GetMapping("comments/articles/{id}")
    public List<Comment> listCOmmentByArticle(){
        return null;
    }

    /**
     * 给某篇文章增加一条评论
     * */
    @ApiOperation("给某篇文章增加一条评论")
    @PostMapping("comments/articles/{id}")
    public String addArticleComment(){
        return null;

    }

}
