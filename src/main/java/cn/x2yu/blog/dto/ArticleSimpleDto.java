package cn.x2yu.blog.dto;


import java.util.Date;

/**
 * 简单文章数据对象
 *含id 创建时间 标题 题图
 * */
public class ArticleSimpleDto {
    private  Long id;           //文章id也是主键
    private  String title;      //文章标题
    private  String create_by;    //创建时间 这里用String储存 Date会转换为String储存
    private  String pictureUrl; //文章题图URl

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

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
