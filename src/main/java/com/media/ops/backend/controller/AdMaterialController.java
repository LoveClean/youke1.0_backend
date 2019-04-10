package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.controller.request.AdMaterialUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.AdMaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdMaterialVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description="广告素材操作接口",produces = "application/json")
@RestController
@RequestMapping("/admaterial/")
public class AdMaterialController extends BaseController {
    
	@Autowired
	private AdMaterialService adMaterialService;
	
	@ApiOperation(value = "添加广告素材操作接口",notes = "添加广告素材")
	@PostMapping(value="add_admaterial.do")
	public ResponseEntity add(@RequestBody AdMaterialAddRequestBean bean,HttpServletRequest request) {
		return adMaterialService.addAdMaterial(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "批量添加广告素材操作接口",notes = "批量添加广告素材")
	@PostMapping(value="batch_add_admaterial.do")
	public ResponseEntity batchAdd(@RequestBody List<AdMaterialAddRequestBean> beans,HttpServletRequest request) {
		return adMaterialService.batchInsertAdmaterial(super.getSessionUser(request).getAccount(), beans);
	}
	
	@ApiOperation(value = "根据id删除广告素材操作接口",notes = "根据id删除广告素材")
	@PostMapping(value="del_admaterial_id.do")	
	public ResponseEntity delbyId(@RequestBody Integer admaterialId,HttpServletRequest request) {
		return adMaterialService.delAdMaterial(admaterialId, super.getSessionUser(request).getAccount());
	}

	@ApiOperation(value = "根据广告id删除素材操作接口",notes = "根据广告id删除素材")
	@PostMapping(value="del_admaterial_adId.do")	
	public ResponseEntity delByAdId(@RequestBody Integer adId,HttpServletRequest request) {
		return adMaterialService.delAdMaterialByAdId(adId, super.getSessionUser(request).getAccount());
	}
	
	@ApiOperation(value = "修改广告素材操作接口",notes = "修改广告素材名称")
	@PostMapping(value="update_admaterial.do")	
	public ResponseEntity uptAdmaterial(@RequestBody AdMaterialUptRequestBean bean,HttpServletRequest request) {
		return adMaterialService.updateAdMaterial(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "批量修改广告素材操作接口",notes = "批量修改广告素材名称")
	@PostMapping(value="batch_update_admaterial.do")	
	public ResponseEntity batchUpdateAdmaterial(@RequestBody List<AdMaterialUptRequestBean> beans,HttpServletRequest request) {
		return adMaterialService.batchUpdateAdMaterial(super.getSessionUser(request).getAccount(), beans);
	}
	//解决
	@ApiOperation(value = "根据广告Id获取素材接口", notes = "根据广告Id获取素材")
	@PostMapping(value = "get_list_adId.do")
	public PageResponseBean<AdMaterialVo> getListByAdId(@RequestParam Integer adId, @RequestParam Integer pageNum,
														@RequestParam Integer PageSize) {
//		if(adId==null) {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
//		}
		return adMaterialService.selectListByAdId(adId,pageNum,PageSize);
	}
}
