package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.controller.request.AdDeliverySearchRequestBean;
import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.AddeliveryService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AddeliveryDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="广告投放操作接口",produces = "application/json")
@RestController
@RequestMapping("/addelivery/")
public class AddeliveryController extends BaseController {

	@Autowired
	private AddeliveryService addeliveryService;
	
	@ApiOperation(value = "投放广告操作接口",notes = "投放广告")
	@PostMapping(value="add_delivery.do")	
	public ResponseEntity addDelivery(@Valid @RequestBody AddeliveryAddRequestBean bean,HttpServletRequest request) {
				return addeliveryService.createAdDelivery(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "修改广告投放属性操作接口",notes = "修改广告投放属性")
	@PostMapping(value="set_delivery.do")	
	public ResponseEntity setDelivery(@Valid @RequestBody AddeliveryUptRequestBean bean,HttpServletRequest request) {
			return addeliveryService.updateAdDelivery(super.getSessionUser(request).getAccount(), bean);
	}	
	
	@ApiOperation(value = "强制投放操作接口",notes = "强制投放广告")
	@PostMapping(value="emergent_delivery.do")	
	public ResponseEntity emergentDelivery(@Valid @RequestBody AddeliveryEmergentRequestBean bean,HttpServletRequest request) {
			return addeliveryService.emergentAdDelivery(super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "广告投放列表接口",notes = "广告投放列表")
	@PostMapping(value="list_delivery.do")	
	public ResponseEntity<PageResponseBean<AddeliveryDetailVo>> getList(@RequestBody PageRequestBean bean){
		return ResponseEntityUtil.success(addeliveryService.selectList(bean));
	}
	
	@ApiOperation(value = "广告投放搜索接口",notes = "广告投放搜索")
	@PostMapping(value="search_delivery.do")	
	public ResponseEntity<PageResponseBean<AddeliveryDetailVo>> searchList(@RequestBody AdDeliverySearchRequestBean bean){
		return ResponseEntityUtil.success(addeliveryService.selectDeliveryByKeys(bean));
	}
	
	@ApiOperation(value = "删除投放记录接口", notes = "删除投放记录")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delAddelivery(@RequestBody Integer id,HttpServletRequest request){
		return addeliveryService.delAddelivery(id,super.getSessionUser(request).getAccount());
	}
}
