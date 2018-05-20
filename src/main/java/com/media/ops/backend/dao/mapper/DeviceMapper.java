package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Device;

import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    
    List<Device> selectList();
    
    List<Device> selectByGroupId(Integer groupId);
    
    List<Device> selectByBuildingId(Integer buildingId);
    
    List<Device> selectByCodeAreaAddress(@Param("code") String code,@Param("areaId") String areaId,@Param("address") String address,@Param("buildingId") Integer buildingId,@Param("groupId") Integer groupId);

    int checkExistCode(@Param("deviceCode")String deviceCode);
    
    int checkExistCodeNotSelf(@Param("deviceId")Integer deviceId,@Param("deviceCode")String deviceCode);
}