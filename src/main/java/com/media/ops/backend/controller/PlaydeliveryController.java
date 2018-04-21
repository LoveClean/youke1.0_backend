package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PlayDeliverySearchRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.PlaydeliveryService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.PlaydeliveryDetailVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="直播投放操作接口",produces = "application/json")
@RestController
@RequestMapping("/playdelivery/")
public class PlaydeliveryController extends BaseController {

	@Autowired
	private PlaydeliveryService playdeliveryService;
	
	@ApiOperation(value = "投放直播操作接口",notes = "投放直播")
	@PostMapping(value="add_delivery.do")	
	public ResponseEntity addDelivery(@Valid @RequestBody PlaydeliveryAddRequestBean bean,HttpServletRequest request) {
				return playdeliveryService.createPlayDelivery(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "修改直播投放操作接口",notes = "修改直播投放")
	@PostMapping(value="set_delivery.do")	
	public ResponseEntity setDelivery(@Valid @RequestBody PlaydeliveryUptRequestBean bean,HttpServletRequest request) {
			return playdeliveryService.updatePlayDelivery(super.getSessionUser(request).getAccount(), bean);
	}	
	
	@ApiOperation(value = "直播投放列表接口",notes = "直播投放列表")
	@PostMapping(value="list_delivery.do")	
	public ResponseEntity<PageResponseBean<PlaydeliveryDetailVo>> getList(@RequestBody PageRequestBean bean){
		return ResponseEntityUtil.success(playdeliveryService.selectList(bean));
	}

	@ApiOperation(value = "直播投放搜索接口",notes = "直播投放搜索")
	@PostMapping(value="search_delivery.do")	
	public ResponseEntity<PageResponseBean<PlaydeliveryDetailVo>> searchList(@RequestBody PlayDeliverySearchRequestBean bean){
		return ResponseEntityUtil.success(playdeliveryService.selectDeliveryByKeys(bean));
	}
	
	@ApiOperation(value = "删除投放记录接口", notes = "删除投放记录")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delPlaydelivery(@RequestBody Integer id,HttpServletRequest request){
		return playdeliveryService.delPlaydelivery(id,super.getSessionUser(request).getAccount());
	}
}
