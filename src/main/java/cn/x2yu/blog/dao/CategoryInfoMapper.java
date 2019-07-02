package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.CategoryInfo;
import cn.x2yu.blog.entity.CategoryInfoExample;
import java.util.List;

public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    CategoryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryInfo record);


    int updateByPrimaryKey(CategoryInfo record);
}