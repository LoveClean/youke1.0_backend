package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.BatchAddeliveryAddRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.AddeliveryService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AddeliveryDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	//解决
	@ApiOperation(value = "广告投放列表接口",notes = "广告投放列表")
	@PostMapping(value="list_delivery.do")	
	public PageResponseBean<AddeliveryDetailVo> getList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
		return addeliveryService.selectList(pageNum,pageSize);
	}
	//解决
	@ApiOperation(value = "广告投放搜索接口",notes = "广告投放搜索")
	@PostMapping(value="search_delivery.do")	
	public PageResponseBean<AddeliveryDetailVo> searchList(@RequestParam(required = false) @ApiParam("城市ID") String cityId,
														   @RequestParam(required = false) @ApiParam("区域ID") String areaId,
														   @RequestParam @ApiParam("投放类型 0设备 1楼宇") Integer deliveryType ,
														   @RequestParam @ApiParam("楼宇/设备分组id") Integer groupId,
														   @RequestParam Integer pageNum, @RequestParam Integer pageSize){
		return addeliveryService.selectDeliveryByKeys(cityId,areaId,deliveryType,groupId,pageNum,pageSize);
	}
	
	@ApiOperation(value = "删除投放记录接口", notes = "删除投放记录")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delAddelivery(@RequestParam String id,HttpServletRequest request){
		return addeliveryService.delAddelivery(Integer.parseInt(id),super.getSessionUser(request).getAccount());
	}

	@ApiOperation(value = "批量投放广告操作接口",notes = "批量投放广告")
	@PostMapping(value="batch_add_delivery.do")
	public ResponseEntity batchAddDelivery(@Valid @RequestBody BatchAddeliveryAddRequestBean bean, HttpServletRequest request) {
		return addeliveryService.BatchAddelivery(bean,super.getSessionUser(request).getAccount());
	}


	@ApiOperation(value = "广告投放搜索接口2",notes = "广告投放搜索2")
	@PostMapping(value="search_delivery2.do")
	public PageResponseBean<AddeliveryDetailVo> searchList2(
			@RequestParam(required = false) @ApiParam("区域ID") String areaId,
			@RequestParam(required = false,defaultValue = "2") @ApiParam("投放类型 0设备 1楼宇") Integer deliveryType,
			@RequestParam(required = false,defaultValue = "0") Integer groupId,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize){
		return addeliveryService.selectDeliveryByKeys2(areaId,deliveryType,groupId,pageNum,pageSize);
	}

	@ApiOperation(value = "批量删除投放记录接口", notes = "批量删除投放记录")
	@PostMapping(value = "batch_delete.do")
	public ResponseEntity<String> batchDeleteDelivery(@RequestParam String ids,HttpServletRequest request){
		return addeliveryService.BatchDeldelivery(ids,super.getSessionUser(request).getAccount());
	}


}
