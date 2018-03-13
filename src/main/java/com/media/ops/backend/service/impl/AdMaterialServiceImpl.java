package com.media.ops.backend.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Maps;
import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.dao.entity.Admaterial;
import com.media.ops.backend.dao.mapper.AdmaterialMapper;
import com.media.ops.backend.service.AdMaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

@Service
public class AdMaterialServiceImpl implements AdMaterialService {

	@Autowired
	private AdmaterialMapper admaterialMapper;
	
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
			Map<String, Integer> result=Maps.newHashMap();
			result.put("admaterialId", admaterial.getId());
			return ResponseEntityUtil.success(result);
		}
		return ResponseEntityUtil.fail("素材插入失败");
	}
}
