package cn.x2yu.blog.service.impl;

import cn.x2yu.blog.dao.CategoryInfoMapper;
import cn.x2yu.blog.dao.CategoryPictureMapper;
import cn.x2yu.blog.dto.CategoryDto;
import cn.x2yu.blog.dto.CategorySimpleDto;
import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.entity.CategoryInfoExample;
import cn.x2yu.blog.entity.CategoryPicture;
import cn.x2yu.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    CategoryInfoMapper categoryInfoMapper;
    @Autowired
    CategoryPictureMapper categoryPictureMapper;

    @Override
    public void addCategoryInfo(CategoryInfo categoryInfo) {

        categoryInfoMapper.insertSelective(categoryInfo);

        //封装一个CategoryPicture对象，存入图片信息
        CategoryPicture categoryPicture = new CategoryPicture();
        categoryPicture.setCategory_id(categoryInfo.getId());
        String url = ("images/featured-post/post-"+categoryInfo.getId()+".jpg");
        categoryPicture.setPicture_url(url);
        categoryPictureMapper.insertSelective(categoryPicture);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryInfoMapper.deleteByPrimaryKey(categoryId);
        categoryPictureMapper.deleteByCategoryId(categoryId);
    }

    @Override
    public void updateCategory(CategoryInfo categoryInfo) {
        Long categoryId = categoryInfo.getId();

        //储存在数据库中分类图片路径
        CategoryPicture categoryPicture = new CategoryPicture();
        String pictureUrl = ("images/featured-post/post-"+categoryId+".jpg");
        categoryPicture.setPicture_url(pictureUrl);
        categoryPicture.setCategory_id(categoryId);
        //数据库主键总是和

        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        categoryPictureMapper.updateByCategoryIdSelective(categoryPicture);
    }

    @Override
    public CategoryDto getCategory() {
        return null;
    }

    @Override
    public CategoryInfo getCategoryInfoByName(String categoryName) {
        CategoryInfoExample categoryInfoExample = new CategoryInfoExample();
        categoryInfoExample.createCriteria().andNameEqualTo(categoryName);

        //用example查询出来的是集合，实际只有一个对象在里面
        CategoryInfo categoryInfo = categoryInfoMapper.selectByExample(categoryInfoExample).get(0);
        return categoryInfo ;
    }

    @Override
    public List<CategorySimpleDto> listSimpleCategory() {

        List<CategorySimpleDto>  categorySimpleDtos = new ArrayList<>();
        CategoryInfoExample categoryInfoExample = new CategoryInfoExample();
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByExample(categoryInfoExample);
        for(int i=0;i<categoryInfos.size();i++){
            CategorySimpleDto categorySimpleDto = new CategorySimpleDto();

            Long id = categoryInfos.get(i).getId();
            String name = categoryInfos.get(i).getName();

            categorySimpleDto.setId(id);
            categorySimpleDto.setName(name);

            categorySimpleDtos.add(categorySimpleDto);
        }

        return categorySimpleDtos;
    }

    @Override
    public List<CategoryDto> listAllCategory() {

        List<CategoryDto> categoryDtos = new ArrayList<>();
        CategoryInfoExample categoryInfoExample = new CategoryInfoExample();
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByExample(categoryInfoExample);

        //拼装分类数据
        for(int i=0;i<categoryInfos.size();i++){
            CategoryDto categoryDto = new CategoryDto();

            Long categoryId = categoryInfos.get(i).getId();
            String categoryName = categoryInfos.get(i).getName();
            Byte categoryNumber = categoryInfos.get(i).getNumber();
            String categorySubtitle = categoryInfos.get(i).getSubtitle();
            String pictureUrl = categoryPictureMapper.selectByPrimaryKey(categoryId).getPicture_url();

            categoryDto.setId(categoryId);
            categoryDto.setName(categoryName);
            categoryDto.setNumber(categoryNumber);
            categoryDto.setSubtitle(categorySubtitle);
            categoryDto.setPictureUrl(pictureUrl);

            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }
}
