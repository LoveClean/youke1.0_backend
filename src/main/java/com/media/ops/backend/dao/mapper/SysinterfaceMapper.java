package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Sysinterface;
import com.media.ops.backend.dao.entity.Syspara;

public interface SysinterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    Sysinterface selectByPrimaryKey(Integer id);
    
    Sysinterface selectByValue(@Param("interfaceUrl")String interfaceUrl);

    List<Sysinterface>  selectAll();
    
    
}