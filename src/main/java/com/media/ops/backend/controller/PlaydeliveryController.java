package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.BatchAddPlayDeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.PlaydeliveryService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlaydeliveryDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description="直播投放操作接口",produces = "application/json")
@RestController
@RequestMapping("/playdelivery/")
public class PlaydeliveryController extends BaseController {

	@Autowired
	private PlaydeliveryService playdeliveryService;
	
	@ApiOperation(value = "投放直播操作接口",notes = "投放直播")
	@PostMapping(value="add_delivery.do")	
	public ResponseEntity addDelivery(@Valid @RequestBody PlaydeliveryAddRequestBean bean, HttpServletRequest request) {
				return playdeliveryService.createPlayDelivery(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "修改直播投放操作接口",notes = "修改直播投放")
	@PostMapping(value="set_delivery.do")	
	public ResponseEntity setDelivery(@Valid @RequestBody PlaydeliveryUptRequestBean bean, HttpServletRequest request) {
			return playdeliveryService.updatePlayDelivery(super.getSessionUser(request).getAccount(), bean);
	}	
	
	@ApiOperation(value = "直播投放列表接口",notes = "直播投放列表")
	@PostMapping(value="list_delivery.do")	
	public PageResponseBean<PlaydeliveryDetailVo> getList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "999") Integer pageSize){
		PageResponseBean<PlaydeliveryDetailVo> page=playdeliveryService.selectList(pageNum,pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}

	@ApiOperation(value = "直播投放搜索接口",notes = "直播投放搜索")
	@PostMapping(value="search_delivery.do")	
	public PageResponseBean<PlaydeliveryDetailVo> searchList(@RequestParam(required = false) @ApiParam("城市id") String cityId,
															 @RequestParam(required = false) @ApiParam("区域id") String areaId,
															 @RequestParam(required = false) @ApiParam("投放类型 0设备 1楼宇") Integer deliveryType,
															 @RequestParam(required = false) @ApiParam("楼宇/设备分组id") Integer groupId,
															 @RequestParam(defaultValue = "1") int pageNum,
															 @RequestParam(defaultValue = "999") int pageSize){
		PageResponseBean<PlaydeliveryDetailVo> page= playdeliveryService.selectDeliveryByKeys(cityId,areaId,deliveryType,groupId,pageNum,pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}
	
	@ApiOperation(value = "删除投放记录接口", notes = "删除投放记录")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delPlaydelivery(@RequestParam String id, HttpServletRequest request){
		return playdeliveryService.delPlaydelivery(Integer.parseInt(id),super.getSessionUser(request).getAccount());
	}

    @ApiOperation(value = "批量投放直播操作接口",notes = "批量投放直播")
    @PostMapping(value="batch_add_delivery.do")
    public ResponseEntity batchAddDelivery(@Valid @RequestBody BatchAddPlayDeliveryAddRequestBean bean, HttpServletRequest request) {
        return playdeliveryService.batchAddPlayDelivery(super.getSessionUser(request).getAccount(), bean);
    }
    @ApiOperation(value = "直播投放搜索2接口",notes = "直播投放搜索2")
    @PostMapping(value="search_delivery2.do")
    public PageResponseBean<PlaydeliveryDetailVo> searchList2(
            @RequestParam(required = false) @ApiParam("区域id") String areaId,
            @RequestParam(required = false,defaultValue = "2") @ApiParam("投放类型 0设备 1楼宇") Integer deliveryType,
            @RequestParam(required = false,defaultValue = "0") @ApiParam("楼宇/设备分组id") Integer groupId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "999") int pageSize){
        PageResponseBean<PlaydeliveryDetailVo> page= playdeliveryService.selectDeliveryByKeys2(areaId,deliveryType,groupId,pageNum,pageSize);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
    @ApiOperation(value = "批量删除投放记录接口", notes = "批量删除投放记录")
    @PostMapping(value = "batch_delete.do")
    public ResponseEntity<String> batchDelPlaydelivery(@RequestParam String ids, HttpServletRequest request){
        return playdeliveryService.batchDelPlaydelivery(ids,super.getSessionUser(request).getAccount());
    }

}
