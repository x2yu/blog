package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.entity.CategoryInfoExample;
import java.util.List;

public interface CategoryInfoMapper {
    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);
}