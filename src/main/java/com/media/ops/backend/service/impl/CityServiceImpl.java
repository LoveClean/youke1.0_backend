package com.media.ops.backend.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.dao.mapper.CityMapper;
import com.media.ops.backend.service.CityService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.util.ResponseEntityUtil;
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;

	@Override
	public ResponseEntity<City> selectByPrimaryKey(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		City city= cityMapper.selectByPrimaryKey(id);
		if(city==null) {
			return ResponseEntityUtil.fail("找不到该城市");
		}else {
			return ResponseEntityUtil.success(city);
		}
	}

	@Override
	public ResponseEntity<List<City>> selectCitiesByProvinceId(String provinceId) {
		if(provinceId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		List<City> cityList= cityMapper.selectCitiesByProvinceId(provinceId);
		if(CollectionUtils.isNotEmpty(cityList)) {
			return ResponseEntityUtil.success(cityList);
		}else {
			return ResponseEntityUtil.fail("没找到相关城市");
		}
	}
	
}
