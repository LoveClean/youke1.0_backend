package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Adgroup;

public interface AdgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adgroup record);

    int insertSelective(Adgroup record);

    Adgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adgroup record);

    int updateByPrimaryKey(Adgroup record);
}