package cn.x2yu.blog.service;

import cn.x2yu.blog.dto.CategoryDto;
import cn.x2yu.blog.dto.CategorySimpleDto;
import cn.x2yu.blog.entity.CategoryInfo;

import java.util.List;

public interface CategoryService {
    void addCategoryInfo(CategoryInfo categoryInfo);

    void deleteCategory(Long categoryId);

    void updateCategory(CategoryInfo categoryInfo);

    CategoryInfo getCategoryInfoByName(String categoryName);

    CategoryDto getCategory();

    List<CategorySimpleDto>listSimpleCategory();

    //查询分类组装数据集
    List<CategoryDto>listAllCategory();
}
