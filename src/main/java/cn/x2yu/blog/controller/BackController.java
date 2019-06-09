package cn.x2yu.blog.controller;

import cn.x2yu.blog.entity.CategoryInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 后端控制器
 * */
@RestController
@RequestMapping("/admin")
public class BackController {

    /**
     * 增加一篇文章
     */
    @ApiOperation("增加一篇文章")
    @PostMapping("articles")
    public String addArticle(){
        return null;
    }

    /**
     * 根据id删除一篇文章
     * */
    @ApiOperation("根据id删除一篇文章")
    @DeleteMapping("articles/{id}")
    public String deleteArticle(){
        return null;
    }

    /**
     * 修改/更新一篇文章
     * */
    @ApiOperation("修改/更新一篇文章")
    @PutMapping("articles/{id}")
    public String updateArticle(){
        return null;
    }

    /**
     * 修改文章分类
     * */
    @ApiOperation("修改文章分类")
    @PutMapping("articles/categories/{id}")
    public String updateArticleCategory(){
        return null;
    }


    /**
     * 根据题图id修改文章题图
     * */
    @ApiOperation("根据题图id修改文章题图")
    @PutMapping("articles/pictures/{id}")
    public String updateArticlePicture(){
        return null;
    }

    /*----------------后台分类管理api分界线--------------------*/

    /**
     * 增加一个分类
     * */
    @ApiOperation("增加一个分类")
    @PostMapping("categories")
    public String addCategory(){
        return null;
    }


    /**
     * 根据id删除分类
     * */
    @ApiOperation("根据id删除分类")
    @DeleteMapping("categories/{id}")
    public String deleteCategory(){
        return null;
    }

    /**
     * 更新编辑分类
     * */
    @ApiOperation("更新编辑分类")
    @PutMapping("categories/{id}")
    public String updateCategory(){
        return null;
    }

    /**
     * 根据id获取对应分类信息
     * */
    @ApiOperation("根据id获取对应分类信息")
    @GetMapping("categories/{id}")
    public CategoryInfo getCategory(){
        return null;
    }

    /*-----------------留言评论api分界线-----------------------*/

    /**
     * 回复留言/评论
     * 通过id去找到对应的Email
     * */
    @ApiOperation("回复留言/评论")
    @GetMapping("comments/reply/{id")
    public String replyComment(){
        return null;
    }


    /**
     * 通过id删除一条留言
     * */
    @ApiOperation("通过id删除一条留言")
    @DeleteMapping("comments/{id}")
    public String deleteComment(){
        return null;
    }

    /**
     *通过评论id删除文章的评论
     * */
    @ApiOperation("通过评论id删除文章的评论")
    @DeleteMapping("comments/articles/{id}")
    public String deleteArticleComment(){
        return null;
    }
}
