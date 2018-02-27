package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Buildingfloor;

public interface BuildingfloorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Buildingfloor record);

    int insertSelective(Buildingfloor record);

    Buildingfloor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Buildingfloor record);

    int updateByPrimaryKey(Buildingfloor record);
}