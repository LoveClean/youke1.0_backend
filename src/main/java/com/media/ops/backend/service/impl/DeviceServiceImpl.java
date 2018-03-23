package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceSearchRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.mapper.DeviceMapper;
import com.media.ops.backend.dao.mapper.DevicegroupMapper;
import com.media.ops.backend.service.DeviceService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;
@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private DevicegroupMapper devicegroupMapper;
	
	@Override
	public PageResponseBean<DeviceListVo> selectDeviceList(PageRequestBean bean) {
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Device> devices= deviceMapper.selectList();
		List<DeviceListVo> deviceListVos= Lists.newArrayList();
		for(Device device: devices) {
			DeviceListVo deviceListVo= assembleDeviceListVo(device);
			deviceListVos.add(deviceListVo);
		}
		PageInfo pageInfo=new PageInfo(devices);
		pageInfo.setList(deviceListVos);
		
		return new PageResponseBean<DeviceListVo>(pageInfo);
		
	}
	
	private DeviceListVo assembleDeviceListVo(Device device) {
		DeviceListVo deviceListVo=new DeviceListVo();
		deviceListVo.setCode(device.getCode());
		deviceListVo.setMac(device.getMac());
		deviceListVo.setType(device.getType());
		deviceListVo.setBrand(device.getBrand());
		deviceListVo.setSpec(device.getSpec());
		deviceListVo.setAreaid(device.getAreaid());
		deviceListVo.setBuildingid(device.getBuildingid());
		deviceListVo.setAddress(device.getAddress());
		
		return deviceListVo;
	}

	@Override
	public ResponseEntity<PageInfo> selectDeviceByCodeAreaAddress(DeviceSearchRequestBean bean) {
		String code=bean.getCode();
		String address=bean.getAddress();
		String areaId=bean.getAreaId();
		Integer pageNum=bean.getPageNum();
		Integer pageSize=bean.getPageSize();
		
		
		if(StringUtils.isBlank(code)&& StringUtils.isBlank(address)&&StringUtils.isBlank(areaId)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		if(StringUtils.isNotBlank(code)) {
			code=new StringBuilder().append("%").append(code).append("%").toString();
		}
		if(StringUtils.isNotBlank(address)) {
			address=new StringBuilder().append("%").append(address).append("%").toString();
		}
		
		PageHelper.startPage(pageNum, pageSize);
		List<Device> devices= deviceMapper.selectByCodeAreaAddress(
				StringUtils.isBlank(code)?null:code, 
						StringUtils.isBlank(areaId)?null:areaId, StringUtils.isBlank(address)?null:address);
		List<DeviceListVo> deviceListVos=Lists.newArrayList();
		
		for(Device device:devices) {
			DeviceListVo deviceListVo= assembleDeviceListVo(device);
			deviceListVos.add(deviceListVo);
		}
		
		PageInfo pageInfo= new PageInfo<>(devices);
		pageInfo.setList(deviceListVos);
		
		return ResponseEntityUtil.success(pageInfo);
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
	public ResponseEntity addDevice(DeviceAddRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Device device=new Device();
		device.setCode(bean.getCode());
		device.setAddress(bean.getAddress());
		device.setType(bean.getType());
		device.setGroupid(bean.getGroupid());
		device.setBrand(bean.getBrand());
		device.setSpec(bean.getSpec());
		device.setAreaid(bean.getAreaId());
		device.setBuildingid(bean.getBuildingId());
		device.setCreateBy(bean.getCreateby());
		
		int resultCount= deviceMapper.insertSelective(device);

		if(resultCount==0) {
			return ResponseEntityUtil.fail("添加设备失败");
		}
		
		Map<String, Object> result= Maps.newHashMap();
		result.put("newDevice",assembleDeviceVo(device));
		return ResponseEntityUtil.success(result);
	}

	@Override
	public ResponseEntity<String> uptDevice(DeviceUptRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Device updateDevice= new Device();
		updateDevice.setId(bean.getId());
		updateDevice.setCode(bean.getCode());
		updateDevice.setAddress(bean.getAddress());
		updateDevice.setType(bean.getType());
		updateDevice.setGroupid(bean.getGroupid());
		updateDevice.setBrand(bean.getBrand());
		updateDevice.setSpec(bean.getSpec());
		updateDevice.setAreaid(bean.getAreaId());
		updateDevice.setBuildingid(bean.getBuildingId());
		updateDevice.setUpdateBy(bean.getUpdateby());
		
		int resultCount= deviceMapper.updateByPrimaryKeySelective(updateDevice);
		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> delDevice(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Device device=new Device();
		device.setId(id);
		device.setDelFlag(Const.DelFlagEnum.DELETED);
		
		int resultCount= deviceMapper.updateByPrimaryKeySelective(device);
		return ResponseEntityUtil.delMessage(resultCount);
	}

}
