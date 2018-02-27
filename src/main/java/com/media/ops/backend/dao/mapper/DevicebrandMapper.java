package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Devicebrand;

public interface DevicebrandMapper {
    int deleteByPrimaryKey(String brand);

    int insert(Devicebrand record);

    int insertSelective(Devicebrand record);
}