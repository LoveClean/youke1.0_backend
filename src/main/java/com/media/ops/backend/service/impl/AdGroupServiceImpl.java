package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.dao.entity.Ad;
import com.media.ops.backend.dao.entity.Adgroup;
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.mapper.AdMapper;
import com.media.ops.backend.dao.mapper.AdgroupMapper;
import com.media.ops.backend.service.AdGroupService;
import com.media.ops.backend.util.ListSortUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AdGroupVo;
import com.media.ops.backend.vo.DeviceGroupVo;
@Service
public class AdGroupServiceImpl implements AdGroupService {

	private  Logger log = LoggerFactory.getLogger(AdGroupServiceImpl.class);

	@Autowired
	private AdgroupMapper adgroupMapper;
	@Autowired
	private AdMapper adMapper;

	@Override
	public ResponseEntity addGroup(String groupName, Integer parentId) {

		if(parentId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		if(adgroupMapper.checkExistName(groupName)>0) {
			return ResponseEntityUtil.fail("同名分组已存在！");
		}
		
		Adgroup adgroup=new Adgroup();

		adgroup.setName(groupName);
		adgroup.setParentid(parentId);
		adgroup.setStatus(Const.GroupStatusEnum.NORMAL);
		
		int resultCount= adgroupMapper.insert(adgroup);
		
		if(resultCount>0) {
			Map<String, Object> result= Maps.newHashMap();
			result.put("newData", adgroup);
			return ResponseEntityUtil.success(result);
		}
		
		return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
      
	}

	@Override
	public ResponseEntity updateGroupName(Integer groupId, String groupName) {
		if(groupId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		if(adgroupMapper.checkExistNameNotSelf(groupName, groupId)>0) {
			return ResponseEntityUtil.fail("同名分组已存在！");
		}
		
		Adgroup adgroup=new Adgroup();

		adgroup.setName(groupName);
		adgroup.setId(groupId);
		
		int resultCount= adgroupMapper.updateByPrimaryKeySelective(adgroup);
		if(resultCount>0) {
			return ResponseEntityUtil.success(adgroupMapper.selectByPrimaryKey(adgroup.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteGroup(Integer groupId) {
		if(groupId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		List<Ad> ads= adMapper.selectByNameGroupId(null, groupId,null,null);
		if(CollectionUtils.isNotEmpty(ads)) {
			return ResponseEntityUtil.fail("该分组下已有广告，不能删除！");
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

	@Override
	public ResponseEntity<List<AdGroupVo>> searchGroupsbyName(String groupName) {
		if(StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		groupName=new StringBuilder().append("%").append(groupName).append("%").toString();
		List<Adgroup> adgroups= adgroupMapper.selectGroupsByName(groupName);
		List<AdGroupVo> adGroupVos=Lists.newArrayList();
		for (Adgroup adgroup : adgroups) {
			AdGroupVo adGroupVo=assembleAdGroupVo(adgroup);
			adGroupVos.add(adGroupVo);
		}
		
		return ResponseEntityUtil.success(adGroupVos);
	}
	
	private AdGroupVo assembleAdGroupVo(Adgroup adgroup) {
		AdGroupVo adGroupVo= new AdGroupVo();
		adGroupVo.setId(adgroup.getId());
		adGroupVo.setName(adgroup.getName());
		adGroupVo.setParentid(adgroup.getParentid());
		return adGroupVo;
	}
}
