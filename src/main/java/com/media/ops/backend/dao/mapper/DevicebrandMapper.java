package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Devicebrand;

public interface DevicebrandMapper {
    int deleteByPrimaryKey(String brand);

    int insert(Devicebrand record);

    int insertSelective(Devicebrand record);
    
    List<Devicebrand>  selectAll();
    
    int checkExist(@Param("deviceBrand")String deviceBrand);
}