package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.util.ResponseEntity;

public interface AdService {

	public ResponseEntity  addAd(String createby, AdAddRequestBean bean);
	
	public ResponseEntity delAd(Integer adId, String updateby);
	
	public ResponseEntity  uptAdName(String updateby, AdUptRequestBean bean);
}
