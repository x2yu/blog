package cn.x2yu.blog.dao;

import cn.x2yu.blog.entity.SysLog;
import cn.x2yu.blog.entity.SysLogExample;

import java.util.List;
public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}