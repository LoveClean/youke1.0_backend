package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.entity.Playdelivery;
import com.media.ops.backend.vo.VmPlayDeliveryVo;


public interface PlaydeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Playdelivery record);

    int insertSelective(Playdelivery record);

    Playdelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Playdelivery record);

    int updateByPrimaryKey(Playdelivery record);
    
    List<Playdelivery> selectList();
    
    List<VmPlayDeliveryVo> selectByGroupBeginEndTime(@Param("deliveryType")Integer deliveryType, @Param("areaId")String areaId, @Param("groupId")Integer groupId, @Param("begintime")String begintime, @Param("endtime")String endtime);

    List<Playdelivery> selectByKeys(@Param("areaId") String areaId,@Param("buildingId") Integer buildingId,@Param("groupId") Integer groupId);

}