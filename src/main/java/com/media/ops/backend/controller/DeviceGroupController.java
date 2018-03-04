package com.media.ops.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.service.DeviceGroupService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="设备分组操作接口",produces = "application/json")
@RestController
@RequestMapping("/devicegroup/")
public class DeviceGroupController {
	@Autowired
	private DeviceGroupService deviceGroupService;
	
	@ApiOperation(value = "添加设备分组操作接口",notes = "添加设备分组")
	@PostMapping(value="add_group.do")	
	public ResponseEntity addGroup( String groupName, @RequestParam(value="parentId", defaultValue="0")int parentId) {
			return deviceGroupService.addGroup(groupName, parentId);
	}
	
	@ApiOperation(value = "修改设备分组操作接口",notes = "修改设备分组")
	@PostMapping(value="set_group.do")	
	public ResponseEntity setDeviceGroupName(Integer groupId, String groupName) {
		return deviceGroupService.updateGroupName(groupId, groupName);
	}
	
	@ApiOperation(value = "获取分组操作接口",notes = "获取分组,默认按id的升序排列,可排序字段:id,name,sortorder, 排序顺序为asc或desc")
	@PostMapping(value="get_group.do")
	public ResponseEntity getChildParallelGroup(
			@RequestParam(value="groupId", defaultValue="0") Integer groupId,
			@RequestParam(value="sortField", defaultValue="id") String sortField,
			@RequestParam(value="sortRule", defaultValue="asc") String sortRule
			) {
			return deviceGroupService.getChildParallelGroup(groupId,sortField,sortRule);
			}
	

	@ApiOperation(value = "递归获取分组操作接口",notes = "递归获取分组")
	@PostMapping(value="get_deep_group.do")
	public ResponseEntity getGroupAndDeepChildCategory(Integer groupId) {
		return deviceGroupService.selectGroupAndChildrenById(groupId);
	}
}
