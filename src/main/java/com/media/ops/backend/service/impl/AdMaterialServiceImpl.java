package com.media.ops.backend.service.impl;

import com.beust.jcommander.internal.Maps;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.controller.request.AdMaterialUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Admaterial;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.mapper.AdmaterialMapper;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.service.AdMaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AdMaterialVo;
import com.media.ops.backend.vo.AdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
			result.put("admaterial", admaterial);
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
		if(resultCount>0) {
			return ResponseEntityUtil.success(admaterialMapper.selectByPrimaryKey(uptAdmaterial.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);

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
	public PageResponseBean<AdMaterialVo> selectListByAdId(Integer adId,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Admaterial> admaterialList= admaterialMapper.selectByAdId(adId);
		List<AdMaterialVo> adMaterialVoList= Lists.newArrayList();
		for(Admaterial admaterial : admaterialList) {
			AdMaterialVo adMaterialVo= assembleAdMaterialVo(admaterial);
			adMaterialVoList.add(adMaterialVo);
		}
		PageInfo pageInfo=new PageInfo(admaterialList);
		pageInfo.setList(adMaterialVoList);
		PageResponseBean<AdMaterialVo> list = new PageResponseBean<AdMaterialVo>(pageInfo);
		list.setHttpStatus(200);
		list.setCode(0);
		return list;
		
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
		
		adMaterialVo.setOrderIndex(admaterial.getOrderindex()==null?1:admaterial.getOrderindex());
		adMaterialVo.setLoadStep(admaterial.getLoadstep()==null?0:admaterial.getLoadstep());
		adMaterialVo.setDisplayTime(admaterial.getDisplaytime()==null?0:admaterial.getDisplaytime());
		adMaterialVo.setMusicPath(admaterial.getMusicpath());
		
		return adMaterialVo;
		
	}


	
}
