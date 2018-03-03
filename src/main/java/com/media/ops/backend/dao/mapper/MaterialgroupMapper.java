package com.media.ops.backend.dao.mapper;

import java.util.List;

import com.media.ops.backend.dao.entity.Materialgroup;

public interface MaterialgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Materialgroup record);

    int insertSelective(Materialgroup record);

    Materialgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Materialgroup record);

    int updateByPrimaryKey(Materialgroup record);
    
    List<Materialgroup> selectGroupChildrenByParentId(Integer parentId);
}