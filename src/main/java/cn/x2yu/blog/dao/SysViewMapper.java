package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.SysView;
import cn.x2yu.blog.entity.SysViewExample;
import java.util.List;

public interface SysViewMapper {
    int insert(SysView record);

    int insertSelective(SysView record);

    List<SysView> selectByExample(SysViewExample example);
}