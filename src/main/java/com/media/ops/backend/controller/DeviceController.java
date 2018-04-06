package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceSearchRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.DeviceService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "设备管理接口", produces = "application/json")
@RestController
@RequestMapping("/device/")
public class DeviceController extends BaseController {
	@Autowired
	private DeviceService deviceService;

	@ApiOperation(value = "获取设备列表接口", notes = "设备列表")
	@PostMapping(value = "get_list.do")
	public ResponseEntity<PageResponseBean<DeviceListVo>> getList(@RequestBody PageRequestBean bean) {
		return ResponseEntityUtil.success(deviceService.selectDeviceList(bean));
	}

	@ApiOperation(value = "设备搜索操作接口", notes = "设备搜索")
	@PostMapping(value = "search.do")
	public ResponseEntity<PageInfo> searchDevice(@RequestBody DeviceSearchRequestBean bean) {
		return deviceService.selectDeviceByCodeAreaAddress(bean);
	}
	@ApiOperation(value = "设备查询操作接口", notes = "设备查询")
	@PostMapping(value = "get.do")
	public ResponseEntity<DeviceVo> getOne(@RequestBody Integer id) {
		return deviceService.selectDevice(id);
	}
	
	@ApiOperation(value = "设备解除绑定接口", notes = "设备解除绑定")
	@PostMapping(value = "unbound.do")
	public ResponseEntity unbound(@RequestBody Integer id,HttpServletRequest request) {
		return deviceService.unboundDevice(id,super.getSessionUser(request).getAccount());
	}	
	
	@ApiOperation(value = "添加设备接口", notes = "设备新增")
	@PostMapping(value = "add.do")
	public ResponseEntity addDevice(@RequestBody DeviceAddRequestBean bean,HttpServletRequest request){
		return deviceService.addDevice(super.getSessionUser(request).getAccount(),bean);
	}
	@ApiOperation(value = "修改设备信息接口", notes = "设备编辑")
	@PostMapping(value = "update.do")
	public ResponseEntity uptDevice(@RequestBody DeviceUptRequestBean bean,HttpServletRequest request){
		return deviceService.uptDevice(super.getSessionUser(request).getAccount(),bean);
	}
	@ApiOperation(value = "删除设备接口", notes = "设备删除")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delDevice(@RequestBody Integer id,HttpServletRequest request){
		return deviceService.delDevice(id,super.getSessionUser(request).getAccount());
	}
}
