package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Addelivery;


public interface AddeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Addelivery record);

    int insertSelective(Addelivery record);

    Addelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Addelivery record);

    int updateByPrimaryKey(Addelivery record);
    
    List<Addelivery> selectList();
    
    List<Addelivery> selectByKeys(@Param("areaId") String areaId,@Param("deliverType") Integer deliverType,@Param("groupId") Integer groupId);
}