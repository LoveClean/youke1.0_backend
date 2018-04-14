package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlaydeliveryDetailVo;


public interface PlaydeliveryService {

	
	public ResponseEntity createPlayDelivery(String createby, PlaydeliveryAddRequestBean bean);
	
	public ResponseEntity updatePlayDelivery(String updateby, PlaydeliveryUptRequestBean bean);

	public PageResponseBean<PlaydeliveryDetailVo> selectList(PageRequestBean bean);
	
	public ResponseEntity<String> delPlaydelivery(Integer id,String updateby);
}
