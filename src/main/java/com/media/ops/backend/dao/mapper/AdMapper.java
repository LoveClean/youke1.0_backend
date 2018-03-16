package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Ad;
import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.vo.AdMaterialListVo;
import com.media.ops.backend.vo.AdMaterialVo;
import com.media.ops.backend.vo.AdVo;
import com.media.ops.backend.vo.AddeliveryVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    ////////////////////以上是自动生成的////////////////////////////

    //根据设备MAC查询当前投放广告，add by linfs at 2018.3.1
    List<AdMaterialListVo> selectAdVoByMac(String mac);
    //根据广告ID查询素材
    List<AdMaterialVo> selectAdMaterialByAdid(int adid);
    ///////////////////////////////////////////////////
    
	List<Ad> selectList();
    
}