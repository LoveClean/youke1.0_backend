package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.controller.request.MaterialUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.MaterialService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.MaterialVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "素材管理接口", produces = "application/json")

@RestController
@RequestMapping("/material/")
public class MaterialController extends BaseController {

	@Autowired
	private MaterialService materialService;

	@ApiOperation(value = "获取素材列表", notes = "素材列表")
	@PostMapping(value = "get_list.do")
	public PageResponseBean<MaterialVo> getList(@RequestParam(defaultValue = "1") int pageNum,
                                                @RequestParam(defaultValue = "999") int pageSize) {
		PageResponseBean page=materialService.selectMaterialList(pageNum,pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}
	
	@ApiOperation(value = "根据id获取素材列表", notes = "根据id获取素材列表")
	@PostMapping(value = "get_list_ids.do")
	public PageResponseBean<MaterialVo> getListByIds(@RequestParam(required = false) @ApiParam("素材id，逗号分隔") String ids,
                                                     @RequestParam(defaultValue = "1") int pageNum,
                                                     @RequestParam(defaultValue = "999") int pageSize) {
		PageResponseBean page=materialService.selectMaterialListByIds(ids,pageNum,pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}

	@ApiOperation(value = "搜索素材接口", notes = "搜索素材")
	@PostMapping(value = "search.do")
	public PageResponseBean<MaterialVo> searchMaterial(@RequestParam(required = false) @ApiParam("素材分组") Integer groupId,
                                                       @RequestParam(required = false) @ApiParam("素材类型") String type,
                                                       @RequestParam(required = false) @ApiParam("关键词") String keyword,
                                                       @RequestParam(defaultValue = "1")int pageNum,
                                                       @RequestParam(defaultValue = "999")int pageSize) {
		PageResponseBean page= materialService.selectMaterialByKeywordTypeGroup(keyword,
			type,groupId, pageNum, pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}
	@ApiOperation(value = "添加素材接口", notes = "添加素材")
	@PostMapping(value = "add.do")	
	public ResponseEntity addMaterial(@RequestBody MaterialAddRequestBean bean, HttpServletRequest request ){
		return materialService.addMaterial(super.getSessionUser(request).getAccount(),bean);
	}
	
	@ApiOperation(value = "修改素材接口", notes = "修改素材")
	@PostMapping(value = "update.do")	
	public ResponseEntity<String> uptMaterial(@RequestBody MaterialUptRequestBean bean, HttpServletRequest request ){
		return materialService.uptMaterial(super.getSessionUser(request).getAccount(),bean);
	}
	
	@ApiOperation(value = "删除素材接口", notes = "删除素材")
	@PostMapping(value = "delete.do")	
	public ResponseEntity<String> delMaterial(@RequestParam Integer id, HttpServletRequest request){
		return materialService.delMaterial(super.getSessionUser(request).getAccount(),id);
	}

	@ApiOperation(value = "获取音频素材列表", notes = "音频素材列表")
	@PostMapping(value = "get_music_list.do")
	public ResponseEntity<List<MaterialVo>> getMusicList() {
		return materialService.selectMaterialMusicList();
	}


	@ApiOperation(value = "获取素材列表除去音频", notes = "素材列表除去音频")
	@PostMapping(value = "get_list2.do")
	public PageResponseBean<MaterialVo> getList2(@RequestParam(defaultValue = "1") int pageNum,
												 @RequestParam(defaultValue = "999") int pageSize) {
		PageResponseBean page=materialService.selectMaterialListExMu(pageNum,pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}

	@ApiOperation(value = "搜索素材接口除去音频", notes = "搜索素材除去音频")
	@PostMapping(value = "search2.do")
	public PageResponseBean<MaterialVo> searchMaterial2(@RequestParam(required = false) @ApiParam("素材分组") Integer groupId,
														@RequestParam(required = false) @ApiParam("素材类型") String type,
														@RequestParam(required = false) @ApiParam("关键词") String keyword,
														@RequestParam(defaultValue = "1")int pageNum,
														@RequestParam(defaultValue = "999")int pageSize) {
		PageResponseBean page= materialService.selectMaterialByKeywordTypeGroupExMu(keyword,
				type,groupId, pageNum, pageSize);
		page.setCode(0);
		page.setHttpStatus(200);
		return page;
	}
}
