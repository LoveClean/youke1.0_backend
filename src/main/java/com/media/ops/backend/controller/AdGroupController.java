package com.media.ops.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.AdGroupService;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="广告分组操作接口",produces = "application/json")
@RestController
@RequestMapping("/adgroup/")
public class AdGroupController {
	@Autowired
	private UserService userService;
	@Autowired
	private AdGroupService adGroupService;
	
	@ApiOperation(value = "添加分组操作接口",notes = "添加分组")
	@PostMapping(value="add_group.do")	
	public ResponseEntity addGroup(HttpSession session, String groupName, @RequestParam(value="parentId", defaultValue="0")int parentId) {
		User user= (User)session.getAttribute(Const.CURRENT_USER);
		if(user==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN);
		}
		if(userService.checkAdminRole(user).isSuccess()) {
			return adGroupService.addGroup(groupName, parentId);
		}else {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
		}
	}
	
	@ApiOperation(value = "修改分组操作接口",notes = "修改分组")
	@PostMapping(value="set_group.do")	
	public ResponseEntity setDeviceGroupName(HttpSession session, Integer groupId, String groupName) {
		User user= (User)session.getAttribute(Const.CURRENT_USER);
		if(user==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN);
		}
		if(userService.checkAdminRole(user).isSuccess()) {
			return adGroupService.updateGroupName(groupId, groupName);
		}else {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
		}
	}
	
	@ApiOperation(value = "获取分组操作接口",notes = "获取分组,默认按id的升序排列,可排序字段:id,name,sortorder, 排序顺序为asc或desc")
	@PostMapping(value="get_group.do")
	public ResponseEntity getChildParallelGroup(
			HttpSession session, 
			@RequestParam(value="groupId", defaultValue="0") Integer groupId,
			@RequestParam(value="sortField", defaultValue="id") String sortField,
			@RequestParam(value="sortRule", defaultValue="asc") String sortRule
			) {
		User user= (User)session.getAttribute(Const.CURRENT_USER);
		if(user==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN);
		}
		if(userService.checkAdminRole(user).isSuccess()) {
			return adGroupService.getChildParallelGroup(groupId,sortField,sortRule);
		}else {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
		}
	}
	
	@ApiOperation(value = "递归获取分组操作接口",notes = "递归获取分组")
	@PostMapping(value="get_deep_group.do")
	public ResponseEntity getGroupAndDeepChildCategory(HttpSession session, Integer groupId) {
		User user= (User)session.getAttribute(Const.CURRENT_USER);
		if(user==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN);
		}
		if(userService.checkAdminRole(user).isSuccess()) {
			//查询当前节点和节点的所有子节点
			return adGroupService.selectGroupAndChildrenById(groupId);
		}else {
			return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
		}
	}	
	

}
