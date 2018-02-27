package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Admaterial;

public interface AdmaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admaterial record);

    int insertSelective(Admaterial record);

    Admaterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admaterial record);

    int updateByPrimaryKey(Admaterial record);
}