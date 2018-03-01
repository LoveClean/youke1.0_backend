package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.util.ResponseEntity;

public interface DeviceGroupService {

	ResponseEntity<String> addGroup(String groupName, Integer parentId);
	
	ResponseEntity<String> updateGroupName(Integer groupId, String groupName);
	
	ResponseEntity<String> deleteGroup(Integer groupId);
	
	ResponseEntity<List<Devicegroup>> getChildParallelGroup(Integer groupId);
	
	ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId);
	
	
}
