package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.controller.request.MaterialQueryRequestBean;
import com.media.ops.backend.controller.request.MaterialUptRequestBean;
import com.media.ops.backend.controller.request.MaterialSearchRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.MaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.MaterialVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "素材管理接口", produces = "application/json")
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/material/")
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	@ApiOperation(value = "获取素材列表", notes = "素材列表")
	@PostMapping(value = "get_list.do")
	public ResponseEntity<PageResponseBean<MaterialVo>> getList(@RequestBody PageRequestBean bean) {
		return ResponseEntityUtil.success(materialService.selectMaterialList(bean));
	}
	
	@ApiOperation(value = "根据id获取素材列表", notes = "根据id获取素材列表")
	@PostMapping(value = "get_list_ids.do")
	public ResponseEntity<PageResponseBean<MaterialVo>> getListByIds(@RequestBody MaterialQueryRequestBean bean) {
		return ResponseEntityUtil.success(materialService.selectMaterialListByIds(bean));
	}

	@ApiOperation(value = "搜索素材接口", notes = "搜索素材")
	@PostMapping(value = "search.do")
	public ResponseEntity<PageResponseBean<MaterialVo>> searchMaterial(
			@Valid @RequestBody MaterialSearchRequestBean bean) {
		return ResponseEntityUtil.success(materialService.selectMaterialByKeywordTypeGroup(bean.getKeyword(),
			bean.getType(),bean.getGroupId(), bean.getPageNum(), bean.getPageSize()));
	}
	@ApiOperation(value = "添加素材接口", notes = "添加素材")
	@PostMapping(value = "add.do")	
	public ResponseEntity<String> addMaterial( @RequestBody MaterialAddRequestBean bean ){
		return materialService.addMaterial(bean);
	}
	
	@ApiOperation(value = "修改素材接口", notes = "修改素材")
	@PostMapping(value = "update.do")	
	public ResponseEntity<String> uptMaterial( @RequestBody MaterialUptRequestBean bean ){
		return materialService.uptMaterial(bean);
	}
	
	@ApiOperation(value = "删除素材接口", notes = "删除素材")
	@PostMapping(value = "delete.do")	
	public ResponseEntity<String> delMaterial( @RequestBody Integer id){
		return materialService.delMaterial(id);
	}
}
