package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Play;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    
    List<Play> selectByPlayerBeginEndTime(@Param("playerId")Integer playerId, @Param("begintime")String begintime, @Param("endtime")String endtime);
    
    List<Play> selectByPlayIdPlayerBeginEndTime(@Param("playId")Integer playId,@Param("playerId")Integer playerId, @Param("begintime")String begintime, @Param("endtime")String endtime);
    
    //通过playerId与status查询直播
    List<Play> selectByPlayIdAndStatus(@Param("playerId")Integer playerId,@Param("status")Integer status);
    //查询所有直播
    List<Play> selectList();
    
    //查询所有未结束的直播
    List<Play> selectUnfinishedPlay();
    
    //通过playerId查询直播
    List<Play> selectByPlayerId(@Param("playerId")Integer playerId);
    
    List<Play> selectByKeys(@Param("playerId")Integer playerId,@Param("status")Integer status,@Param("playTitle")String playTitle,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    //查询已直播结束的直播
    List<Play> selectByStatusAndRemarks();
    
    int updateRemarksById(@Param("id")Integer id);


    @Select("SELECT COUNT(*) FROM tbplay WHERE del_flag=0")
    int sumPlay();
}