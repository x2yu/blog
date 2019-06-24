package cn.x2yu.blog.controller;

import cn.x2yu.blog.dto.*;
import cn.x2yu.blog.entity.Comment;
import cn.x2yu.blog.service.ArticleService;
import cn.x2yu.blog.service.CategoryService;
import cn.x2yu.blog.service.CommentService;
import cn.x2yu.blog.util.FormatFile;
import cn.x2yu.blog.util.ReadMd;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    ReadMd readMd;
    @Autowired
    FormatFile formatFile;
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
            articleContent = readMd.readMdFile(articleTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    //    System.out.println(articleContent);
        return articleContent;
    }

    /**
     * 获取最新发布的三篇文章
     * */
    @ApiOperation("获取最新发布的三篇文章")
    @GetMapping("articles/list/latest")
    public List<ArticleSimpleDto> listArticleSimple(){
        List<ArticleSimpleDto>articleSimpleDtos = new ArrayList<>();
        articleSimpleDtos = articleService.listArticleSimple();
        return articleSimpleDtos;
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
    @GetMapping("comments/list/{page}")
    public PageInfo listAllComment(@PathVariable("page") Integer pageNum)throws Exception{


        PageHelper.startPage(pageNum,5);
        List<Comment> commentList = commentService.listAllComment();

        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return pageInfo;
    }

    /**
     * 增加一条留言
     * */
    @ApiOperation("增加一条留言")
    @PostMapping("comments")
    public String addComment(@RequestBody Comment comment, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        String path = request.getSession().getServletContext().getRealPath("markdown");
        System.out.println(path);
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
