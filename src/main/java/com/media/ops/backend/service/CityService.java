package com.media.ops.backend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AreaVo;
import com.media.ops.backend.vo.CityVo;
import com.media.ops.backend.vo.ProvinceVo;

public interface CityService {
	/**
	 * 根据记录id查询城市
	 * @param id
	 * @return
	 */
	ResponseEntity<CityVo> selectCityById(Integer id);
	/**
	 * 根据城市id查询城市
	 * @param cityId
	 * @return
	 */
	ResponseEntity<CityVo> selectByCityId(String cityId);
    /**
     * 根据省份ID获取该省城市列表
     * @param provinceId
     * @return
     */
	ResponseEntity<List<CityVo>> selectCitiesByProvinceId(String provinceId);
	
	/**
	 * 根据areaId获取地区
	 * @param areaId
	 * @return
	 */
	ResponseEntity<AreaVo> selectByAreaId(String areaId);
	
	/**
	 * 根据provinceId获取省份
	 * @param provinceId
	 * @return
	 */
	ResponseEntity<ProvinceVo> selectByProvinceId(String provinceId);
	
	/**
	 * 根据城市Id获取该市所有县区
	 * @param cityId
	 * @return
	 */
	ResponseEntity<List<AreaVo>> selectAreasByCityId(String cityId);
	
	ResponseEntity<List<ProvinceVo>> selectProvinces();
}
