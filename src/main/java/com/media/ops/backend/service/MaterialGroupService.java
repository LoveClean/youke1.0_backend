package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Materialgroup;
import com.media.ops.backend.util.ResponseEntity;

public interface MaterialGroupService {

	ResponseEntity<String> addGroup(String groupName, Integer parentId);
	
	ResponseEntity<String> updateGroupName(Integer groupId, String groupName);
	
	ResponseEntity<String> deleteGroup(Integer groupId);
	
	ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId);
	
	ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId, String sortField,String sortRule);
	
	ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId);
}
