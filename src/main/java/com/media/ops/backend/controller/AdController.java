package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.controller.request.AdAddRequestBean;
import com.media.ops.backend.controller.request.AdUptRequestBean;
import com.media.ops.backend.service.AdService;
import com.media.ops.backend.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="广告操作接口",produces = "application/json")
@CrossOrigin(origins="*", maxAge=3600)
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
	public ResponseEntity del(@RequestBody Integer adId,HttpServletRequest request) {
		return adService.delAd(adId, super.getSessionUser(request).getAccount());
	}

	@ApiOperation(value = "修改广告名称操作接口",notes = "修改广告名称")
	@PostMapping(value="update_ad_name.do")	
	public ResponseEntity uptAdName(@RequestBody AdUptRequestBean bean,HttpServletRequest request) {
		return adService.uptAdName(super.getSessionUser(request).getAccount(), bean);
	}
}
