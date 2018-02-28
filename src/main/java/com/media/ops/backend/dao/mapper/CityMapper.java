package com.media.ops.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.City;


public interface CityMapper {

    /**
     * 根据id获取城市
     * @param id
     * @return
     */
    City selectByPrimaryKey(Integer id);

    /**
     * 根据省份id,获取该省所有城市
     * @return
     */
    List<City> selectCitiesByProvinceId(@Param("provinceId") String provinceId);
}