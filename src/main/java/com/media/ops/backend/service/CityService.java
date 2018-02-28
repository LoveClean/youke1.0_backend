package com.media.ops.backend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.util.ResponseEntity;

public interface CityService {
	ResponseEntity<City> selectByPrimaryKey(Integer id);
	ResponseEntity<List<City>> selectCitiesByProvinceId(@Param("provinceId") String provinceId);
}
