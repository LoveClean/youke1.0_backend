package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.controller.request.AdMaterialUptRequestBean;
import com.media.ops.backend.dao.entity.Admaterial;


public interface AdmaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admaterial record);

    int insertSelective(Admaterial record);

    Admaterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admaterial record);

    int updateByPrimaryKey(Admaterial record);
    
    List<Admaterial> selectByAdId(Integer adid);
    
    int batchUpdateDelFlagByAdId(@Param("adid")Integer adid, @Param("updateBy")String updateBy);
    
        
}