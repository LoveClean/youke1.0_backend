package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Adgroup;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdGroupVo;
import com.media.ops.backend.vo.DeviceGroupVo;

public interface AdGroupService {

	ResponseEntity addGroup(String groupName, Integer parentId);
	
	ResponseEntity updateGroupName(Integer groupId, String groupName);
	
	ResponseEntity<String> deleteGroup(Integer groupId);
	
	ResponseEntity<List<Adgroup>> getChildParallelGroup(Integer groupId);
	
	ResponseEntity<List<Adgroup>> getChildParallelGroup(Integer groupId, String sortField,String sortRule);
	
	ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId);
	
	ResponseEntity<List<AdGroupVo>> searchGroupsbyName(String groupName);
}
