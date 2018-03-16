package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.controller.request.AdMaterialUptRequestBean;
import com.media.ops.backend.dao.entity.Admaterial;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.mapper.AdmaterialMapper;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.service.AdMaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AdMaterialVo;

@Service
public class AdMaterialServiceImpl implements AdMaterialService {

	@Autowired
	private AdmaterialMapper admaterialMapper;
	@Autowired
	private MaterialMapper materialMapper;
	
	public ResponseEntity  addAdMaterial(String createby, AdMaterialAddRequestBean bean) {
		Admaterial admaterial=new Admaterial();
		admaterial.setAdid(bean.getAdId());
		admaterial.setMaterialid(bean.getMaterialId());
		admaterial.setOrderindex(bean.getOrderIndex());
		admaterial.setLoadstep(bean.getLoadStep());
		admaterial.setDisplaytime(bean.getDisplayTime());
		admaterial.setMusicpath(bean.getMusicPath());
		admaterial.setCreateBy(createby);
		admaterial.setUpdateBy(createby);
		
		int resultCount= admaterialMapper.insert(admaterial);
		if(resultCount>0) {
			Map<String, Object> result=Maps.newHashMap();
			result.put("admaterial", assembleAdMaterialVo(admaterial));
			return ResponseEntityUtil.success(result);
		}
		return ResponseEntityUtil.fail("广告素材插入失败");
	}
	
	public ResponseEntity batchInsertAdmaterial(String createby, List<AdMaterialAddRequestBean> beans) {
		List<AdMaterialVo> adMaterialVos=Lists.newArrayList();
		for (AdMaterialAddRequestBean bean : beans) {
			Admaterial admaterial=new Admaterial();
			admaterial.setAdid(bean.getAdId());
			admaterial.setMaterialid(bean.getMaterialId());
			admaterial.setOrderindex(bean.getOrderIndex());
			admaterial.setLoadstep(bean.getLoadStep());
			admaterial.setDisplaytime(bean.getDisplayTime());
			admaterial.setMusicpath(bean.getMusicPath());
			admaterial.setCreateBy(createby);
			admaterial.setUpdateBy(createby);
			
			int resultCount= admaterialMapper.insert(admaterial);
			if(resultCount>0) {
				adMaterialVos.add(assembleAdMaterialVo(admaterial));
			}
		}
		return ResponseEntityUtil.addMessage(adMaterialVos.size());
		
	}
	
	
	public ResponseEntity updateAdMaterial(String updateby, AdMaterialUptRequestBean bean) {
		Admaterial uptAdmaterial= new Admaterial();
		uptAdmaterial.setId(bean.getId());
		uptAdmaterial.setOrderindex(bean.getOrderIndex());
		uptAdmaterial.setLoadstep(bean.getLoadStep());
		uptAdmaterial.setDisplaytime(bean.getDisplayTime());
		uptAdmaterial.setMusicpath(bean.getMusicPath());
		uptAdmaterial.setUpdateBy(updateby);
		
		int resultCount= admaterialMapper.updateByPrimaryKeySelective(uptAdmaterial);
		return ResponseEntityUtil.updMessage(resultCount);
	}
	
	@Override
	public ResponseEntity batchUpdateAdMaterial(String updateby, List<AdMaterialUptRequestBean> beans) {
		// TODO Auto-generated method stub
		for (AdMaterialUptRequestBean adMaterialUptRequestBean : beans) {
			return this.updateAdMaterial(updateby, adMaterialUptRequestBean);
		}
		return ResponseEntityUtil.fail("更新广告素材失败");
	}
	
	public ResponseEntity<String> delAdMaterial(Integer id, String updateby){
		if(id==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Admaterial delAdmaterial= new Admaterial();
		delAdmaterial.setId(id);
		delAdmaterial.setDelFlag(Const.DelFlagEnum.DELETED);
		delAdmaterial.setUpdateBy(updateby);
		
		int resultCount= admaterialMapper.updateByPrimaryKeySelective(delAdmaterial);
		return ResponseEntityUtil.delMessage(resultCount);
		
	}
	
	public ResponseEntity  delAdMaterialByAdId(Integer adId, String updateby) {
		if(adId==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}

		int resultCount= admaterialMapper.batchUpdateDelFlagByAdId(adId, updateby);
		
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public List<AdMaterialVo> selectListByAdId(Integer adId) {
		
		List<Admaterial> admaterialList= admaterialMapper.selectByAdId(adId);
		List<AdMaterialVo> adMaterialVoList= Lists.newArrayList();
		for(Admaterial admaterial : admaterialList) {
			AdMaterialVo adMaterialVo= assembleAdMaterialVo(admaterial);
			adMaterialVoList.add(adMaterialVo);
		}
		
		return adMaterialVoList;
		
	}
	
	private AdMaterialVo assembleAdMaterialVo(Admaterial admaterial) {
		AdMaterialVo adMaterialVo=new AdMaterialVo();
		adMaterialVo.setId(admaterial.getId());
		adMaterialVo.setAdId(admaterial.getAdid());
		
		
		Material material= materialMapper.selectByPrimaryKey(admaterial.getMaterialid());
		adMaterialVo.setMaterialId(material.getId());
		adMaterialVo.setMaterialName(material.getName());
		adMaterialVo.setMaterialType(material.getType());
		adMaterialVo.setMaterialPath(material.getPath());
		
		adMaterialVo.setOrderIndex(admaterial.getOrderindex());
		adMaterialVo.setLoadStep(admaterial.getLoadstep());
		adMaterialVo.setDisplayTime(admaterial.getDisplaytime());
		adMaterialVo.setMusicPath(admaterial.getMusicpath());
		
		return adMaterialVo;
		
	}


	
}
