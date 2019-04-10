package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Devicespec;
import com.media.ops.backend.service.DeviceSpecService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="设备规格字典管理接口",produces = "application/json")
@RestController
@RequestMapping("/devicespec/")
public class DeviceSpecController {

	@Autowired
	private DeviceSpecService deviceSpecService;
	
	@ApiOperation(value = "添加设备规格字典接口",notes = "添加设备规格")
	@PostMapping(value="add_spec.do")	
	public ResponseEntity addSpec(@RequestParam String spec) {
			return deviceSpecService.addSpec(new Devicespec(spec));
	}
	
	@ApiOperation(value = "删除设备规格字典接口",notes = "删除设备规格")
	@PostMapping(value="del_spec.do")	
	public ResponseEntity delSpec(@RequestParam String spec) {
			return deviceSpecService.delSpec(spec);
	}
	@ApiOperation(value = "获取设备规格字典接口",notes = "获取设备规格")
	@GetMapping(value="get_list.do")		
	public ResponseEntity<List<Devicespec>> getList() {
		return deviceSpecService.selectAll(); 
	}
}
