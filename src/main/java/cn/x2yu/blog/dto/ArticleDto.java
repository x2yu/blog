package cn.x2yu.blog.dto;

import java.util.Date;

/**
 * 拼接的文章对象 主页（index）使用
 * **/
public class ArticleDto {
    private  Long id;           //文章id也是主键
    private  String title;      //文章标题
    private  String summary;    //文章摘要
    private  String category;   //文章分类
    private  String pictyreUrl; //文章题图URl

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPictyreUrl() {
        return pictyreUrl;
    }

    public void setPictyreUrl(String pictyreUrl) {
        this.pictyreUrl = pictyreUrl;
    }
}
