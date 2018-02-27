package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Play;

public interface PlayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Play record);

    int insertSelective(Play record);

    Play selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);
}