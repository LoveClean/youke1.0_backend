package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.DeviceGroupAddRequestBean;
import com.media.ops.backend.controller.request.DeviceGroupUptRequestBean;
import com.media.ops.backend.service.DeviceGroupService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description="设备分组操作接口",produces = "application/json")
@RestController
@RequestMapping("/devicegroup/")
public class DeviceGroupController {
	@Autowired
	private DeviceGroupService deviceGroupService;
	
	@ApiOperation(value = "添加设备分组操作接口",notes = "添加设备分组")
	@PostMapping(value="add_group.do")	
	public ResponseEntity addGroup(@RequestBody DeviceGroupAddRequestBean bean) {
			return deviceGroupService.addGroup(bean.getGroupName(), bean.getParentId());
	}
	
	@ApiOperation(value = "修改设备分组操作接口",notes = "修改设备分组")
	@PostMapping(value="set_group.do")	
	public ResponseEntity setDeviceGroupName(@Valid @RequestBody  DeviceGroupUptRequestBean bean) {
		return deviceGroupService.updateGroupName(bean.getGroupId(), bean.getGroupName());
	}
	
	@ApiOperation(value = "获取分组操作接口",notes = "获取分组,默认按id的升序排列,可排序字段:id,name,sortorder, 排序顺序为asc或desc")
	@PostMapping(value="get_group.do")
	public ResponseEntity getChildParallelGroup(
			@RequestParam(value="groupId", defaultValue="0") Integer groupId,
			@RequestParam(value="sortField", defaultValue="id") String sortField,
			@RequestParam(value="sortRule", defaultValue="desc") String sortRule
			) {
			return deviceGroupService.getChildParallelGroup(groupId,sortField,sortRule);
			}
	

	@ApiOperation(value = "递归获取分组操作接口",notes = "递归获取分组")
	@PostMapping(value="get_deep_group.do")
	public ResponseEntity getGroupAndDeepChildCategory(@RequestBody Integer groupId) {
		return deviceGroupService.selectGroupAndChildrenById(groupId);
	}
	

	@ApiOperation(value = "设备分组搜索操作接口",notes = "搜索设备分组")
	@PostMapping(value="search.do")
	public ResponseEntity searchGroups(@RequestParam String groupName) {
		return deviceGroupService.searchGroupsbyName(groupName);
	}
	
	@ApiOperation(value = "删除分组操作接口",notes = "删除分组")
	@PostMapping(value="del.do")	
	public ResponseEntity del(@RequestParam  Integer id,HttpServletRequest request) {
		return deviceGroupService.deleteGroup(id);
	}
}
