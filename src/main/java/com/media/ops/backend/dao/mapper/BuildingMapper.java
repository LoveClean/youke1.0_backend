package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Building;

public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
    
    int checkExist(@Param("areaid")String areaid, @Param("address")String address, @Param("name")String name);
    
    List<Building> selectList();
    
    List<Building> selectListByAreaId(String areaId);
    
    List<Building> selectListByAreaIdBuildingKey(@Param("areaId")String areaId, @Param("buildingName")String buildingName);
}