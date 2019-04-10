package com.media.ops.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.controller.request.MaterialUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.entity.Materialgroup;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.dao.mapper.MaterialgroupMapper;
import com.media.ops.backend.service.MaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.MaterialVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialMapper materialMapper;
	@Resource
	private MaterialgroupMapper materialgroupMapper;
	
	@Override
	public PageResponseBean<MaterialVo> selectMaterialList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
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
	
	public PageResponseBean<MaterialVo> selectMaterialListByIds(String ids, int pageNum, int pageSize){
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		String idlist[]= ids.split(",");
		List<Integer> materialIds=Lists.newArrayList();
		for(String id:idlist) {
			materialIds.add(Integer.parseInt(id));
		}
		List<Material> materials=  materialMapper.selectListByIds(materialIds);
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
	public PageResponseBean<MaterialVo> selectMaterialByKeywordTypeGroup(String keyword, String type, Integer groupId,
                                                                         Integer pageNum, Integer pageSize) {
		if(StringUtils.isNotBlank(keyword)) {
			keyword= new StringBuilder().append("%").append(keyword).append("%").toString();
		}
		
		
		PageHelper.startPage(pageNum, pageSize);
		List<Material> materials= materialMapper.selectByNameTypeGroupId(StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(type)?null:type,groupId);
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
		
		
		Materialgroup materialgroup= materialgroupMapper.selectByPrimaryKey(ma.getGroupid());
		if(materialgroup!=null) {
			materialVo.setGroupId(materialgroup.getId());
			materialVo.setGroupName(materialgroup.getName());
		}
		
		if(materialMapper.checkExistDelivery(ma.getId())>0) {
			materialVo.setEditStatus(0);
		}
		else {
			materialVo.setEditStatus(1);
		}
		
		return materialVo;
	}

	@Override
	public ResponseEntity addMaterial(String createby, MaterialAddRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
	
		Material material= new Material();
		material.setName(bean.getName());
		material.setType(bean.getType());
		material.setGroupid(bean.getGroupid());
		material.setPath(bean.getPath());
		material.setCreateBy(createby);
		material.setUpdateBy(createby);
		
		int resultCount= materialMapper.insert(material);
		
		if(resultCount==0) {
			return ResponseEntityUtil.fail("添加素材失败");
		}
		
		Map<String, Object> result= Maps.newHashMap();
		result.put("newMaterial",material);
		return  ResponseEntityUtil.success(result);
	}

	@Override
	public ResponseEntity uptMaterial(String updateby, MaterialUptRequestBean bean) {
		if(bean==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Material updateMaterial= new Material();
		updateMaterial.setId(bean.getId());
		updateMaterial.setName(bean.getName());
		updateMaterial.setType(bean.getType());
		updateMaterial.setGroupid(bean.getGroupid());
		updateMaterial.setPath(bean.getPath());
		updateMaterial.setUpdateBy(updateby);
		
		int resultCount= materialMapper.updateByPrimaryKeySelective(updateMaterial);
		
		if(resultCount>0) {
			return ResponseEntityUtil.success(assembleMaterialVo(materialMapper.selectByPrimaryKey(updateMaterial.getId())));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> delMaterial(String updateby, Integer id) {
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Material material= new Material();
		material.setId(id);
		material.setUpdateBy(updateby);
		material.setDelFlag(Const.DelFlagEnum.DELETED);
		
		int resultCount= materialMapper.updateByPrimaryKeySelective(material);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<MaterialVo>> selectMaterialMusicList() {
		List<Material> list = materialMapper.selectMusicList();
		List<MaterialVo> materialVos= Lists.newArrayList();
		for(Material material : list) {
			MaterialVo materialVo= assembleMaterialVo(material);
			materialVos.add(materialVo);
		}
		return ResponseEntityUtil.success(materialVos);
	}

	@Override
	public PageResponseBean<MaterialVo> selectMaterialListExMu(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Material> materials=  materialMapper.selectList2();
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
	public PageResponseBean<MaterialVo> selectMaterialByKeywordTypeGroupExMu(String keyword, String type, Integer groupId, Integer pageNum, Integer pageSize) {
		if(StringUtils.isNotBlank(keyword)) {
			keyword= new StringBuilder().append("%").append(keyword).append("%").toString();
		}


		PageHelper.startPage(pageNum, pageSize);
		List<Material> materials= materialMapper.selectByNameTypeGroupId2(StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(type)?null:type,groupId);
		List<MaterialVo> materialVos= Lists.newArrayList();

		for(Material material : materials) {
			MaterialVo materialVo= assembleMaterialVo(material);
			materialVos.add(materialVo);
		}

		PageInfo pageInfo =new PageInfo(materials);
		pageInfo.setList(materialVos);
		return new PageResponseBean<MaterialVo>(pageInfo);
	}

}
