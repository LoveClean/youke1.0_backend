package com.media.ops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.dao.entity.Devicebrand;
import com.media.ops.backend.dao.entity.Devicespec;
import com.media.ops.backend.service.DeviceBrandService;
import com.media.ops.backend.service.DeviceSpecService;
import com.media.ops.backend.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="设备品牌字典管理接口",produces = "application/json")
@RestController
@RequestMapping("/devicebrand/")
public class DeviceBrandController {
	@Autowired
	private DeviceBrandService deviceBrandService;
	
	@ApiOperation(value = "添加设备品牌字典接口",notes = "添加设备品牌")
	@PostMapping(value="add_brand.do")	
	public ResponseEntity addBrand(String brand) {
			return deviceBrandService.addBrand(new Devicebrand(brand));
	}
	
	@ApiOperation(value = "删除设备品牌字典接口",notes = "删除设备品牌")
	@PostMapping(value="del_brand.do")	
	public ResponseEntity delBrand(String brand) {
			return deviceBrandService.delBrand(brand);
	}
	@ApiOperation(value = "获取设备品牌字典接口",notes = "获取设备品牌")
	@GetMapping(value="get_list.do")		
	public ResponseEntity<List<Devicebrand>> getList() {
		return deviceBrandService.selectAll(); 
	}	
}
