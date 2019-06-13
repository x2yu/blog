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
            articleDto.setPictureUrl(pictureUrl);

            listArticleDto.add(articleDto);
        }

        return listArticleDto;
    }

    /**
     * 根据分类名称（category）查询该分类下的文章
     * */
    @Override
    public List<ArticleDto> listArticleByCategory(Long categoryId) {

        List<ArticleDto>articleDtosByCategory = new ArrayList<>();

        //根据category_id 查询和article关系数据集合
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        articleCategoryExample.setOrderByClause("id desc");
        articleCategoryExample.createCriteria().andCategory_idEqualTo(categoryId);

        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(articleCategoryExample);

        //获取分类id
        Long category_id = articleCategories.get(0).getCategory_id();

        //封装返回的数据
        for(int i=0;i<articleCategories.size();i++){
            ArticleDto articleDto = new ArticleDto();
            Long article_id = articleCategories.get(i).getArticle_id();

            articleDto.setId(article_id);
            articleDto.setTitle(articleInfoMapper.selectByPrimaryKey(article_id).getTitle());
            articleDto.setSummary(articleInfoMapper.selectByPrimaryKey(article_id).getSummary());

            //获取分类名称
            String category = categoryInfoMapper.selectByPrimaryKey(category_id).getName();
            articleDto.setCategory(category);

            //获取图片url
            String pictureUrl = articlePictureMapper.selectByArticleId(article_id).getPicture_url();
            articleDto.setPictureUrl(pictureUrl);

            articleDtosByCategory.add(articleDto);
        }

        return articleDtosByCategory;
    }
}
