package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Adgroup;

public interface AdgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adgroup record);

    int insertSelective(Adgroup record);

    Adgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adgroup record);

    int updateByPrimaryKey(Adgroup record);

    List<Adgroup> selectGroupChildrenByParentId(Integer parentId);
    
    List<Adgroup> selectGroupsByName(@Param("groupName")String groupName);
}