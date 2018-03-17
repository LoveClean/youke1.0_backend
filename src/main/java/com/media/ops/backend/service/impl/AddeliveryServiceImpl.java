package com.media.ops.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.dao.entity.Addelivery;
import com.media.ops.backend.dao.mapper.AddeliveryMapper;
import com.media.ops.backend.service.AddeliveryService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;


@Service
public class AddeliveryServiceImpl implements AddeliveryService {

	@Autowired
	private AddeliveryMapper addeliveryMapper;
	
	public ResponseEntity createAdDelivery(String createby, AddeliveryAddRequestBean bean) {
		
		Addelivery addelivery =new Addelivery();
		addelivery.setAdid(bean.getAdid());
		addelivery.setAdtype(bean.getAdtype());
		addelivery.setDelivertype(bean.getDelivertype());
		addelivery.setAreaid(bean.getAreaid());
		addelivery.setGroupid(bean.getGroupid());
		addelivery.setBegintime(bean.getBegintime());
		addelivery.setEndtime(bean.getEndtime());
		addelivery.setCreateBy(createby);
		addelivery.setUpdateBy(createby);
		
		int resultCount= addeliveryMapper.insertSelective(addelivery);
		return ResponseEntityUtil.addMessage(resultCount);
	}
	
	public ResponseEntity updateAdDelivery(String updateby, AddeliveryUptRequestBean bean) {
        Addelivery uptAddelivery= new Addelivery();
        uptAddelivery.setId(bean.getId());
        uptAddelivery.setAdtype(bean.getAdtype());
        uptAddelivery.setDelivertype(bean.getDelivertype());
        uptAddelivery.setAreaid(bean.getAreaid());
        uptAddelivery.setGroupid(bean.getGroupid());
        uptAddelivery.setBegintime(bean.getBegintime());
        uptAddelivery.setEndtime(bean.getEndtime());
        
        int resultCount= addeliveryMapper.updateByPrimaryKeySelective(uptAddelivery);
       
        return ResponseEntityUtil.updMessage(resultCount);
	}
	
}
