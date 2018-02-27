package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Oper;
import org.apache.ibatis.annotations.Param;

public interface OperMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("account") String account);

    int insert(Oper record);

    int insertSelective(Oper record);

    Oper selectByPrimaryKey(@Param("id") Integer id, @Param("account") String account);

    int updateByPrimaryKeySelective(Oper record);

    int updateByPrimaryKey(Oper record);
}