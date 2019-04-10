package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.BatchAddPlayDeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlaydeliveryDetailVo;


public interface PlaydeliveryService {

	
	 ResponseEntity createPlayDelivery(String createby, PlaydeliveryAddRequestBean bean);
	
	 ResponseEntity updatePlayDelivery(String updateby, PlaydeliveryUptRequestBean bean);

	 PageResponseBean<PlaydeliveryDetailVo> selectList(Integer pageNum, Integer pageSize);

	 ResponseEntity<String> delPlaydelivery(Integer id, String updateby);

	 PageResponseBean<PlaydeliveryDetailVo> selectDeliveryByKeys(String cityId, String areaId, Integer deliveryType, Integer groupId, int pageNum, int pageSize);

	 ResponseEntity batchAddPlayDelivery(String createby, BatchAddPlayDeliveryAddRequestBean bean);

	 ResponseEntity<String> batchDelPlaydelivery(String ids, String updateby);

	 PageResponseBean<PlaydeliveryDetailVo> selectDeliveryByKeys2(String areaId, Integer deliveryType, Integer groupId, int pageNum, int pageSize);

}
