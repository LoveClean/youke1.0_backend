package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Devicetype;
import com.media.ops.backend.service.DeviceTypeService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="设备类型字典管理接口",produces = "application/json")
@RestController
@RequestMapping("/devicetype/")
public class DeviceTypeController {

	@Autowired
	private DeviceTypeService deviceTypeService;
	
	@ApiOperation(value = "添加设备类型字典接口",notes = "添加设备类型")
	@PostMapping(value="add_type.do")	
	public ResponseEntity addType(@RequestParam String name) {
			return deviceTypeService.addType(new Devicetype(name));
	}
	
	@ApiOperation(value = "删除设备类型字典接口",notes = "删除设备类型")
	@PostMapping(value="del_type.do")	
	public ResponseEntity delType(@RequestParam Integer id) {
			return deviceTypeService.delType(id);
	}
	@ApiOperation(value = "获取设备类型字典接口",notes = "获取设备类型")
	@GetMapping(value="get_list.do")		
	public ResponseEntity<List<Devicetype>> getList() {
		return deviceTypeService.selectAll();
	}
}
