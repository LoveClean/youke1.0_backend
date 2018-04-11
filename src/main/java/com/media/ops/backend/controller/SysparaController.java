package com.media.ops.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.controller.request.SysparaAddRequestBean;
import com.media.ops.backend.controller.request.SysparaUptRequestBean;
import com.media.ops.backend.service.SysparaService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.SysparaVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统参数设置接口",produces = "application/json")
@RestController
@RequestMapping("/syspara/")
public class SysparaController extends BaseController {

	@Autowired
	private SysparaService sysparaService;

	@ApiOperation(value = "添加参数操作接口",notes = "添加参数")
	@PostMapping(value="add.do")	
	public ResponseEntity add(@RequestBody SysparaAddRequestBean bean,HttpServletRequest request) {
		return sysparaService.addSyspara(super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "删除参数操作接口",notes = "删除参数")
	@PostMapping(value="del.do")	
	public ResponseEntity del(@RequestBody Integer id,HttpServletRequest request) {
		return sysparaService.delSyspara(super.getSessionUser(request).getAccount(), id);
	}
	
	@ApiOperation(value = "修改参数操作接口",notes = "修改参数")
	@PostMapping(value="update.do")	
	public ResponseEntity update(@RequestBody SysparaUptRequestBean bean,HttpServletRequest request) {
		return sysparaService.uptSyspara(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "获取参数列表接口", notes = "参数列表")
	@PostMapping(value = "get_list.do")
	public ResponseEntity<List<SysparaVo>> getList(){
		return sysparaService.selectAll();
	}
	
	@ApiOperation(value = "根据name获取参数接口", notes = "查询参数")
	@PostMapping(value = "get_syspara.do")
	public ResponseEntity<SysparaVo> getSysparaByName(@RequestBody String name){
		return sysparaService.selectByName(name);
	}
}
