package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Devicespec;

public interface DevicespecMapper {
    int deleteByPrimaryKey(String spec);

    int insert(Devicespec record);

    int insertSelective(Devicespec record);
    
    List<Devicespec>  selectAll();
    
    int checkExist(@Param("deviceSpec")String deviceSpec);
}