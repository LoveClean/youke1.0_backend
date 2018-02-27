package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Syspara;

public interface SysparaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Syspara record);

    int insertSelective(Syspara record);

    Syspara selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Syspara record);

    int updateByPrimaryKey(Syspara record);
}