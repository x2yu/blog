package cn.x2yu.blog.service;

import cn.x2yu.blog.dto.CategoryDto;
import cn.x2yu.blog.dto.CategorySimpleDto;
import cn.x2yu.blog.entity.CategoryInfo;

import java.util.List;

public interface CategoryService {
    void addCategory();
    void deleteCategory();
    void updateCategory();

    CategoryDto getCategory();

    List<CategorySimpleDto>listSimpleCategory();

    //查询分类组装数据集
    List<CategoryDto>listAllCategory();
}
