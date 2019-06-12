package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.ArticleCategoryMapper;
import cn.x2yu.blog.dao.ArticleInfoMapper;
import cn.x2yu.blog.dao.ArticlePictureMapper;
import cn.x2yu.blog.dao.CategoryInfoMapper;
import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.entity.*;
import cn.x2yu.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章Service实现类
 * 说明：ArticleInfo里面封装了picture/content/category等信息
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticlePictureMapper articlePictureMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    public ArticleServiceImpl(){

    }


    @Override
    public void addAticle() {

    }

    @Override
    public void deleteArticle() {

    }

    @Override
    public void updateArticle() {

    }

    @Override
    public void updateArticleCategory() {

    }

    @Override
    public String getOneById(Long id) {
        //获取文章的标题
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        System.out.println(articleInfo.getTitle());
        return articleInfo.getTitle();
    }

    @Override
    public ArticlePicture getPictureByArticleId(Long id) {
        return null;
    }


    /**
     * 获取首页展示文章卡片内容集合
     * 从tbl_article_picture/tbl_article_info/tbl_article_category表中获取内容
     * */
    @Override
    public List<ArticleDto> listAll() {

        List<ArticleDto> listArticleDto = new ArrayList<>();

        //查询tbl_article_info
        ArticleInfoExample articleInfoExample = new ArticleInfoExample();
        articleInfoExample.setOrderByClause("id desc");
        // 无添加查询即返回所有
        List<ArticleInfo> listArticleInfo = articleInfoMapper.selectByExample(articleInfoExample);

        for(int i =0;i<listArticleInfo.size();i++){
            ArticleDto articleDto = new ArticleDto();
            articleDto.setId(listArticleInfo.get(i).getId());
            articleDto.setTitle(listArticleInfo.get(i).getTitle());
            articleDto.setSummary(listArticleInfo.get(i).getSummary());

            //先根据文章id获取文章和分类的关系表数据，在根据category_id查询分类名称
            ArticleCategory articleCategory = articleCategoryMapper.selectByArticleId(listArticleInfo.get(i).getId());
            Long aticleCategoryId = articleCategory.getCategory_id();

            String category = categoryInfoMapper.selectByPrimaryKey(aticleCategoryId).getName();
            articleDto.setCategory(category);

            //获取图片url
            String pictureUrl = articlePictureMapper.selectByArticleId(listArticleInfo.get(i).getId()).getPicture_url();
            articleDto.setPictyreUrl(pictureUrl);

            listArticleDto.add(articleDto);
        }

        return listArticleDto;
    }
}
