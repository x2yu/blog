package cn.x2yu.blog.service;


import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.entity.ArticlePicture;

import java.util.List;

/**
 * 文章Service
 * 说明：ArticleInfo里面封装了picture/content/category等信息
 */

public interface ArticleService {
    void addAticle();
    void deleteArticle();
    void updateArticle();
    void updateArticleCategory();

    String getOneById(Long id);
    ArticlePicture getPictureByArticleId(Long id);

    List<ArticleDto>listAll();
}
