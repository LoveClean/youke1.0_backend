package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.DeviceGroupVo;

public interface DeviceGroupService {

	ResponseEntity addGroup(String groupName, Integer parentId);
	
	ResponseEntity updateGroupName(Integer groupId, String groupName);
	
	ResponseEntity<String> deleteGroup(Integer groupId);
	
	ResponseEntity<List<DeviceGroupVo>> getChildParallelGroup(Integer groupId);
	
	ResponseEntity<List<DeviceGroupVo>> getChildParallelGroup(Integer groupId, String sortField,String sortRule);
	
	ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId);
	
	
}
