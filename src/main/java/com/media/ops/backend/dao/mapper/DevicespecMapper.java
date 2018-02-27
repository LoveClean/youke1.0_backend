package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Devicespec;

public interface DevicespecMapper {
    int deleteByPrimaryKey(String spec);

    int insert(Devicespec record);

    int insertSelective(Devicespec record);
}