package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Ad;
import com.media.ops.backend.dao.entity.Adgroup;
import com.media.ops.backend.dao.entity.Admaterial;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.mapper.AdMapper;
import com.media.ops.backend.dao.mapper.AdgroupMapper;
import com.media.ops.backend.dao.mapper.AdmaterialMapper;
import com.media.ops.backend.dao.mapper.MaterialMapper;
import com.media.ops.backend.service.AdMaterialService;
import com.media.ops.backend.service.AdService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AdMaterialVo;
import com.media.ops.backend.vo.AdVo;

@Service
public class AdServiceImpl implements AdService{

	@Autowired
	private AdMapper adMapper;
	@Autowired
	private AdmaterialMapper admaterialMapper;
	@Autowired
	private AdgroupMapper adgroupMapper;
	@Autowired
	private MaterialMapper materialMapper;
	
	public ResponseEntity  addAd(String createby, AdAddRequestBean bean) {
		Ad ad=new Ad();
		ad.setCreateBy(createby);
		ad.setUpdateBy(createby);
		ad.setName(bean.getName());
		ad.setGroupid(bean.getGroupid());
		
		int resultCount=adMapper.insert(ad);
		if(resultCount>0) {
			Map<String, Object> result= Maps.newHashMap();
			result.put("ad", ad);
			return  ResponseEntityUtil.success(result);
		}

		return ResponseEntityUtil.fail("新建广告失败");
	}
	
	public ResponseEntity delAd(Integer adId, String updateby) {
		Ad uptAd=new Ad();
		uptAd.setDelFlag(Const.DelFlagEnum.DELETED);
		uptAd.setUpdateBy(updateby);
		uptAd.setId(adId);
		int resultCount= adMapper.updateByPrimaryKeySelective(uptAd);
		
		if(resultCount>0) {
			admaterialMapper.batchUpdateDelFlagByAdId(adId, updateby);
		}

		return ResponseEntityUtil.delMessage(resultCount);

	}

	@Override
	public ResponseEntity uptAdName(String updateby, AdUptRequestBean bean) {
		Ad uptAd=new Ad();
		uptAd.setUpdateBy(updateby);
		uptAd.setId(bean.getId());
		uptAd.setName(bean.getName());
		int resultCount= adMapper.updateByPrimaryKeySelective(uptAd);
		return ResponseEntityUtil.updMessage(resultCount);
	}
	
	public PageResponseBean<AdVo> selectAdList(PageRequestBean bean){
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Ad> adList= adMapper.selectList();
		List<AdVo> adVos= Lists.newArrayList();
		for(Ad ad: adList) {
			List<Admaterial> admaterials= admaterialMapper.selectByAdId(ad.getId());
			List<AdMaterialVo> adMaterialVos=Lists.newArrayList();
			for (Admaterial admaterial : admaterials) {
				AdMaterialVo adMaterialVo= assembleAdMaterialVo(admaterial);
				adMaterialVos.add(adMaterialVo);
			}
			
			AdVo adVo= assembleAdVo(ad, adMaterialVos);
			adVos.add(adVo);
		}
		
		PageInfo pageInfo=new PageInfo(adList);
		pageInfo.setList(adVos);
		
		return new PageResponseBean<AdVo>(pageInfo);
	}
	
	
	private AdVo assembleAdVo(Ad ad, List<AdMaterialVo> adMaterialVos) {
		AdVo adVo=new AdVo();
		adVo.setId(ad.getId());
		adVo.setName(ad.getName());
		adVo.setGroupid(ad.getGroupid());
		
		Adgroup adgroup= adgroupMapper.selectByPrimaryKey(ad.getGroupid());
		if(adgroup!=null) {
			adVo.setGroupName(adgroup.getName());
		}
		adVo.setAdMaterialVos(adMaterialVos);
	
		return adVo;
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
