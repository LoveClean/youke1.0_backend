package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Materialgroup;

public interface MaterialgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Materialgroup record);

    int insertSelective(Materialgroup record);

    Materialgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Materialgroup record);

    int updateByPrimaryKey(Materialgroup record);
    
    List<Materialgroup> selectGroupChildrenByParentId(Integer parentId);
    
    List<Materialgroup> selectGroupsByName(@Param("groupName")String groupName);
    
    int checkExistName(@Param("groupName")String groupName);
    
    int checkExistNameNotSelf(@Param("groupName")String groupName, @Param("groupId")Integer groupId);

}