package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.vo.AdMaterialListVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PlayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Play record);

    int insertSelective(Play record);

    Play selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);
    ////////////////////以上是自动生成的////////////////////////////

    //查询直播开始时间在时段区间中的直播，add by linfs at 2018.3.2
    List<Play> selectByTime(@Param("begintime")String begintime, @Param("endtime")String endtime);

}