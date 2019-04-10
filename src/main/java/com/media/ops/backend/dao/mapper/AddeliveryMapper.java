package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Addelivery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AddeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Addelivery record);

    int insertSelective(Addelivery record);

    Addelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Addelivery record);

    int updateByPrimaryKey(Addelivery record);
    
    List<Addelivery> selectList();
    
    List<Addelivery> selectByKeys(@Param("areaId") String areaId,@Param("deliverType") Integer deliverType,@Param("groupId") Integer groupId);

    int checkExistByAdId(@Param("adId")Integer adId);
    
    int checkDeliveryByAdId(@Param("adId")Integer adId);
    
    int batchUpdateDelFlagByAdId(@Param("adid")Integer adid, @Param("updateBy")String updateBy);

    int batchInsert(@Param("addeliverys")  List<Addelivery> addeliveries);

    int batchDelete(@Param("list")List<Integer> list,@Param("updateBy") String updataBy);

    List<Addelivery> selectByKeys2(@Param("areaId") String areaId,@Param("deliverType") Integer deliverType,@Param("groupId") Integer groupId);

}