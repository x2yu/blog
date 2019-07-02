package cn.x2yu.blog.controller;

import cn.x2yu.blog.entity.ArticleCategory;
import cn.x2yu.blog.entity.ArticleInfo;
import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.service.ArticleService;
import cn.x2yu.blog.service.CategoryService;
import cn.x2yu.blog.service.CommentService;
import cn.x2yu.blog.util.UploadAndDeleteFile;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后端控制器
 * */
@RestController
@RequestMapping("/admin")
public class BackController {
    @Autowired
    CommentService commentService;
    @Autowired
    UploadAndDeleteFile uploadAndDeleteFile;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

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
    public String updateArticle(@PathVariable ("id") Long articleId,@RequestParam(value = "article_img",required = false) MultipartFile img,
                                @RequestParam("article_title") String title, @RequestParam("article_summary") String summary,
                                @RequestParam("article_category")String category, @RequestParam("article_content")String content){

        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(articleId);
        articleInfo.setTitle((title+".md"));
        articleInfo.setSummary(summary);

        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo = categoryService.getCategoryInfoByName(category);
        Long categoryId = categoryInfo.getId();

        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticle_id(articleId);
        articleCategory.setCategory_id(categoryId);

        //数据库更新
        articleService.updateArticleInfo(articleInfo);
        articleService.updateArticleCategory(articleCategory);
        articleService.updateArticlePic(articleId);

        //图片资源更新
        uploadAndDeleteFile.articleUpload(articleId,img);

        //文章文件更新



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
    public String addCategory(@RequestParam(value = "category_img",required = false) MultipartFile img,@RequestParam("category_name") String name,
                              @RequestParam("category_subtitle") String subtitle){

        if(img.isEmpty()){
            return "上传失败";
        }

        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName(name);
        categoryInfo.setSubtitle(subtitle);

        //存入CategoryInfo
        categoryService.addCategoryInfo(categoryInfo);

        //用数据库中对应分类的id命名
        Long categoryId = categoryService.getCategoryInfoByName(name).getId();

        //上传图片
        uploadAndDeleteFile.categoryUpload(categoryId,img);

        return null;
    }


    /**
     * 根据id删除分类
     * */
    @ApiOperation("根据id删除分类")
    @DeleteMapping("categories/{id}")
    public String deleteCategory(@PathVariable("id")Long categoryId){
        categoryService.deleteCategory(categoryId);
        uploadAndDeleteFile.categoryDelete(categoryId);
        return null;
    }

    /**
     * 更新编辑分类
     * */
    @ApiOperation("更新编辑分类")
    @PutMapping("categories/{id}")
    public String updateCategory(@PathVariable ("id") Long categoryId,@RequestParam("category_img") MultipartFile img,
                                 @RequestParam("category_name") String name, @RequestParam("category_subtitle") String subtitle){
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName(name);
        categoryInfo.setSubtitle(subtitle);
        categoryInfo.setId(categoryId);
        //数据库更新
        categoryService.updateCategory(categoryInfo);
        //图片资源更新
        uploadAndDeleteFile.categoryUpdate(categoryId,img);

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
    public String deleteComment(@PathVariable ("id") Long commentId){
        commentService.deleteComment(commentId);
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
