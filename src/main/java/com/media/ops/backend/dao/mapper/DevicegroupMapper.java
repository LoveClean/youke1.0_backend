package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Devicegroup;

public interface DevicegroupMapper {
    int deleteByPrimaryKey(String groupname);

    int insert(Devicegroup record);

    int insertSelective(Devicegroup record);
}