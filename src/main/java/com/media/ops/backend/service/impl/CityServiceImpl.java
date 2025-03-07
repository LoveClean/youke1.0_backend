package com.media.ops.backend.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.dao.entity.Province;
import com.media.ops.backend.dao.mapper.AreaMapper;
import com.media.ops.backend.dao.mapper.CityMapper;
import com.media.ops.backend.dao.mapper.ProvinceMapper;
import com.media.ops.backend.service.CityService;
import com.media.ops.backend.util.ResponseEntity;
import com.beust.jcommander.internal.Lists;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.util.StringUtil;
import com.media.ops.backend.vo.AreaVo;
import com.media.ops.backend.vo.CityVo;
import com.media.ops.backend.vo.ProvinceVo;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private ProvinceMapper provinceMapper;

	@Override
	public ResponseEntity<CityVo> selectCityById(Integer id) {
		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		City city = cityMapper.selectByPrimaryKey(id);
		if (city == null) {
			return ResponseEntityUtil.fail("找不到该城市");
		} else {
			return ResponseEntityUtil.success(assembleCityVo(city));
		}
	}

	@Override
	public ResponseEntity<CityVo> selectByCityId(String cityId) {
		if (StringUtil.isBlank(cityId)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		City city = cityMapper.selectByCityId(cityId);
		if (city == null) {
			return ResponseEntityUtil.fail("找不到该城市");
		} else {
			return ResponseEntityUtil.success(assembleCityVo(city));
		}
	}

	@Override
	public ResponseEntity<List<CityVo>> selectCitiesByProvinceId(String provinceId) {
		if (provinceId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		List<City> cityList = cityMapper.selectCitiesByProvinceId(provinceId);
		
		if (CollectionUtils.isNotEmpty(cityList)) {
			List<CityVo> cityVoList= Lists.newArrayList();
			for (City city : cityList) {
				CityVo cityVo= assembleCityVo(city);
				cityVoList.add(cityVo);
			}
			return ResponseEntityUtil.success(cityVoList);
		} else {
			return ResponseEntityUtil.fail("没找到相关城市");
		}
	}

	@Override
	public ResponseEntity<AreaVo> selectByAreaId(String areaId) {
		if (StringUtil.isBlank(areaId)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Area area = areaMapper.selectByAreaId(areaId);
		if (area == null) {
			return ResponseEntityUtil.fail("找不到该区域");
		} else {
			return ResponseEntityUtil.success( assembleAreaVo(area) );
		}
	}

	@Override
	public ResponseEntity<List<AreaVo>> selectAreasByCityId(String cityId) {
		if (cityId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		List<Area> areaList = areaMapper.selectAreasByCityId(cityId);
		
		if (CollectionUtils.isNotEmpty(areaList)) {
			List<AreaVo> areaVoList=Lists.newArrayList();
			for (Area area : areaList) {
				AreaVo areaVo= assembleAreaVo(area);
				areaVoList.add(areaVo);
			}
			return ResponseEntityUtil.success(areaVoList);
		} else {
			return ResponseEntityUtil.fail("没找到相关县区");
		}
	}
	
	@Override
	public ResponseEntity<ProvinceVo> selectByProvinceId(String provinceId) {
		if (StringUtil.isBlank(provinceId)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Province province= provinceMapper.selectByProvinceId(provinceId);
		
		if (province == null) {
			return ResponseEntityUtil.fail("找不到该省份");
		} else {
			return ResponseEntityUtil.success( assembleProvinceVo(province) );
		}
	}
	
	@Override
	public ResponseEntity<List<ProvinceVo>> selectProvinces() {
         List<Province> provinces=provinceMapper.selectAllProvinces();
         List<ProvinceVo> provinceVoList=Lists.newArrayList();
         for (Province province : provinces) {
			ProvinceVo provinceVo= assembleProvinceVo(province);
			provinceVoList.add(provinceVo);
		}
         if(CollectionUtils.isNotEmpty(provinceVoList)) {
        	 return ResponseEntityUtil.success(provinceVoList);
         }else {
        	 return ResponseEntityUtil.fail("没找到省份");
         }

	}
	
	private  AreaVo assembleAreaVo(Area area) {
		AreaVo areaVo = new AreaVo();
		areaVo.setAreaId(area.getAreaid());
		areaVo.setArea(area.getArea());

		String provinceName = "", cityName = "";
		City city = cityMapper.selectByCityId(area.getFather());
		if (city != null) {
			cityName = city.getCity();
			Province province = provinceMapper.selectByProvinceId(city.getFather());
			provinceName = province != null ? province.getProvince() : "";
		}
		areaVo.setFullName(provinceName + cityName + area.getArea());
		return areaVo;
	}

	private CityVo assembleCityVo(City city) {
		CityVo cityVo = new CityVo();
		cityVo.setCityId(city.getCityid());
		cityVo.setCity(city.getCity());

		List<Area> areas = areaMapper.selectAreasByCityId(city.getCityid());

		List<AreaVo> areaVos = Lists.newArrayList();
		for (Area area : areas) {
             AreaVo areaVo= assembleAreaVo(area);
             areaVos.add(areaVo);
		}
	
		cityVo.setAreaVoList(areaVos);
		return cityVo;
	}

	private ProvinceVo assembleProvinceVo(Province province) {
		ProvinceVo provinceVo=new ProvinceVo();
		provinceVo.setProvId(province.getProvinceid());
		provinceVo.setProvince(province.getProvince());
		
//		List<City> cities=cityMapper.selectCitiesByProvinceId(province.getProvinceid());
//	    List<CityVo> cityVos=Lists.newArrayList();
//		for (City city : cities) {
//			CityVo cityVo=assembleCityVo(city);
//			cityVos.add(cityVo);
//		}
//		
//		provinceVo.setCityVoList(cityVos);
		
		return provinceVo;
	}




}
