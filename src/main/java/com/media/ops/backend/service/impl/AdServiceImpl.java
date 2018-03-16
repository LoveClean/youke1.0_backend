package com.media.ops.backend.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.dao.entity.Ad;
import com.media.ops.backend.dao.mapper.AdMapper;
import com.media.ops.backend.dao.mapper.AdmaterialMapper;
import com.media.ops.backend.service.AdService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

@Service
public class AdServiceImpl implements AdService{

	@Autowired
	private AdMapper adMapper;
	@Autowired
	private AdmaterialMapper admaterialMapper;
	
	public ResponseEntity  addAd(String createby, AdAddRequestBean bean) {
		Ad ad=new Ad();
		ad.setCreateBy(createby);
		ad.setUpdateBy(createby);
		ad.setName(bean.getName());
		ad.setGroupid(bean.getGroupid());
		
		int resultCount=adMapper.insert(ad);
		if(resultCount>0) {
			Map<String, Integer> result= Maps.newHashMap();
			result.put("adId", ad.getId());
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
	
	
}
