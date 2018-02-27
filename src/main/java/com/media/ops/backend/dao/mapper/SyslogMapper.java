package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Syslog;

public interface SyslogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    Syslog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKeyWithBLOBs(Syslog record);

    int updateByPrimaryKey(Syslog record);
}