package com.media.ops.backend.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.ops.backend.dao.entity.Devicespec;
import com.media.ops.backend.dao.mapper.DevicespecMapper;
import com.media.ops.backend.service.DeviceSpecService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

@Service
public class DeviceSpecServiceImpl  implements DeviceSpecService {

	@Autowired
	private DevicespecMapper devicespecMapper;
	
	@Override
	public ResponseEntity<String> addSpec(Devicespec devicespec) {
		return ResponseEntityUtil.addMessage(devicespecMapper.insert(devicespec));
	}

	@Override
	public ResponseEntity<String> delSpec(String spec) {
		return ResponseEntityUtil.delMessage(devicespecMapper.deleteByPrimaryKey(spec));
	}

	@Override
	public ResponseEntity<List<Devicespec>> selectAll() {
		List<Devicespec> devicespecList= devicespecMapper.selectAll();
		if(CollectionUtils.isEmpty(devicespecList)) {
			return ResponseEntityUtil.fail("没有任何与规格相关的字典数据");
		}
		return ResponseEntityUtil.success(devicespecList);
	}

	
}
