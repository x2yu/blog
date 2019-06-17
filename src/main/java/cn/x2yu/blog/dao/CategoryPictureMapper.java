package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.CategoryPicture;
import cn.x2yu.blog.entity.CategoryPictureExample;
import java.util.List;

public interface CategoryPictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryPicture record);

    int insertSelective(CategoryPicture record);

    List<CategoryPicture> selectByExample(CategoryPictureExample example);

    CategoryPicture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryPicture record);

    int updateByPrimaryKey(CategoryPicture record);
}