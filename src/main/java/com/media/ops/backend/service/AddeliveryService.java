package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.util.ResponseEntity;

public interface AddeliveryService {

	
	public ResponseEntity createAdDelivery(String createby, AddeliveryAddRequestBean bean);
	
	public ResponseEntity updateAdDelivery(String updateby, AddeliveryUptRequestBean bean);
}
