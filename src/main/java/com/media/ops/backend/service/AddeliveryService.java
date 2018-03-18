package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AddeliveryDetailVo;


public interface AddeliveryService {

	
	public ResponseEntity createAdDelivery(String createby, AddeliveryAddRequestBean bean);
	
	public ResponseEntity updateAdDelivery(String updateby, AddeliveryUptRequestBean bean);

	public ResponseEntity emergentAdDelivery(String account, AddeliveryEmergentRequestBean bean);
	
	public PageResponseBean<AddeliveryDetailVo> selectList(PageRequestBean bean);
}
