package com.media.ops.backend.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.ops.backend.dao.entity.Devicebrand;
import com.media.ops.backend.dao.mapper.DevicebrandMapper;
import com.media.ops.backend.service.DeviceBrandService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

@Service
public class DeviceBrandServiceImpl implements DeviceBrandService {

	@Autowired
	private DevicebrandMapper devicebrandMapper;
	
	@Override
	public ResponseEntity<String> addBrand(Devicebrand devicebrand) {
		// TODO Auto-generated method stub
		if(devicebrandMapper.checkExist(devicebrand.getBrand())>0) {
			return ResponseEntityUtil.fail("已存在同名的设备品牌！");
		}else {
			return ResponseEntityUtil.addMessage(devicebrandMapper.insert(devicebrand));			
		}
	}

	@Override
	public ResponseEntity<String> delBrand(String brand) {
		return ResponseEntityUtil.delMessage(devicebrandMapper.deleteByPrimaryKey(brand));
	}

	@Override
	public ResponseEntity<List<Devicebrand>> selectAll() {
		List<Devicebrand> devicebrandList= devicebrandMapper.selectAll();
		if(CollectionUtils.isEmpty(devicebrandList)) {
			return ResponseEntityUtil.fail("没有任何与品牌相关的字典数据");
		}
		return ResponseEntityUtil.success(devicebrandList);
	}

}
