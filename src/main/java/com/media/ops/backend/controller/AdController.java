package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdMergeUptRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.AdService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description="广告操作接口",produces = "application/json")
@RestController
@RequestMapping("/ad/")
public class AdController extends BaseController {

	@Autowired
	private AdService adService;
	
	@ApiOperation(value = "添加广告操作接口",notes = "添加广告")
	@PostMapping(value="add_ad.do")
	public ResponseEntity add(@RequestBody AdAddRequestBean bean,HttpServletRequest request) {
		return adService.addAd(super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "删除广告操作接口",notes = "删除广告")
	@PostMapping(value="del_ad.do")	
	public ResponseEntity del(@RequestParam Integer adId,HttpServletRequest request) {
		return adService.delAd(adId, super.getSessionUser(request).getAccount());
	}

	@ApiOperation(value = "修改广告名称操作接口",notes = "修改广告名称")
	@PostMapping(value="update_ad_name.do")	
	public ResponseEntity uptAdName(@RequestBody AdUptRequestBean bean,HttpServletRequest request) {
		return adService.uptAdName(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "修改广告操作接口",notes = "修改广告")
	@PostMapping(value="update_ad.do")	
	public ResponseEntity uptAd(@RequestBody AdMergeUptRequestBean bean,HttpServletRequest request) {
		return adService.uptAd(super.getSessionUser(request).getAccount(), bean);
	}	

	@ApiOperation(value = "获取广告列表接口", notes = "广告列表")
	@PostMapping(value = "get_list.do")
	public PageResponseBean<AdVo> getList(@RequestParam(defaultValue = "1") Integer pageNum,
										  @RequestParam(defaultValue = "999") Integer pageSize) {
		return adService.selectAdList(pageNum,pageSize);
	}

	@ApiOperation(value = "搜索广告接口", notes = "广告搜索")
	@PostMapping(value = "search_list.do")
	public PageResponseBean<AdVo> searchList(@RequestParam Integer pageNum,
											 @RequestParam Integer pageSize,
											 @RequestParam(required = false) @ApiParam("开始时间") String beginTime,
											 @RequestParam(required = false) @ApiParam("结束时间") String endTime,
											 @RequestParam(required = false) String keyword,
											 @RequestParam @ApiParam("分组id") Integer groupId) {
		return adService.selectAdByKeywordGroup(keyword, groupId, pageNum, pageSize, beginTime, endTime);
	}
	
	//查看广告详情接口
	@ApiOperation(value = "根据id获取广告详情接口", notes = "根据id获取广告详情")
	@PostMapping(value = "get_ad.do")
	public ResponseEntity<AdVo> getAd(@RequestBody Integer id) {
		return adService.selectAd(id);
	}


}
