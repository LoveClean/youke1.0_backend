package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Devicegroup;

public interface DevicegroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicegroup record);

    int insertSelective(Devicegroup record);

    Devicegroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicegroup record);

    int updateByPrimaryKey(Devicegroup record);
}