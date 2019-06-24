package cn.x2yu.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ArchiveArticleDto {
    private  Long id;           //文章id也是主键
    private  String title;      //文章标题
    private  String category;   //文章分类
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Date create_by;    //创建时间

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Date create_by) {
        this.create_by = create_by;
    }
}
