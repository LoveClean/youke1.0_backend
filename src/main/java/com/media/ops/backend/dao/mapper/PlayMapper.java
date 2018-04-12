package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlayerVo;

public interface PlayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Play record);

    int insertSelective(Play record);

    Play selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);
    
    int updateStreamAddressById(@Param("streamaddress")String streamaddress, @Param("id")Integer id);

    //查询直播开始时间在时段区间中的直播，add by linfs at 2018.3.2
    List<Play> selectByTime(@Param("begintime")String begintime, @Param("endtime")String endtime);
    
    List<Play> selectByBeginEndTime(@Param("begintime")String begintime, @Param("endtime")String endtime);
    
    //通过playerId与status查询直播
    List<Play> selectByPlayIdAndStatus(@Param("playerId")Integer playerId,@Param("status")Integer status);
    //查询所有直播
    List<Play> selectList();
    //通过playerId查询直播
    List<Play> selectByPlayerId(@Param("playerId")Integer playerId);


}