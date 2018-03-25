package com.media.ops.backend.dao.mapper;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicetype;

public interface DevicetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicetype record);

    int insertSelective(Devicetype record);

    Devicetype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicetype record);

    int updateByPrimaryKey(Devicetype record);
    
    List<Devicetype>  selectAll();
}