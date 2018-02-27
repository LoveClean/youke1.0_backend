package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Materialgroup;

public interface MaterialgroupMapper {
    int deleteByPrimaryKey(String groupname);

    int insert(Materialgroup record);

    int insertSelective(Materialgroup record);
}