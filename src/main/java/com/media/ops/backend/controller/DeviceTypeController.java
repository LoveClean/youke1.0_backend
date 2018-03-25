package com.media.ops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.dao.entity.Devicetype;
import com.media.ops.backend.service.DeviceTypeService;
import com.media.ops.backend.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="设备类型字典管理接口",produces = "application/json")
@RestController
@RequestMapping("/devicetype/")
public class DeviceTypeController {

	@Autowired
	private DeviceTypeService deviceTypeService;
	
	@ApiOperation(value = "添加设备类型字典接口",notes = "添加设备类型")
	@PostMapping(value="add_type.do")	
	public ResponseEntity addType(@RequestBody String name) {
			return deviceTypeService.addType(new Devicetype(name));
	}
	
	@ApiOperation(value = "删除设备类型字典接口",notes = "删除设备类型")
	@PostMapping(value="del_type.do")	
	public ResponseEntity delType(@RequestBody Integer id) {
			return deviceTypeService.delType(id);
	}
	@ApiOperation(value = "获取设备类型字典接口",notes = "获取设备类型")
	@GetMapping(value="get_list.do")		
	public ResponseEntity<List<Devicetype>> getList() {
		return deviceTypeService.selectAll();
	}
}
