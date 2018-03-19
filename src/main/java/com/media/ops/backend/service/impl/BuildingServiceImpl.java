package com.media.ops.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.BuildingAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorUptRequestBean;
import com.media.ops.backend.controller.request.BuildingUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.dao.entity.Building;
import com.media.ops.backend.dao.entity.Buildingfloor;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.Floordevice;
import com.media.ops.backend.dao.entity.Province;
import com.media.ops.backend.dao.mapper.AreaMapper;
import com.media.ops.backend.dao.mapper.BuildingMapper;
import com.media.ops.backend.dao.mapper.BuildingfloorMapper;
import com.media.ops.backend.dao.mapper.CityMapper;
import com.media.ops.backend.dao.mapper.DeviceMapper;
import com.media.ops.backend.dao.mapper.FloordeviceMapper;
import com.media.ops.backend.dao.mapper.ProvinceMapper;
import com.media.ops.backend.service.BuildingService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AreaVo;
import com.media.ops.backend.vo.BuildingFloorListVo;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;
import com.media.ops.backend.vo.DeviceVo;
import com.media.ops.backend.vo.FloorDeviceVo;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private BuildingfloorMapper buildingfloorMapper;
	
	
	@Autowired
	private FloordeviceMapper floordeviceMapper;
	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean) {
		Building building = new Building();
		building.setName(bean.getName());
		building.setAddress(bean.getAddress());
		building.setAreaid(bean.getAreaid());
		building.setCreateBy(createby);
		building.setUpdateBy(createby);

		int resultCount = buildingMapper.insertSelective(building);
		return ResponseEntityUtil.addMessage(resultCount);
	}

	@Override
	public ResponseEntity updateBuilding(String updateby, BuildingUptRequestBean bean) {
		Building uptBuilding = new Building();
		uptBuilding.setId(bean.getId());
		uptBuilding.setName(bean.getName());
		uptBuilding.setAreaid(bean.getAreaid());
		uptBuilding.setAddress(bean.getAddress());
		uptBuilding.setUpdateBy(updateby);

		int resultCount = buildingMapper.updateByPrimaryKeySelective(uptBuilding);

		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId) {
		Building delBuilding = new Building();
		delBuilding.setId(buildingId);
		delBuilding.setUpdateBy(updateby);

		int resultCount = buildingMapper.updateByPrimaryKeySelective(delBuilding);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public PageResponseBean<BuildingVo> selectList(PageRequestBean bean) {
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Building> buildings = buildingMapper.selectList();

		List<BuildingVo> buildingVos = Lists.newArrayList();
		for (Building building : buildings) {
			List<Buildingfloor> buildingfloorList= buildingfloorMapper.selectListByBuildingId(building.getId());
			BuildingVo buildingVo= assembleBuildingVo(building, buildingfloorList);
			buildingVos.add(buildingVo);
		}
		PageInfo pageInfo=new PageInfo(buildings);
		pageInfo.setList(buildingVos);

		return new PageResponseBean<BuildingVo>(pageInfo);
	}

	@Override
	public ResponseEntity<BuildingVo> selectBuildingById(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Building building= buildingMapper.selectByPrimaryKey(id);
		if(building!=null) {
			List<Buildingfloor> buildingfloorList= buildingfloorMapper.selectListByBuildingId(building.getId() );
			BuildingVo buildingVo=assembleBuildingVo(building, buildingfloorList);
			return ResponseEntityUtil.success(buildingVo);
		}
		else {
			return ResponseEntityUtil.fail("找不到该楼宇信息");
		}
	}

	// 封装楼宇模型
	private BuildingVo assembleBuildingVo(Building building, List<Buildingfloor> buildingfloorList) {
		BuildingVo buildingVo = new BuildingVo();
		buildingVo.setId(building.getId());
		buildingVo.setName(building.getName());
		buildingVo.setAreaid(building.getAreaid());
		Area area = areaMapper.selectByAreaId(building.getAreaid());
		if (area != null) {
			buildingVo.setAreaVo(assembleAreaVo(area));
		}
		buildingVo.setAddress(building.getAddress());

		List<BuildingFloorListVo> buildingFloorListVos = Lists.newArrayList();

		for (Buildingfloor buildingfloor : buildingfloorList) {
			BuildingFloorListVo buildingFloorListVo = assembleBuildingFloorListVo(buildingfloor);
			buildingFloorListVos.add(buildingFloorListVo);
		}
		buildingVo.setBuildingFloorListVos(buildingFloorListVos);

		return buildingVo;
	}

	// 封装楼层模型
	private BuildingFloorListVo assembleBuildingFloorListVo(Buildingfloor buildingfloor) {
		BuildingFloorListVo buildingFloorListVo = new BuildingFloorListVo();
		buildingFloorListVo.setBuildingid(buildingfloor.getBuildingid());
		buildingFloorListVo.setFloorno(buildingfloor.getFloorno());
		buildingFloorListVo.setPath(buildingfloor.getPath());

		return buildingFloorListVo;
	}

	
	
	
	
	
	
	// 封装楼层设备模型
	private FloorDeviceVo assembleFloorDeviceVo(Floordevice floordevice) {
		FloorDeviceVo floorDeviceVo = new FloorDeviceVo();
		floorDeviceVo.setFloorId(floordevice.getFloorid());

		Device device = deviceMapper.selectByPrimaryKey(floordevice.getDeviceid());
		if (device != null) {
			floorDeviceVo.setDeviceVo(assembleDeviceVo(device));
		}
		floorDeviceVo.setX(floordevice.getX());
		floorDeviceVo.setY(floordevice.getY());

		return floorDeviceVo;
	}

	// 封装区域模型
	private AreaVo assembleAreaVo(Area area) {
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

	// 封装设备模型
	private DeviceVo assembleDeviceVo(Device device) {
		DeviceVo deviceVo = new DeviceVo();
		deviceVo.setCode(device.getCode());
		deviceVo.setMac(device.getMac());

		deviceVo.setType(device.getType());

		deviceVo.setGroupid(device.getGroupid());
		deviceVo.setBrand(device.getBrand());
		deviceVo.setSpec(device.getSpec());
		deviceVo.setAreaid(device.getAreaid());
		deviceVo.setBuildingid(device.getBuildingid());
		deviceVo.setAddress(device.getAddress());

		return deviceVo;
	}

	@Override
	public ResponseEntity createBuildingFloor(String createby, BuildingFloorAddRequestBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity updateBuilding(String updateby, BuildingFloorUptRequestBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> delBuildingFloor(String updateby, Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> delFloorByBuildingId(String updateby, Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<BuildingFloorVo>> selectFloorsByBuildingId(Integer buildingId) {
		// TODO Auto-generated method stub
		return null;
	}
}
