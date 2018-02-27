package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Adgroup;

public interface AdgroupMapper {
    int deleteByPrimaryKey(String groupname);

    int insert(Adgroup record);

    int insertSelective(Adgroup record);
}