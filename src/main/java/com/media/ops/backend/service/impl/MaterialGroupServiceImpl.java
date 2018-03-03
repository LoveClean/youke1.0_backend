package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;

import com.media.ops.backend.dao.entity.Materialgroup;

import com.media.ops.backend.dao.mapper.MaterialgroupMapper;
import com.media.ops.backend.service.MaterialGroupService;
import com.media.ops.backend.util.ListSortUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;


@Service
public class MaterialGroupServiceImpl implements MaterialGroupService{

	private  Logger log = LoggerFactory.getLogger(MaterialGroupServiceImpl.class);

	@Autowired
	private MaterialgroupMapper materialgroupMapper;

	@Override
	public ResponseEntity<String> addGroup(String groupName, Integer parentId) {

		if(parentId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Materialgroup materialgroup=new Materialgroup();

		materialgroup.setName(groupName);
		materialgroup.setParentid(parentId);
		materialgroup.setStatus(Const.GroupStatusEnum.NORMAL);
		
		int resultCount= materialgroupMapper.insert(materialgroup);
		return ResponseEntityUtil.addMessage(resultCount);
      
	}

	@Override
	public ResponseEntity<String> updateGroupName(Integer groupId, String groupName) {
		if(groupId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Materialgroup materialgroup=new Materialgroup();

		materialgroup.setName(groupName);
		materialgroup.setId(groupId);
		
		int resultCount= materialgroupMapper.updateByPrimaryKeySelective(materialgroup);
		
		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> deleteGroup(Integer groupId) {
		if(groupId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Materialgroup materialgroup= new Materialgroup();
		materialgroup.setId(groupId);
		materialgroup.setStatus(Const.GroupStatusEnum.ABANDON);
		
		int resultCount= materialgroupMapper.updateByPrimaryKeySelective(materialgroup);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId) {
		List<Materialgroup> materialgroups= materialgroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(materialgroups)) {
			log.info("未找到当前分类的子分类");
		}
	
		return ResponseEntityUtil.success(materialgroups);
	}
	
	@Override
	public ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId, String sortField, String sortRule) {
		List<Materialgroup> materialgroups= materialgroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(materialgroups)) {
			log.info("未找到当前分类的子分类");
		}
		
		new ListSortUtil<Materialgroup>().Sort(materialgroups, sortField, sortRule);
		
		return ResponseEntityUtil.success(materialgroups);
	}

	//递归算法，算出子节点
	private Set<Materialgroup> findChildCategory(Set<Materialgroup> materialgroupSet, Integer groupId){
		Materialgroup materialgroup= materialgroupMapper.selectByPrimaryKey(groupId);
		if(materialgroup!=null) {
			materialgroupSet.add(materialgroup);
		}
		//查找子节点，递归算法一定要有一个退出的条件
		List<Materialgroup> materialgroups= materialgroupMapper.selectGroupChildrenByParentId(groupId);
		for(Materialgroup groupItem: materialgroups) {
			findChildCategory(materialgroupSet, groupItem.getId());
		}
		return materialgroupSet;
	}

	@Override
	public ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId) {
		Set<Materialgroup> materialgroups= Sets.newHashSet();
		findChildCategory(materialgroups, groupId);
		
		List<Integer> groupIdList= Lists.newArrayList();
		if(groupId!=null) {
			for(Materialgroup materialgroup: materialgroups) {
				groupIdList.add(materialgroup.getId());
			}
		}
		return ResponseEntityUtil.success(groupIdList);
	}
}
