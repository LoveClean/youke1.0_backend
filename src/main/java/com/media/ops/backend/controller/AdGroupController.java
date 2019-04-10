package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.AdGroupAddRequestBean;
import com.media.ops.backend.controller.request.AdGroupUptRequestBean;
import com.media.ops.backend.service.AdGroupService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description="广告分组操作接口",produces = "application/json")
@RestController
@RequestMapping("/adgroup/")
public class AdGroupController {
	@Autowired
	private AdGroupService adGroupService;
	
	@ApiOperation(value = "添加分组操作接口",notes = "添加分组")
	@PostMapping(value="add_group.do")	
	public ResponseEntity addGroup(@RequestBody AdGroupAddRequestBean bean) {
				return adGroupService.addGroup(bean.getGroupName(), bean.getParentId());
	}
	
	@ApiOperation(value = "修改分组操作接口",notes = "修改分组")
	@PostMapping(value="set_group.do")	
	public ResponseEntity setDeviceGroupName(@Valid @RequestBody AdGroupUptRequestBean bean) {
			return adGroupService.updateGroupName(bean.getGroupId(), bean.getGroupName());
	}
	//
	@ApiOperation(value = "获取分组操作接口",notes = "获取分组,默认按id的升序排列,可排序字段:id,name,sortorder, 排序顺序为asc或desc")
	@PostMapping(value="get_group.do")
	public ResponseEntity getChildParallelGroup(
			@RequestParam(value="parentId", defaultValue="0") Integer parentId,
			@RequestParam(value="sortField", defaultValue="id") String sortField,
			@RequestParam(value="sortRule", defaultValue="desc") String sortRule
			) {
			return adGroupService.getChildParallelGroup(parentId,sortField,sortRule);
	}
	//
	@ApiOperation(value = "递归获取分组操作接口",notes = "递归获取分组")
	@PostMapping(value="get_deep_group.do")
	public ResponseEntity getGroupAndDeepChildCategory(@RequestBody Integer groupId) {
			return adGroupService.selectGroupAndChildrenById(groupId);
	}	
	
	//
	@ApiOperation(value = "广告分组搜索操作接口",notes = "搜索广告分组")
	@PostMapping(value="search.do")
	public ResponseEntity searchGroups(@RequestParam String groupName) {
		return adGroupService.searchGroupsbyName(groupName);
	}
	
	@ApiOperation(value = "删除分组操作接口",notes = "删除分组")
	@PostMapping(value="del.do")	
	public ResponseEntity del(@RequestParam Integer id,HttpServletRequest request) {
		return adGroupService.deleteGroup(id);
	}
}
