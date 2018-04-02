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
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.entity.Materialgroup;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.dao.mapper.MaterialgroupMapper;
import com.media.ops.backend.service.MaterialGroupService;
import com.media.ops.backend.util.ListSortUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceGroupVo;
import com.media.ops.backend.vo.MaterialGroupVo;
import com.media.ops.backend.vo.MaterialVo;

@Service
public class MaterialGroupServiceImpl implements MaterialGroupService {

	private Logger log = LoggerFactory.getLogger(MaterialGroupServiceImpl.class);

	@Autowired
	private MaterialgroupMapper materialgroupMapper;
	@Autowired
	private MaterialMapper materialMapper;

	@Override
	public ResponseEntity addGroup(String groupName, Integer parentId) {

		if (parentId == null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		Materialgroup materialgroup = new Materialgroup();

		materialgroup.setName(groupName);
		materialgroup.setParentid(parentId);
		materialgroup.setStatus(Const.GroupStatusEnum.NORMAL);

		int resultCount = materialgroupMapper.insert(materialgroup);
		if (resultCount > 0) {
			Map<String, Object> result = Maps.newHashMap();
			result.put("newData", materialgroup);
			return ResponseEntityUtil.success(result);
		}

		return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);

	}

	@Override
	public ResponseEntity updateGroupName(Integer groupId, String groupName) {
		if (groupId == null || StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		Materialgroup materialgroup = new Materialgroup();

		materialgroup.setName(groupName);
		materialgroup.setId(groupId);

		int resultCount = materialgroupMapper.updateByPrimaryKeySelective(materialgroup);

		if (resultCount > 0) {
			return ResponseEntityUtil.success(materialgroupMapper.selectByPrimaryKey(materialgroup.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteGroup(Integer groupId) {
		if (groupId == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		Materialgroup materialgroup = new Materialgroup();
		materialgroup.setId(groupId);
		materialgroup.setStatus(Const.GroupStatusEnum.ABANDON);

		int resultCount = materialgroupMapper.updateByPrimaryKeySelective(materialgroup);

		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<Materialgroup>> getChildParallelGroup(Integer groupId) {
		List<Materialgroup> materialgroups = materialgroupMapper.selectGroupChildrenByParentId(groupId);
		if (CollectionUtils.isEmpty(materialgroups)) {
			log.info("未找到当前分类的子分类");
		}

		return ResponseEntityUtil.success(materialgroups);
	}

	@Override
	public ResponseEntity<List<MaterialGroupVo>> getChildParallelGroup(Integer groupId, String sortField,
			String sortRule) {
		List<Materialgroup> materialgroups = materialgroupMapper.selectGroupChildrenByParentId(groupId);
		if (CollectionUtils.isEmpty(materialgroups)) {
			log.info("未找到当前分类的子分类");
		}

		new ListSortUtil<Materialgroup>().Sort(materialgroups, sortField, sortRule);

		List<MaterialGroupVo> materialGroupVos = Lists.newArrayList();
		for (Materialgroup materialgroup : materialgroups) {
			List<Material> materialList = materialMapper.selectByGroupId(materialgroup.getId());
			MaterialGroupVo materialGroupVo = assembleMaterialGroupVo(materialgroup, materialList);
			materialGroupVos.add(materialGroupVo);
		}

		return ResponseEntityUtil.success(materialGroupVos);
	}

	// 递归算法，算出子节点
	private Set<Materialgroup> findChildCategory(Set<Materialgroup> materialgroupSet, Integer groupId) {
		Materialgroup materialgroup = materialgroupMapper.selectByPrimaryKey(groupId);
		if (materialgroup != null) {
			materialgroupSet.add(materialgroup);
		}
		// 查找子节点，递归算法一定要有一个退出的条件
		List<Materialgroup> materialgroups = materialgroupMapper.selectGroupChildrenByParentId(groupId);
		for (Materialgroup groupItem : materialgroups) {
			findChildCategory(materialgroupSet, groupItem.getId());
		}
		return materialgroupSet;
	}

	@Override
	public ResponseEntity<List<Integer>> selectGroupAndChildrenById(Integer groupId) {
		Set<Materialgroup> materialgroups = Sets.newHashSet();
		findChildCategory(materialgroups, groupId);

		List<Integer> groupIdList = Lists.newArrayList();
		if (groupId != null) {
			for (Materialgroup materialgroup : materialgroups) {
				groupIdList.add(materialgroup.getId());
			}
		}
		return ResponseEntityUtil.success(groupIdList);
	}

	private MaterialGroupVo assembleMaterialGroupVo(Materialgroup materialgroup, List<Material> materialList) {
		MaterialGroupVo materialGroupVo = new MaterialGroupVo();
		materialGroupVo.setId(materialgroup.getId());
		materialGroupVo.setName(materialgroup.getName());

		if (CollectionUtils.isNotEmpty(materialList)) {
			List<MaterialVo> materialVoList = Lists.newArrayList();
			for (Material material : materialList) {
				MaterialVo materialVo = new MaterialVo();
				materialVo.setId(material.getId());
				materialVo.setName(material.getName());
				materialVo.setType(material.getType());
				materialVo.setPath(material.getPath());

				materialVoList.add(materialVo);
			}
			materialGroupVo.setMaterialVoList(materialVoList);
		}
		return materialGroupVo;
	}

	@Override
	public ResponseEntity<List<MaterialGroupVo>> searchGroupsbyName(String groupName) {
		if(StringUtils.isBlank(groupName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		groupName=new StringBuilder().append("%").append(groupName).append("%").toString();
		
		List<Materialgroup> materialgroups= materialgroupMapper.selectGroupsByName(groupName);
		List<MaterialGroupVo> materialGroupVos=Lists.newArrayList();
		for (Materialgroup materialgroup : materialgroups) {
			MaterialGroupVo materialGroupVo=assembleMaterialGroupVo(materialgroup,null);
			materialGroupVos.add(materialGroupVo);
		}
		
		return ResponseEntityUtil.success(materialGroupVos);
	}

}
