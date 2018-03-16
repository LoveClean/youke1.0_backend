package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdVo;

public interface AdService {

	public ResponseEntity  addAd(String createby, AdAddRequestBean bean);
	
	public ResponseEntity delAd(Integer adId, String updateby);
	
	public ResponseEntity  uptAdName(String updateby, AdUptRequestBean bean);
	
	public PageResponseBean<AdVo> selectAdList(PageRequestBean bean);
}
