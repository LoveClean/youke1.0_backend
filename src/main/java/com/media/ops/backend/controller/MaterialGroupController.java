package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.MaterialGroupAddRequestBean;
import com.media.ops.backend.controller.request.MaterialGroupUptRequestBean;
import com.media.ops.backend.service.MaterialGroupService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description="素材分组操作接口",produces = "application/json")
@RestController
@RequestMapping("/materialgroup/")
public class MaterialGroupController extends BaseController{
	@Autowired
	private MaterialGroupService materialGroupService;
	
	@ApiOperation(value = "添加素材分组操作接口",notes = "添加素材分组")
	@PostMapping(value="add_group.do")	
	public ResponseEntity addGroup(@RequestBody MaterialGroupAddRequestBean bean) {
			return materialGroupService.addGroup(bean.getGroupName(), bean.getParentId());
	}
	
	@ApiOperation(value = "修改素材分组操作接口",notes = "修改素材分组")
	@PostMapping(value="set_group.do")	
	public ResponseEntity setDeviceGroupName(@Valid @RequestBody MaterialGroupUptRequestBean bean) {
		return materialGroupService.updateGroupName(bean.getGroupId(), bean.getGroupName());
	}
	
	@ApiOperation(value = "获取分组操作接口",notes = "获取分组,默认按id的升序排列,可排序字段:id,name,sortorder, 排序顺序为asc或desc")
	@PostMapping(value="get_group.do")
	public ResponseEntity getChildParallelGroup(
			@RequestParam(value="groupId", defaultValue="0") Integer groupId,
			@RequestParam(value="sortField", defaultValue="id") String sortField,
			@RequestParam(value="sortRule", defaultValue="desc") String sortRule
			) {
			return materialGroupService.getChildParallelGroup(groupId,sortField,sortRule);
	}
	
	@ApiOperation(value = "递归获取分组操作接口",notes = "递归获取分组")
	@PostMapping(value="get_deep_group.do")
	public ResponseEntity getGroupAndDeepChildCategory(@RequestBody Integer groupId) {
			return materialGroupService.selectGroupAndChildrenById(groupId);
	}
	

	@ApiOperation(value = "素材分组搜索操作接口",notes = "搜索素材分组")
	@PostMapping(value="search.do")
	public ResponseEntity searchGroups(@RequestParam String groupName) {
		return materialGroupService.searchGroupsbyName(groupName);
	}
	
	@ApiOperation(value = "删除分组操作接口",notes = "删除分组")
	@PostMapping(value="del.do")	
	public ResponseEntity del(@RequestParam Integer id,HttpServletRequest request) {
		return materialGroupService.deleteGroup(id);
	}
}
