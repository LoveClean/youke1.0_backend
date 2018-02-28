package com.media.ops.backend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.contants.Const;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.service.CityService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="城市操作接口描述",produces = "application/json")
@RestController
@RequestMapping("/city/")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@ApiOperation(value = "查找城市接口",notes = "根据id查找城市")
	@PostMapping(value="get_city.do")	
	public ResponseEntity<City> getCity(Integer id){
		return cityService.selectByPrimaryKey(id);
	}
	
	@ApiOperation(value = "获取所有城市接口",notes = "根据省份获取所有城市")
	@PostMapping(value="get_list.do")	
	public ResponseEntity<List<City>> getList(String provinceId){
		return cityService.selectCitiesByProvinceId(provinceId);
	}	
	
}
