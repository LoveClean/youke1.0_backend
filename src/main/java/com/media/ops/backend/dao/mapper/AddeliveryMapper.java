package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Addelivery;

public interface AddeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Addelivery record);

    int insertSelective(Addelivery record);

    Addelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Addelivery record);

    int updateByPrimaryKey(Addelivery record);
}