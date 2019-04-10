package com.media.ops.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Building;
import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.mapper.BuildingMapper;
import com.media.ops.backend.dao.mapper.DeviceMapper;
import com.media.ops.backend.dao.mapper.DevicegroupMapper;
import com.media.ops.backend.dao.mapper.DevicetypeMapper;
import com.media.ops.backend.service.DeviceService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AreaVo;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private DevicegroupMapper devicegroupMapper;
	@Autowired
	private CityServiceImpl cityServiceImpl;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private DevicetypeMapper devicetypeMapper;
	
	@Override
	public PageResponseBean<DeviceListVo> selectDeviceList( Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Device> devices= deviceMapper.selectList();
		List<DeviceListVo> deviceListVos= Lists.newArrayList();
		for(Device device: devices) {
			DeviceListVo deviceListVo= assembleDeviceListVo(device);
			deviceListVos.add(deviceListVo);
		}
		PageInfo pageInfo=new PageInfo(devices);
		pageInfo.setList(deviceListVos);
		PageResponseBean<DeviceListVo> list = new PageResponseBean<DeviceListVo>(pageInfo);
		list.setHttpStatus(200);
		list.setCode(0);
		return list;
		
	}
	
	private DeviceListVo assembleDeviceListVo(Device device) {
		DeviceListVo deviceListVo=new DeviceListVo();
		deviceListVo.setId(device.getId());
		deviceListVo.setCode(device.getCode());
		deviceListVo.setMac(device.getMac());
		
//		Devicetype  devicetype= devicetypeMapper.selectByPrimaryKey(Integer.parseInt(device.getType()));
//		if(devicetype!=null) {
//			deviceListVo.setType(devicetype.getName());
//		}
		deviceListVo.setType(device.getType());
		
		deviceListVo.setBrand(device.getBrand());
		deviceListVo.setSpec(device.getSpec());
		deviceListVo.setAreaid(device.getAreaid());
		
		ResponseEntity responseEntity=cityServiceImpl.selectByAreaId(device.getAreaid());
		if(responseEntity.isSuccess()) {
			AreaVo areaVo= (AreaVo)responseEntity.getData();
			deviceListVo.setAreaFullName(areaVo.getFullName());
		}
		
		Building building= buildingMapper.selectByPrimaryKey(device.getBuildingid());
		if(building!=null) {
			deviceListVo.setBuildingid(building.getId());
			deviceListVo.setBuildingName(building.getName());
		}
		
		Devicegroup devicegroup= devicegroupMapper.selectByPrimaryKey(device.getGroupid());
		if(devicegroup!=null) {
			deviceListVo.setGroupid(devicegroup.getId());
			deviceListVo.setGroupName(devicegroup.getName());
		}
		
		deviceListVo.setAddress(device.getAddress());
		
		return deviceListVo;
	}

	@Override
	public PageResponseBean<DeviceListVo> selectDeviceByCodeAreaAddress(String code2, String cityId2, String areaId2, String address2,
																  Integer buildingId2, Integer groupId2, Integer pageNum2, Integer pageSize2) {
		String code=code2;
		String address=address2;
		String cityId= cityId2;
		String areaId=areaId2;
		Integer buildingId= buildingId2;
		Integer groupId= groupId2;
		Integer pageNum=pageNum2;
		Integer pageSize=pageSize2;
		
		if(StringUtils.isNotBlank(code)) {
			code=new StringBuilder().append("%").append(code).append("%").toString();
		}
		if(StringUtils.isNotBlank(address)) {
			address=new StringBuilder().append("%").append(address).append("%").toString();
		}
		
		if(StringUtils.isNotBlank(cityId) && StringUtils.isBlank(areaId)) {
			cityId = cityId.substring(0, 4);
			areaId=new StringBuilder().append(cityId).append("%").toString();
		}
		
		PageHelper.startPage(pageNum, pageSize);
		List<Device> devices= deviceMapper.selectByCodeAreaAddress(
				StringUtils.isBlank(code)?null:code, 
						StringUtils.isBlank(areaId)?null:areaId, StringUtils.isBlank(address)?null:address, buildingId==0?null:buildingId, groupId==0?null:groupId);
		List<DeviceListVo> deviceListVos=Lists.newArrayList();
		
		for(Device device:devices) {
			DeviceListVo deviceListVo= assembleDeviceListVo(device);
			deviceListVos.add(deviceListVo);
		}
		
		PageInfo pageInfo= new PageInfo<>(devices);
		pageInfo.setList(deviceListVos);
		PageResponseBean<DeviceListVo> list= new PageResponseBean<DeviceListVo>(pageInfo);
		list.setCode(0);
		list.setHttpStatus(200);
		return list;
	}

	@Override
	public ResponseEntity<DeviceVo> selectDevice(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Device device= deviceMapper.selectByPrimaryKey(id);
		if(device==null) {
			return ResponseEntityUtil.fail("该设备不存在或已删除");
		}
		DeviceVo deviceVo= assembleDeviceVo(device);
		
		return ResponseEntityUtil.success(deviceVo);
		
	}

	private DeviceVo assembleDeviceVo(Device device) {
		DeviceVo deviceVo=new DeviceVo();
		deviceVo.setId(device.getId());
		deviceVo.setCode(device.getCode());
		deviceVo.setMac(device.getMac());
		
//		Devicetype  devicetype= devicetypeMapper.selectByPrimaryKey(Integer.parseInt(device.getType()));
//		if(devicetype!=null) {
//			deviceVo.setType(devicetype.getName());
//		}
		deviceVo.setType(device.getType());
		
		deviceVo.setGroupid(device.getGroupid());
		deviceVo.setBrand(device.getBrand());
		deviceVo.setSpec(device.getSpec());
		deviceVo.setAreaid(device.getAreaid());
		
		ResponseEntity responseEntity=cityServiceImpl.selectByAreaId(device.getAreaid());
		if(responseEntity.isSuccess()) {
			AreaVo areaVo= (AreaVo)responseEntity.getData();
			deviceVo.setAreaFullName(areaVo.getFullName());
		}
		
		Building building= buildingMapper.selectByPrimaryKey(device.getBuildingid());
		if(building!=null) {
			deviceVo.setBuildingid(building.getId());
			deviceVo.setBuildingName(building.getName());
		}
		
		Devicegroup devicegroup= devicegroupMapper.selectByPrimaryKey(device.getGroupid());
		if(devicegroup!=null) {
			deviceVo.setGroupid(devicegroup.getId());
			deviceVo.setGroupName(devicegroup.getName());
		}
		
		deviceVo.setAddress(device.getAddress());
		
			
		return deviceVo;
	}

	@Override
	public ResponseEntity addDevice(String createby,DeviceAddRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		if(deviceMapper.checkExistCode(bean.getCode())>0) {
			return ResponseEntityUtil.fail("存在编号相同的设备!");
		}
		Device device=new Device();
		device.setCode(bean.getCode());
		device.setMac(bean.getAddress());   //设备mac地址
		device.setType(bean.getType());
		device.setGroupid(bean.getGroupid());
		device.setBrand(bean.getBrand());
		device.setSpec(bean.getSpec());
		device.setAreaid(bean.getAreaId());
		
		Building building= buildingMapper.selectByPrimaryKey(bean.getBuildingId());
		if( building!=null ) {
			device.setBuildingid(building.getId());
			device.setAddress(building.getAddress());
		}
		
		device.setCreateBy(createby);
		device.setUpdateBy(createby);
		
		
		int resultCount= deviceMapper.insert(device);

		if(resultCount==0) {
			return ResponseEntityUtil.fail("添加设备失败");
		}
		
		Map<String, Object> result= Maps.newHashMap();
		result.put("newDevice",device);
		return ResponseEntityUtil.success(result);
	}


	@Override
	public ResponseEntity uptDevice(String updateby,DeviceUptRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		if(deviceMapper.checkExistCodeNotSelf(bean.getId(), bean.getCode())>0) {
			return ResponseEntityUtil.fail("存在编号相同的设备!");
		}
		
		Device updateDevice= new Device();
		updateDevice.setId(bean.getId());
		updateDevice.setCode(bean.getCode());
		updateDevice.setMac(bean.getAddress());
		updateDevice.setType(bean.getType());
		updateDevice.setGroupid(bean.getGroupid());
		updateDevice.setBrand(bean.getBrand());
		updateDevice.setSpec(bean.getSpec());
		updateDevice.setAreaid(bean.getAreaId());
		
		Building building= buildingMapper.selectByPrimaryKey(bean.getBuildingId());
		if( building!=null ) {
			updateDevice.setBuildingid(building.getId());
			updateDevice.setAddress(building.getAddress());
		}
		
		updateDevice.setUpdateBy(updateby);
		
		int resultCount= deviceMapper.updateByPrimaryKeySelective(updateDevice);
		if(resultCount>0) {
			return ResponseEntityUtil.success(deviceMapper.selectByPrimaryKey(updateDevice.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> delDevice(Integer id,String updateby) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Device device=new Device();
		device.setId(id);
		device.setDelFlag(Const.DelFlagEnum.DELETED);
		device.setUpdateBy(updateby);
		
		int resultCount= deviceMapper.updateByPrimaryKeySelective(device);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity unboundDevice(Integer id,String updateby) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Device device=new Device();
		device.setId(id);
		device.setMac("");
		device.setUpdateBy(updateby);
		
		int resultCount= deviceMapper.updateByPrimaryKeySelective(device);
		return ResponseEntityUtil.updMessage(resultCount);
	}


}
