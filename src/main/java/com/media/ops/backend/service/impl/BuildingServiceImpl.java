package com.media.ops.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.*;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.*;
import com.media.ops.backend.dao.mapper.*;
import com.media.ops.backend.service.BuildingService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private  DevicetypeMapper devicetypeMapper;

	@Override
	public ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean) {
		
		if(buildingMapper.checkExist(bean.getAreaid(), bean.getAddress(), bean.getName())>0) {
			return ResponseEntityUtil.fail("同一地址下已有同名的楼宇");
		}

		Building building = new Building();
		building.setName(bean.getName());
		building.setAddress(bean.getAddress());
		building.setAreaid(bean.getAreaid());
		building.setCreateBy(createby);
		building.setUpdateBy(createby);

		int resultCount = buildingMapper.insertSelective(building);
		if(resultCount>0) {
			//Map<String, Object> result= Maps.newHashMap();
			//result.put("newData", building);
			return ResponseEntityUtil.success(building);
		}
		
		return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
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

		if(resultCount>0) {
			return ResponseEntityUtil.success(buildingMapper.selectByPrimaryKey(uptBuilding.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId) {
		Building delBuilding = new Building();
		delBuilding.setId(buildingId);
		delBuilding.setUpdateBy(updateby);
		delBuilding.setDelFlag(Const.DelFlagEnum.DELETED);
		
		int resultCount = buildingMapper.updateByPrimaryKeySelective(delBuilding);
		if (resultCount > 0) {
			List<BuildingFloorVo> buildingFloorVos= this.selectFloorsByBuildingId(buildingId).getData();
			if(CollectionUtils.isNotEmpty(buildingFloorVos)) {
				return this.delFloorByBuildingId(updateby, buildingId);
			}
		}
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public PageResponseBean<BuildingVo> selectList(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Building> buildings = buildingMapper.selectList();

		List<BuildingVo> buildingVos = Lists.newArrayList();
		for (Building building : buildings) {
			List<Buildingfloor> buildingfloorList = buildingfloorMapper.selectListByBuildingId(building.getId());
			BuildingVo buildingVo = assembleBuildingVo(building, buildingfloorList);
			buildingVos.add(buildingVo);
		}
		PageInfo pageInfo = new PageInfo(buildings);
		pageInfo.setList(buildingVos);
		PageResponseBean<BuildingVo> list= new PageResponseBean<BuildingVo>(pageInfo);
		list.setCode(0);
		list.setHttpStatus(200);
		return list;
	}

	@Override
	public ResponseEntity<BuildingVo> selectBuildingById(Integer id) {
		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Building building = buildingMapper.selectByPrimaryKey(id);
		if (building != null) {
			List<Buildingfloor> buildingfloorList = buildingfloorMapper.selectListByBuildingId(building.getId());
			BuildingVo buildingVo = assembleBuildingVo(building, buildingfloorList);
			return ResponseEntityUtil.success(buildingVo);
		} else {
			return ResponseEntityUtil.fail("找不到该楼宇信息");
		}
	}

//	public ResponseEntity<List<AreaBuildingVo>> selectBuildingByAreaId(String areaId){
//		if(StringUtils.isBlank(areaId)) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
//		}
//		
//		List<Building> buildings = buildingMapper.selectListByAreaId(areaId);
//
//		List<AreaBuildingVo> buildingVos = Lists.newArrayList();
//		for (Building building : buildings) {
//			AreaBuildingVo areaBuildingVo=assembleAreaBuildingVo(building);
//			buildingVos.add(areaBuildingVo);
//		}
//		
//		if(CollectionUtils.isEmpty(buildingVos)) {
//			return ResponseEntityUtil.fail("找不到该区域的楼宇信息");
//		}
//		
//		return ResponseEntityUtil.success(buildingVos);
//	}

	public ResponseEntity<List<BuildingVo>> selectBuildingByAreaId(String areaId){
		if(StringUtils.isBlank(areaId)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		List<Building> buildings = buildingMapper.selectListByAreaId(areaId);

		List<BuildingVo> buildingVos = Lists.newArrayList();
		for (Building building : buildings) {
			List<Buildingfloor> buildingfloorList = buildingfloorMapper.selectListByBuildingId(building.getId());
			BuildingVo buildingVo = assembleBuildingVo(building, buildingfloorList);
			buildingVos.add(buildingVo);
		}
		
		if(CollectionUtils.isEmpty(buildingVos)) {
			return ResponseEntityUtil.fail("找不到该区域的楼宇信息");
		}
		
		return ResponseEntityUtil.success(buildingVos);
	}
	//楼宇搜索
	public PageResponseBean<BuildingVo> selectBuildingsbyKey(String provinceId2,String cityId2, String areaId2,
															 String buildingKey2, Integer pageNum2,Integer pageSize2){
		String buildingKey=buildingKey2;
		String provinceId= provinceId2;
		String cityId= cityId2;
		String areaId=areaId2;
		Integer pageNum=pageNum2;
		Integer pageSize=pageSize2;

//		if(StringUtils.isBlank(buildingKey)&& StringUtils.isBlank(areaId)&&StringUtils.isBlank(cityId)&&StringUtils.isBlank(provinceId)) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
//		}

		if(StringUtils.isNotBlank(buildingKey)) {
			buildingKey=new StringBuilder().append("%").append(buildingKey).append("%").toString();
		}

		if(StringUtils.isNotBlank(provinceId) && StringUtils.isBlank(cityId) && StringUtils.isBlank(areaId)) {
			areaId= new StringBuilder().append("%").append(provinceId.substring(0, 2)).append("%").toString();
		}

		if(StringUtils.isNotBlank(cityId) && StringUtils.isBlank(areaId)) {
			areaId= new StringBuilder().append("%").append(cityId.substring(0, 4)).append("%").toString();
		}

		PageHelper.startPage(pageNum, pageSize);
		List<Building> buildings = buildingMapper.selectListByAreaIdBuildingKey(StringUtils.isBlank(areaId)?null:areaId, StringUtils.isBlank(buildingKey)?null:buildingKey);

		List<BuildingVo> buildingVos = Lists.newArrayList();
		for (Building building : buildings) {
			List<Buildingfloor> buildingfloorList = buildingfloorMapper.selectListByBuildingId(building.getId());
			BuildingVo buildingVo = assembleBuildingVo(building, buildingfloorList);
			buildingVos.add(buildingVo);
		}
		PageInfo pageInfo = new PageInfo(buildings);
		pageInfo.setList(buildingVos);
		PageResponseBean<BuildingVo> list =new PageResponseBean<BuildingVo>(pageInfo);
		list.setCode(0);
		list.setHttpStatus(200);
		return list;


	}
	
	private AreaBuildingVo assembleAreaBuildingVo(Building building) {
		AreaBuildingVo areaBuildingVo=new AreaBuildingVo();
		areaBuildingVo.setId(building.getId());
		areaBuildingVo.setName(building.getName());
		areaBuildingVo.setAreaid(building.getAreaid());
		areaBuildingVo.setAddress(building.getAddress());
		
		return areaBuildingVo;
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
		buildingFloorListVo.setId(buildingfloor.getId());
		buildingFloorListVo.setBuildingid(buildingfloor.getBuildingid());
		buildingFloorListVo.setFloorno(buildingfloor.getFloorno());
		buildingFloorListVo.setPath(buildingfloor.getPath());

		return buildingFloorListVo;
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

	///////////////////////////////////////////////////////////////////////////

	/**
	 * 添加楼层
	 */
	@Override
	public ResponseEntity createBuildingFloor(String createby, BuildingFloorAddRequestBean bean) {
		
		if(buildingfloorMapper.checkExist(bean.getBuildingid(), bean.getFloorno())>0) {
			return ResponseEntityUtil.fail("已有同名的楼层");
		}
		
		Buildingfloor buildingfloor = new Buildingfloor();
		buildingfloor.setBuildingid(bean.getBuildingid());
		buildingfloor.setFloorno(bean.getFloorno());
		buildingfloor.setPath(bean.getPath());
		buildingfloor.setCreateBy(createby);
		buildingfloor.setUpdateBy(createby);

		int resultCount = buildingfloorMapper.insertSelective(buildingfloor);
		if(resultCount>0) {
			Map<String, Object> result= Maps.newHashMap();
			result.put("newData", buildingfloor);
			return ResponseEntityUtil.success(result);
		}
		
		return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
	}
	
	public ResponseEntity batchInsertFloor(String createby, List<BuildingFloorAddRequestBean> beans) {

		List<Buildingfloor> buildingfloors=Lists.newArrayList();
		for (BuildingFloorAddRequestBean bean : beans) {
			Buildingfloor buildingfloor=new Buildingfloor();
			buildingfloor.setBuildingid(bean.getBuildingid());
			buildingfloor.setFloorno(bean.getFloorno());
			buildingfloor.setPath(bean.getPath());
			buildingfloor.setCreateBy(createby);
			buildingfloor.setUpdateBy(createby);
			
			try {
				if(buildingfloorMapper.checkExist(bean.getBuildingid(), bean.getFloorno())<=0) {
					buildingfloorMapper.insert(buildingfloor);
					buildingfloors.add(buildingfloor);
				}
			} catch (Exception e) {
				return ResponseEntityUtil.fail("批量处理有异常");
			}
		}
		if(beans.size() == buildingfloors.size()) {
			Building building= buildingMapper.selectByPrimaryKey(buildingfloors.get(0).getBuildingid());
			BuildingVo buildingVo=assembleBuildingVo(building, buildingfloors);
			
			return ResponseEntityUtil.success(buildingVo);
		}else {
			return ResponseEntityUtil.fail("部分操作失败，请检查!");
		}
	}

	@Override
	public ResponseEntity updateBuildingFloor(String updateby, BuildingFloorUptRequestBean bean) {
		Buildingfloor buildingfloor = new Buildingfloor();
		buildingfloor.setId(bean.getId());
		buildingfloor.setBuildingid(bean.getBuildingid());
		buildingfloor.setFloorno(bean.getFloorno());
		buildingfloor.setPath(bean.getPath());
		buildingfloor.setUpdateBy(updateby);

		int resultCount = buildingfloorMapper.updateByPrimaryKeySelective(buildingfloor);
		if(resultCount>0) {
			return ResponseEntityUtil.success(buildingfloorMapper.selectByPrimaryKey(buildingfloor.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	/**
	 * 修改楼层信息
	 */
	@Override
	public ResponseEntity<String> delBuildingFloor(String updateby, Integer Id) {
		Buildingfloor delBuildingfloor = new Buildingfloor();
		delBuildingfloor.setId(Id);
		delBuildingfloor.setDelFlag(Const.DelFlagEnum.DELETED);
		delBuildingfloor.setUpdateBy(updateby);

		int resultCount = buildingfloorMapper.updateByPrimaryKeySelective(delBuildingfloor);
		return ResponseEntityUtil.delMessage(resultCount);

	}

	/**
	 * 根据楼宇id删除楼层
	 */
	@Override
	public ResponseEntity<String> delFloorByBuildingId(String updateby, Integer buildingId) {
		if (buildingId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		int resultCount = buildingfloorMapper.uptFloorByBuildingId(buildingId, Const.DelFlagEnum.DELETED,
				StringUtils.isBlank(updateby) ? null : updateby);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public PageResponseBean<BuildingFloorVo> selectFloorsByBuildingId(Integer buildingId,Integer pageNum,Integer pageSize) {
//		// TODO Auto-generated method stub
//		if (buildingId == null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
//		}
		PageHelper.startPage(pageNum,pageSize);
		List<Buildingfloor> buildingfloors = buildingfloorMapper.selectListByBuildingId(buildingId);
		List<BuildingFloorVo> buildingFloorVos = Lists.newArrayList();
		for (Buildingfloor buildingfloor : buildingfloors) {
			BuildingFloorVo buildingFloorVo = assembleBuildingFloorVo(buildingfloor);
			buildingFloorVos.add(buildingFloorVo);
		}

		PageInfo pageInfo=new PageInfo(buildingfloors);
		pageInfo.setList(buildingFloorVos);
		PageResponseBean<BuildingFloorVo> list = new PageResponseBean<BuildingFloorVo>(pageInfo);
		list.setHttpStatus(200);
		list.setCode(0);
		return list;
	}

	public ResponseEntity<List<BuildingFloorVo>> selectFloorsByBuildingId(Integer buildingId) {
		// TODO Auto-generated method stub
		if (buildingId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		List<Buildingfloor> buildingfloors = buildingfloorMapper.selectListByBuildingId(buildingId);
		List<BuildingFloorVo> buildingFloorVos = Lists.newArrayList();
		for (Buildingfloor buildingfloor : buildingfloors) {
			BuildingFloorVo buildingFloorVo = assembleBuildingFloorVo(buildingfloor);
			buildingFloorVos.add(buildingFloorVo);
		}

		return ResponseEntityUtil.success(buildingFloorVos);
	}

	public ResponseEntity<BuildingFloorVo> selectFloorById(Integer id) {
		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Buildingfloor buildingfloor = buildingfloorMapper.selectByPrimaryKey(id);
		if (buildingfloor == null) {
			return ResponseEntityUtil.fail("找不到相关楼层");
		}

		BuildingFloorVo buildingFloorVo = assembleBuildingFloorVo(buildingfloor);
		return ResponseEntityUtil.success(buildingFloorVo);

	}

	// 封装BuildingFloor模型
	private BuildingFloorVo assembleBuildingFloorVo(Buildingfloor buildingfloor) {
		BuildingFloorVo buildingFloorVo = new BuildingFloorVo();
		buildingFloorVo.setId(buildingfloor.getId());
		buildingFloorVo.setBuildingid(buildingfloor.getBuildingid());
		buildingFloorVo.setFloorno(buildingfloor.getFloorno());
		buildingFloorVo.setPath(buildingfloor.getPath());
		// FloorDeviceList

		List<Floordevice> floordevices = floordeviceMapper.selectByFloorno(buildingfloor.getId());
		List<FloorDeviceVo> floorDeviceVos = Lists.newArrayList();
		for (Floordevice floordevice : floordevices) {
			FloorDeviceVo floorDeviceVo = assembleFloorDeviceVo(floordevice);
			floorDeviceVos.add(floorDeviceVo);
		}
		buildingFloorVo.setFloorDeviceVoList(floorDeviceVos);
		return buildingFloorVo;
	}

	/////////////////////////// 楼层设备操作//////////////////////////////////////
	@Override
	public ResponseEntity addFloorDevice(String createby, FloorDeviceAddRequestBean bean) {
		Floordevice floordevice = new Floordevice();
		floordevice.setDeviceid(bean.getDeviceid());
		floordevice.setFloorid(bean.getFloorid());
		floordevice.setX(bean.getX());
		floordevice.setY(bean.getY());
		floordevice.setMemo(bean.getMemo());

		int resultCount = floordeviceMapper.insertSelective(floordevice);
		if(resultCount>0) {
			Map<String, Object> result= Maps.newHashMap();
			result.put("newData", floordevice);
			return ResponseEntityUtil.success(result);
		}
		
		return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
	}
	
	@Override
	public ResponseEntity batchFloorDevice(String createby, List<FloorDeviceAddRequestBean> beans) {


		List<FloorDeviceVo> floordevices= Lists.newArrayList();
		for (FloorDeviceAddRequestBean bean : beans) {
			Floordevice floordevice=new Floordevice();
			floordevice.setDeviceid(bean.getDeviceid());
			floordevice.setFloorid(bean.getFloorid());
			floordevice.setX(bean.getX());
			floordevice.setY(bean.getY());
			floordevice.setMemo(bean.getMemo());
			
			try {
				floordeviceMapper.insert(floordevice);
				floordevices.add(assembleFloorDeviceVo(floordevice));
			} catch (Exception e) {
				return ResponseEntityUtil.fail("批量处理有异常");
			}
		}		
		
		if(floordevices.size() == beans.size()) {
			return ResponseEntityUtil.success(floordevices);
		}else {
			return ResponseEntityUtil.fail("部分操作失败，请检查!");
		}

	}

	@Override
	public ResponseEntity updateFloorDevice(String updateby, FloorDeviceUptRequestBean bean) {
		Floordevice floordevice = new Floordevice();
		floordevice.setId(bean.getId());
		floordevice.setDeviceid(bean.getDeviceid());
		floordevice.setFloorid(bean.getFloorid());
		floordevice.setX(bean.getX());
		floordevice.setY(bean.getY());
		floordevice.setMemo(bean.getMemo());

		int resultCount = floordeviceMapper.updateByPrimaryKeySelective(floordevice);
		if(resultCount>0) {
			return ResponseEntityUtil.success(floordeviceMapper.selectByPrimaryKey(floordevice.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> delFloorDevice(String updateby, Integer Id) {
		if (Id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		int resultCount = floordeviceMapper.deleteByPrimaryKey(Id);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> delDevicesByFloorId(String updateby, Integer floorId) {
		if (floorId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		int resultCount = floordeviceMapper.delDevicesByFloorId(floorId);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<FloorDeviceVo>> selecDevicesByFloorId(Integer floorId) {
		if (floorId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		List<Floordevice> floordevices = floordeviceMapper.selectByFloorno(floorId);

		List<FloorDeviceVo> floorDeviceVos = Lists.newArrayList();
		for (Floordevice floordevice : floordevices) {
              FloorDeviceVo floorDeviceVo=assembleFloorDeviceVo(floordevice);
              floorDeviceVos.add(floorDeviceVo);
		}
		return ResponseEntityUtil.success(floorDeviceVos);
	}

	@Override
	public ResponseEntity<FloorDeviceVo> selectFloorDeviceById(Integer id) {

		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Floordevice floordevice= floordeviceMapper.selectByPrimaryKey(id);
		if (floordevice == null) {
			return ResponseEntityUtil.fail("找不到相关设备");
		}

		FloorDeviceVo floorDeviceVo= assembleFloorDeviceVo(floordevice);
		return ResponseEntityUtil.success(floorDeviceVo);
	}

	// 封装楼层设备模型
	private FloorDeviceVo assembleFloorDeviceVo(Floordevice floordevice) {
		FloorDeviceVo floorDeviceVo = new FloorDeviceVo();
		floorDeviceVo.setId(floordevice.getId());
		floorDeviceVo.setFloorId(floordevice.getFloorid());

//		Device device = deviceMapper.selectByPrimaryKey(floordevice.getDeviceid());
//		if (device != null) {
//			floorDeviceVo.setDeviceVo(assembleDeviceVo(device));
//		}
		Devicetype devicetype= devicetypeMapper.selectByPrimaryKey(floordevice.getDeviceid());
		if(devicetype !=null) {
			floorDeviceVo.setDeviceTypeId(devicetype.getId());
			floorDeviceVo.setDeviceTypeName(devicetype.getName());
		}
		
		floorDeviceVo.setX(floordevice.getX());
		floorDeviceVo.setY(floordevice.getY());

		return floorDeviceVo;
	}

	// 封装设备模型
	private DeviceVo assembleDeviceVo(Device device) {
		DeviceVo deviceVo = new DeviceVo();
		deviceVo.setId(device.getId());
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
	public ResponseEntity<String> delFloorDevices(String updateby, List<Integer> ids) {
		
		if(CollectionUtils.isEmpty(ids)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		int resultCount = floordeviceMapper.deleteByIds(ids);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public List<TestProvinceVo> selectList2(){
		List<Building> buildings = buildingMapper.selectList();

		Map<String,Object> areas = new HashMap<String, Object>();
		for(Building building:buildings){
			//封装楼宇
			TestBuildingVo testBuildingVo=new TestBuildingVo();
			testBuildingVo.setId(building.getAreaid()+building.getId());
			testBuildingVo.setLabel(building.getName());
			if(areas.get(building.getAreaid())==null){// 如果map中没有此区域，则进行区域新增
				//封装县区
				Area area = areaMapper.selectByAreaId(building.getAreaid());
				TestAreaVo testAreaVo =new TestAreaVo();
				testAreaVo.setId(building.getAreaid());
				testAreaVo.setLabel(area.getArea());
				//添加楼宇
				List<TestBuildingVo> testBuildingVos=new ArrayList<>();
				testBuildingVos.add(testBuildingVo);
				testAreaVo.setChildren(testBuildingVos);
				//放入map
				areas.put(building.getAreaid(), testAreaVo);
			}else{// 如果此区域已存在，则进行楼宇添加
				//拿出县区
				TestAreaVo testAreaVo = (TestAreaVo) areas.get(building.getAreaid());
				//添加楼宇
				List<TestBuildingVo> testBuildingVos= testAreaVo.getChildren();
				testBuildingVos.add(testBuildingVo);
				testAreaVo.setChildren(testBuildingVos);
				//放回县区（更新）
				areas.put(building.getAreaid(), testAreaVo);
			}
		}

		Map citys = new HashMap();
		for (Object v : areas.values()) {
			TestAreaVo testAreaVo =(TestAreaVo) v;
			String cityId= testAreaVo.getId().substring(0,4)+"00";
			if(citys.get(cityId)==null){
				//封装市区
				City city = cityMapper.selectByCityId(cityId);
				TestCityVo testCityVo = new TestCityVo();
				testCityVo.setId(cityId);
				testCityVo.setLabel(city.getCity());

				//添加县区
				List<TestAreaVo> testAreaVos =new ArrayList<>();
				testAreaVos.add(testAreaVo);
				testCityVo.setChildren(testAreaVos);
				//放入map
				citys.put(cityId, testCityVo);
			}else{
				TestCityVo testCityVo = (TestCityVo) citys.get(cityId);
				//添加县区
				List<TestAreaVo> testAreaVos = testCityVo.getChildren();
				testAreaVos.add(testAreaVo);
				testCityVo.setChildren(testAreaVos);
				//放入map
				citys.put(cityId, testCityVo);
			}
		}

		Map provinces = new HashMap();
		for (Object v : citys.values()) {
			TestCityVo testCityVo =(TestCityVo) v;
			String provinceId= testCityVo.getId().substring(0,2)+"0000";
			if(provinces.get(provinceId)==null){
				//封装省
				Province province = provinceMapper.selectByProvinceId(provinceId);
				TestProvinceVo testProvinceVo = new TestProvinceVo();
				testProvinceVo.setId(provinceId);
				testProvinceVo.setLabel(province.getProvince());

				//添加市区
				List<TestCityVo> testCities=new ArrayList<>();
				testCities.add(testCityVo);
				testProvinceVo.setChildren(testCities);
				//放入map
				provinces.put(provinceId, testProvinceVo);
			}else{
				TestProvinceVo testProvinceVo = (TestProvinceVo) provinces.get(provinceId);
				//添加市区
				List<TestCityVo> testCities= testProvinceVo.getChildren();
				testCities.add(testCityVo);
				testProvinceVo.setChildren(testCities);
				//放入map
				provinces.put(provinceId, testProvinceVo);
			}
		}
		List<TestProvinceVo> testProvinceVos = new ArrayList<>();
		for (Object v : provinces.values()) {
			testProvinceVos.add((TestProvinceVo) v);
		}
		return testProvinceVos;
	}

	@Override
	public List<TestProvinceVo> selectList3(){
		List<Building> buildings = buildingMapper.selectList();


		Map<String,Object> areas = new HashMap<String, Object>();
		for(Building building:buildings){

			if(areas.get(building.getAreaid())==null){
				//封装县区
				Area area = areaMapper.selectByAreaId(building.getAreaid());
				TestAreaVo testAreaVo =new TestAreaVo();
				testAreaVo.setId(building.getAreaid());
				testAreaVo.setLabel(area.getArea());

				//放入map
				areas.put(building.getAreaid(), testAreaVo);
			}
		}

		Map citys = new HashMap();
		for (Object v : areas.values()) {
			TestAreaVo testAreaVo =(TestAreaVo) v;
			String cityId= testAreaVo.getId().substring(0,4)+"00";
			if(citys.get(cityId)==null){
				//封装市区
				City city = cityMapper.selectByCityId(cityId);
				TestCityVo testCityVo = new TestCityVo();
				testCityVo.setId(cityId);
				testCityVo.setLabel(city.getCity());

				//添加县区
				List<TestAreaVo> testAreaVos =new ArrayList<>();
				testAreaVos.add(testAreaVo);
				testCityVo.setChildren(testAreaVos);
				//放入map
				citys.put(cityId, testCityVo);
			}else{
				TestCityVo testCityVo = (TestCityVo) citys.get(cityId);
				//添加县区
				List<TestAreaVo> testAreaVos = testCityVo.getChildren();
				testAreaVos.add(testAreaVo);
				testCityVo.setChildren(testAreaVos);
				//放入map
				citys.put(cityId, testCityVo);
			}
		}

		Map provinces = new HashMap();
		for (Object v : citys.values()) {
			TestCityVo testCityVo =(TestCityVo) v;
			String provinceId= testCityVo.getId().substring(0,2)+"0000";
			if(provinces.get(provinceId)==null){
				//封装省
				Province province = provinceMapper.selectByProvinceId(provinceId);
				TestProvinceVo testProvinceVo = new TestProvinceVo();
				testProvinceVo.setId(provinceId);
				testProvinceVo.setLabel(province.getProvince());

				//添加市区
				List<TestCityVo> testCities=new ArrayList<>();
				testCities.add(testCityVo);
				testProvinceVo.setChildren(testCities);
				//放入map
				provinces.put(provinceId, testProvinceVo);
			}else{
				TestProvinceVo testProvinceVo = (TestProvinceVo) provinces.get(provinceId);
				//添加市区
				List<TestCityVo> testCities= testProvinceVo.getChildren();
				testCities.add(testCityVo);
				testProvinceVo.setChildren(testCities);
				//放入map
				provinces.put(provinceId, testProvinceVo);
			}
		}
		List<TestProvinceVo> testProvinceVos = new ArrayList<>();
		for (Object v : provinces.values()) {
			testProvinceVos.add((TestProvinceVo) v);
		}
		return testProvinceVos;
	}
}
