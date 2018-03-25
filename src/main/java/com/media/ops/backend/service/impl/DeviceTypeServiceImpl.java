package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.media.ops.backend.dao.entity.Devicetype;
import com.media.ops.backend.dao.mapper.DevicetypeMapper;
import com.media.ops.backend.service.DeviceTypeService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService{

	@Autowired
	private DevicetypeMapper devicetypeMapper;
	
	
	@Override
	public ResponseEntity addType(Devicetype devicetype) {
		int resultCount=devicetypeMapper.insert(devicetype);
		if(resultCount==0) {
			return ResponseEntityUtil.fail("添加设备类型失败");
		}
		
		Map<String, Object> result= Maps.newHashMap();
		result.put("newData",devicetype);
		return ResponseEntityUtil.success(result);
	}

	@Override
	public ResponseEntity<String> delType(Integer id) {
		return ResponseEntityUtil.delMessage(devicetypeMapper.deleteByPrimaryKey(id));
	}

	@Override
	public ResponseEntity<List<Devicetype>> selectAll() {
		List<Devicetype> devicetypes= devicetypeMapper.selectAll();
		if(CollectionUtils.isEmpty(devicetypes)) {
			return ResponseEntityUtil.fail("没有任何与类型相关的字典数据");
		}
		return ResponseEntityUtil.success(devicetypes);
	}

	
}
