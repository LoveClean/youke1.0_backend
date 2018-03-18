package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Area;

public interface AreaMapper {


    Area selectByPrimaryKey(Integer id);
    
    Area selectByAreaId(@Param("areaId")String areaId);

    List<Area> selectAreasByCityId(@Param("cityId") String cityId);

}