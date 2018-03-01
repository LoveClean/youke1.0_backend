package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Device;

import java.util.Map;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    ////////////////////以上是自动生成的////////////////////////////

    //根据设备编号查询设备，add by linfs at 2018.3.1
    Device selectByCode(String code);

}