package com.media.ops.backend.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Area;

public interface AreaMapper {


    Area selectByPrimaryKey(Integer id);
    
    Area selectByAreaId(@Param("areaId")String areaId);


}