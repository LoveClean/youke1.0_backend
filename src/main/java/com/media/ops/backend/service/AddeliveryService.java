package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.BatchAddeliveryAddRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AddeliveryDetailVo;


public interface AddeliveryService {


    ResponseEntity createAdDelivery(String createby, AddeliveryAddRequestBean bean);

    ResponseEntity updateAdDelivery(String updateby, AddeliveryUptRequestBean bean);

    ResponseEntity emergentAdDelivery(String account, AddeliveryEmergentRequestBean bean);

    PageResponseBean<AddeliveryDetailVo> selectList(Integer pageNum, Integer pageSize);

    ResponseEntity<String> delAddelivery(Integer id, String updateby);

    PageResponseBean<AddeliveryDetailVo> selectDeliveryByKeys(String cityId, String areaId, Integer deliveryType,
                                                              Integer groupId, Integer pageNum, Integer pageSize);

    ResponseEntity<String> BatchAddelivery(BatchAddeliveryAddRequestBean bean, String createby);

    ResponseEntity<String> BatchDeldelivery(String ids, String updateby);

    PageResponseBean<AddeliveryDetailVo> selectDeliveryByKeys2(String areaId, Integer deliveryType, Integer groupId,
                                                               Integer pageNum, Integer pageSize);


}
