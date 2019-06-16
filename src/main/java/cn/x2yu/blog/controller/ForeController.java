package cn.x2yu.blog.controller;

import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.dto.CategoryDto;
import cn.x2yu.blog.dto.CategorySimpleDto;
import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.service.ArticleService;
import cn.x2yu.blog.service.CategoryService;
import cn.x2yu.blog.service.CommentService;
import cn.x2yu.blog.util.ReadMd;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 * */
@RestController
@RequestMapping("/api")
public class ForeController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    /**
     * 获取所有文章
     * */
    @ApiOperation("获取所有文章")
    @GetMapping("articles/list")
    public List<ArticleDto> listAllArticleInfo(){

        List<ArticleDto> articleDtos = articleService.listAll();

        return articleDtos;
    }


    /**
     * 获取一个分类下的所有文章
     * */
    @ApiOperation("获取一个分类下的所有文章")
    @GetMapping("articles/list/categories/{id}")
    public List<ArticleDto> listArticleByCategory(@PathVariable("id") Long id){

        List<ArticleDto> articleDtosByCategory = articleService.listArticleByCategory(id);

        return articleDtosByCategory;
    }

    /**
     * 获取指定id文章
     * */
    @ApiOperation("获取指定id文章")
    @GetMapping("articles/{id}")
    public String getArticleById(@PathVariable ("id") Long articleId) {
        String articleTitle = articleService.getOneById(articleId);
        String articleContent="";
        try{
            ReadMd readMd = new ReadMd();
            articleContent = readMd.readMdFile(articleTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    //    System.out.println(articleContent);
        return articleContent;
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
     * 获取所有分类带图片
     * */
    @ApiOperation("获取所有分类")
    @GetMapping("categories/list")
    public List<CategoryDto> listAllCategoryInfo(){

        List<CategoryDto>categoryDtos = categoryService.listAllCategory();

        return categoryDtos;
    }

    /**
     * 获取仅含id和名称的分类数据集
     * */
    @ApiOperation("获取仅含id和名称的分类数据集")
    @GetMapping("categories/simple/list")
    public List<CategorySimpleDto> listSimpleCategories(){

        List<CategorySimpleDto> categorySimpleDtos = categoryService.listSimpleCategory();

        return categorySimpleDtos;
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
    public String addComment(@RequestBody Comment comment, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        comment.setIp(ip);
        commentService.addComment(comment);
        return "null";
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
