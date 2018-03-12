package com.media.ops.backend.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.MaterailAddRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.MaterailUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.service.MaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.MaterialVo;



@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialMapper materialMapper;
	
	@Override
	public PageResponseBean<MaterialVo> selectMaterialList(PageRequestBean bean) {
		// TODO Auto-generated method stub
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Material> materials=  materialMapper.selectList();
		List<MaterialVo> materialVos= Lists.newArrayList();

		for(Material material : materials) {
			MaterialVo materialVo= assembleMaterialVo(material);
			materialVos.add(materialVo);
		}
		PageInfo pageInfo =new PageInfo(materials);
		pageInfo.setList(materialVos);
		return new PageResponseBean<MaterialVo>(pageInfo);
	}
	
	@Override
	public PageResponseBean<MaterialVo> selectMaterialByKeywordGroup(String keyword, Integer groupId,
			Integer pageNum, Integer pageSize) {
		if(StringUtils.isNotBlank(keyword)) {
			keyword= new StringBuilder().append("%").append(keyword).append("%").toString();
		}
		System.out.println(keyword+"--------"+groupId);
		PageHelper.startPage(pageNum, pageSize);
		List<Material> materials= materialMapper.selectByNameGroupId(StringUtils.isBlank(keyword)?null:keyword, groupId);
		List<MaterialVo> materialVos= Lists.newArrayList();
		
		for(Material material : materials) {
			MaterialVo materialVo= assembleMaterialVo(material);
			materialVos.add(materialVo);
		}
		
		PageInfo pageInfo =new PageInfo(materials);
		pageInfo.setList(materialVos);
		return new PageResponseBean<MaterialVo>(pageInfo);
	}

	@Override
	public ResponseEntity<MaterialVo> selectMaterial(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Material material= materialMapper.selectByPrimaryKey(id);
		MaterialVo materialVo= assembleMaterialVo(material);
	
		return ResponseEntityUtil.success(materialVo);
	}
	
	private MaterialVo assembleMaterialVo(Material ma) {
		MaterialVo materialVo=new MaterialVo();
		materialVo.setId(ma.getId());
		materialVo.setName(ma.getName());
		materialVo.setPath(ma.getPath());
		materialVo.setType(ma.getType());
		
		return materialVo;
	}

	@Override
	public ResponseEntity<String> addMaterial(MaterailAddRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Material material= new Material();
		material.setName(bean.getName());
		material.setType(bean.getType());
		material.setGroupid(bean.getGroupid());
		material.setPath(bean.getPath());
		material.setCreateBy(bean.getCreateBy());
		
		int resultCount= materialMapper.insertSelective(material);
		
		
		return ResponseEntityUtil.addMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> uptMaterial(MaterailUptRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Material updateMaterial= new Material();
		updateMaterial.setId(bean.getId());
		updateMaterial.setName(bean.getName());
		updateMaterial.setType(bean.getType());
		updateMaterial.setGroupid(bean.getGroupid());
		updateMaterial.setPath(bean.getPath());
		updateMaterial.setUpdateBy(bean.getUpdateBy());
		
		int resultCount= materialMapper.updateByPrimaryKeySelective(updateMaterial);
		
		
		return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> delMaterial(Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Material material= new Material();
		material.setId(id);
		material.setDelFlag(Const.DelFlagEnum.DELETED);
		
		int resultCount= materialMapper.updateByPrimaryKeySelective(material);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}



}
