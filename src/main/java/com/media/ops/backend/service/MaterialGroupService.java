package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Materialgroup;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.MaterialGroupVo;

public interface MaterialGroupService {

	ResponseEntity addGroup(String groupName, Integer parentId);
	
	ResponseEntity updateGroupName(Integer groupId, String groupName);
	
	ResponseEntity<String> deleteGroup(Integer groupId);
	
	ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId);
	
	ResponseEntity<List<MaterialGroupVo>> getChildParallelGroup(Integer groupId, String sortField,String sortRule);
	
	ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId);
	
	ResponseEntity<List<MaterialGroupVo>> searchGroupsbyName(String groupName);
}
