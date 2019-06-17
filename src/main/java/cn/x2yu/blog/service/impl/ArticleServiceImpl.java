package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.ArticleCategoryMapper;
import cn.x2yu.blog.dao.ArticleInfoMapper;
import cn.x2yu.blog.dao.ArticlePictureMapper;
import cn.x2yu.blog.dao.CategoryInfoMapper;
import cn.x2yu.blog.dto.ArticleDto;
import cn.x2yu.blog.dto.ArticleSimpleDto;
import cn.x2yu.blog.entity.*;
import cn.x2yu.blog.service.ArticleService;
import cn.x2yu.blog.util.FormatFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    FormatFile formatFile;

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
            Long articleId = listArticleInfo.get(i).getId();
            String title = listArticleInfo.get(i).getTitle();
            title = formatFile.formatArticleTitle(title);
            String summary = listArticleInfo.get(i).getSummary();


            articleDto.setId(articleId);
            articleDto.setTitle(title);
            articleDto.setSummary(summary);

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

        if(articleCategories.size()==0){
            return null;
        }else {
        //获取分类id
        Long category_id = articleCategories.get(0).getCategory_id();

        //封装返回的数据
        for(int i=0;i<articleCategories.size();i++){
            ArticleDto articleDto = new ArticleDto();
            Long article_id = articleCategories.get(i).getArticle_id();
            String title = articleInfoMapper.selectByPrimaryKey(article_id).getTitle();
            title = formatFile.formatArticleTitle(title);
            String summary = articleInfoMapper.selectByPrimaryKey(article_id).getSummary();

            articleDto.setId(article_id);
            articleDto.setTitle(title);
            articleDto.setSummary(summary);

            //获取分类名称
            String category = categoryInfoMapper.selectByPrimaryKey(category_id).getName();
            articleDto.setCategory(category);

            //获取图片url
            String pictureUrl = articlePictureMapper.selectByArticleId(article_id).getPicture_url();
            articleDto.setPictureUrl(pictureUrl);

            articleDtosByCategory.add(articleDto);
        }

            }

        return articleDtosByCategory;
    }

    /**
     * 查询用于展示最新文章的简单文章数据体
     * */
    @Override
    public List<ArticleSimpleDto> listArticleSimple() {

        List<ArticleSimpleDto>articleSimpleDtos = new ArrayList<>();

        List<ArticleInfo> articleInfos = new ArrayList<>();
        ArticleInfoExample articleInfoExample = new ArticleInfoExample();
        articleInfoExample.setOrderByClause("create_by desc");
        articleInfos = articleInfoMapper.selectByExample(articleInfoExample);

        if(articleInfos.size()==0){
            return null;
        }else {
            //限制查询三条数据，避免越界访问
            for(int i = 0;i<articleInfos.size() && i<3;i++){
                Long articleId = articleInfos.get(i).getId();
                String title = articleInfos.get(i).getTitle();
                title = formatFile.formatArticleTitle(title);
                Date create_by = articleInfos.get(i).getCreate_by();
                String newDate = formatFile.formatDate(create_by);

                String pictureUrl = articlePictureMapper.selectByArticleId(articleId).getPicture_url();

                ArticleSimpleDto articleSimpleDto = new ArticleSimpleDto();
                articleSimpleDto.setId(articleId);
                articleSimpleDto.setTitle(title);
                articleSimpleDto.setCreate_by(newDate);
                articleSimpleDto.setPictureUrl(pictureUrl);

                articleSimpleDtos.add(articleSimpleDto);
            }
        }
        return articleSimpleDtos;
    }
}
