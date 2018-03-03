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
import com.media.ops.backend.dao.entity.Adgroup;
import com.media.ops.backend.dao.mapper.AdgroupMapper;
import com.media.ops.backend.service.AdGroupService;
import com.media.ops.backend.util.ListSortUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
@Service
public class AdGroupServiceImpl implements AdGroupService {

	private  Logger log = LoggerFactory.getLogger(AdGroupServiceImpl.class);

	@Autowired
	private AdgroupMapper adgroupMapper;

	@Override
	public ResponseEntity<String> addGroup(String groupName, Integer parentId) {

		if(parentId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Adgroup adgroup=new Adgroup();

		adgroup.setName(groupName);
		adgroup.setParentid(parentId);
		adgroup.setStatus(Const.GroupStatusEnum.NORMAL);
		
		int resultCount= adgroupMapper.insert(adgroup);
		return ResponseEntityUtil.addMessage(resultCount);
      
	}

	@Override
	public ResponseEntity<String> updateGroupName(Integer groupId, String groupName) {
		if(groupId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Adgroup adgroup=new Adgroup();

		adgroup.setName(groupName);
		adgroup.setId(groupId);
		
		int resultCount= adgroupMapper.updateByPrimaryKeySelective(adgroup);
		
		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> deleteGroup(Integer groupId) {
		if(groupId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Adgroup adgroup= new Adgroup();
		adgroup.setId(groupId);
		adgroup.setStatus(Const.GroupStatusEnum.ABANDON);
		
		int resultCount= adgroupMapper.updateByPrimaryKeySelective(adgroup);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<Adgroup>> getChildParallelGroup(Integer groupId) {
		List<Adgroup> adgroups= adgroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(adgroups)) {
			log.info("未找到当前分类的子分类");
		}
	
		return ResponseEntityUtil.success(adgroups);
	}
	
	@Override
	public ResponseEntity<List<Adgroup>> getChildParallelGroup(Integer groupId, String sortField, String sortRule) {
		List<Adgroup> adgroups= adgroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(adgroups)) {
			log.info("未找到当前分类的子分类");
		}
		
		new ListSortUtil<Adgroup>().Sort(adgroups, sortField, sortRule);
		
		return ResponseEntityUtil.success(adgroups);
	}

	//递归算法，算出子节点
	private Set<Adgroup> findChildCategory(Set<Adgroup> adgroupSet, Integer groupId){
		Adgroup adgroup= adgroupMapper.selectByPrimaryKey(groupId);
		if(adgroup!=null) {
			adgroupSet.add(adgroup);
		}
		//查找子节点，递归算法一定要有一个退出的条件
		List<Adgroup> adgroups= adgroupMapper.selectGroupChildrenByParentId(groupId);
		for(Adgroup groupItem: adgroups) {
			findChildCategory(adgroupSet, groupItem.getId());
		}
		return adgroupSet;
	}

	@Override
	public ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId) {
		Set<Adgroup> adgroups= Sets.newHashSet();
		findChildCategory(adgroups, groupId);
		
		List<Integer> groupIdList= Lists.newArrayList();
		if(groupId!=null) {
			for(Adgroup adgroup: adgroups) {
				groupIdList.add(adgroup.getId());
			}
		}
		return ResponseEntityUtil.success(groupIdList);
	}
}
