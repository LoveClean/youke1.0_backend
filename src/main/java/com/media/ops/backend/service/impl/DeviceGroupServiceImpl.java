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
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.mapper.DevicegroupMapper;
import com.media.ops.backend.service.DeviceGroupService;
import com.media.ops.backend.util.ListSortUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceGroupVo;

@Service
public class DeviceGroupServiceImpl implements DeviceGroupService {
	
	private  Logger log = LoggerFactory.getLogger(DeviceGroupServiceImpl.class);

	@Autowired
	private DevicegroupMapper devicegroupMapper;

	@Override
	public ResponseEntity<String> addGroup(String groupName, Integer parentId) {

		if(parentId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Devicegroup devicegroup= new Devicegroup();
		devicegroup.setName(groupName);
		devicegroup.setParentid(parentId);
		devicegroup.setStatus(Const.GroupStatusEnum.NORMAL);
		
		int resultCount= devicegroupMapper.insert(devicegroup);
		return ResponseEntityUtil.addMessage(resultCount);
      
	}

	@Override
	public ResponseEntity<String> updateGroupName(Integer groupId, String groupName) {
		if(groupId==null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Devicegroup devicegroup= new Devicegroup();
		devicegroup.setId(groupId);
		devicegroup.setName(groupName);
		
		int resultCount= devicegroupMapper.updateByPrimaryKeySelective(devicegroup);
		
		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> deleteGroup(Integer groupId) {
		if(groupId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Devicegroup devicegroup= new Devicegroup();
		devicegroup.setId(groupId);
		devicegroup.setStatus(Const.GroupStatusEnum.ABANDON);
		
		int resultCount= devicegroupMapper.updateByPrimaryKeySelective(devicegroup);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<DeviceGroupVo>> getChildParallelGroup(Integer groupId) {
		List<Devicegroup> devicegroups= devicegroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(devicegroups)) {
			log.info("未找到当前分类的子分类");
		}
		
		List<DeviceGroupVo> deviceGroupVoList= Lists.newArrayList();
		for(Devicegroup devicegroup: devicegroups) {
			DeviceGroupVo deviceGroupVo= this.assembleDeviceGroupVo(devicegroup);
			deviceGroupVoList.add(deviceGroupVo);
		}
		
		
		return ResponseEntityUtil.success(deviceGroupVoList);
	}
	
	@Override
	public ResponseEntity<List<DeviceGroupVo>> getChildParallelGroup(Integer groupId, String sortField, String sortRule) {
		List<Devicegroup> devicegroups= devicegroupMapper.selectGroupChildrenByParentId(groupId);
		if(CollectionUtils.isEmpty(devicegroups)) {
			log.info("未找到当前分类的子分类");
		}
		
		new ListSortUtil<Devicegroup>().Sort(devicegroups, sortField, sortRule);
		
		List<DeviceGroupVo> deviceGroupVoList= Lists.newArrayList();
		for(Devicegroup devicegroup: devicegroups) {
			DeviceGroupVo deviceGroupVo= this.assembleDeviceGroupVo(devicegroup);
			deviceGroupVoList.add(deviceGroupVo);
		}
		
		return ResponseEntityUtil.success(deviceGroupVoList);
	}

	
	private DeviceGroupVo assembleDeviceGroupVo(Devicegroup devicegroup) {
		DeviceGroupVo deviceGroupVo= new DeviceGroupVo();
		deviceGroupVo.setId(devicegroup.getId());
		deviceGroupVo.setName(devicegroup.getName());
		deviceGroupVo.setParentid(devicegroup.getParentid());
		return deviceGroupVo;
	}
	
	//递归算法，算出子节点
	private Set<Devicegroup> findChildCategory(Set<Devicegroup> devicegroupSet, Integer groupId){
		Devicegroup devicegroup= devicegroupMapper.selectByPrimaryKey(groupId);
		if(devicegroup!=null) {
			devicegroupSet.add(devicegroup);
		}
		//查找子节点，递归算法一定要有一个退出的条件
		List<Devicegroup> devicegroups= devicegroupMapper.selectGroupChildrenByParentId(groupId);
		for(Devicegroup groupItem: devicegroups) {
			findChildCategory(devicegroupSet, groupItem.getId());
		}
		return devicegroupSet;
	}

	@Override
	public ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId) {
		Set<Devicegroup> devicegroups= Sets.newHashSet();
		findChildCategory(devicegroups, groupId);
		
		List<Integer> groupIdList= Lists.newArrayList();
		if(groupId!=null) {
			for(Devicegroup devicegroup: devicegroups) {
				groupIdList.add(devicegroup.getId());
			}
		}
		return ResponseEntityUtil.success(groupIdList);
	}


}
