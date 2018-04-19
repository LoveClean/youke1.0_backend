package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Buildingfloor;

public interface BuildingfloorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Buildingfloor record);

    int insertSelective(Buildingfloor record);

    Buildingfloor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Buildingfloor record);

    int updateByPrimaryKey(Buildingfloor record);
    
    int uptFloorByBuildingId(@Param("buildingId")Integer buildingId, @Param("delFlag")String delFlag, @Param("updateBy")String updateBy);
    
    List<Buildingfloor> selectListByBuildingId(@Param("buildingId")Integer buildingId);
    
    int checkExist(@Param("buildingid")Integer buildingid, @Param("floorno")Integer floorno);

}