package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.DeviceService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "设备管理接口", produces = "application/json")
@RestController
@RequestMapping("/device/")
public class DeviceController extends BaseController {
	@Autowired
	private DeviceService deviceService;
	//解决
	@ApiOperation(value = "获取设备列表接口", notes = "设备列表")
	@PostMapping(value = "get_list.do")
	public PageResponseBean<DeviceListVo> getList(@RequestParam(defaultValue = "1") Integer pageNum,
												  @RequestParam(defaultValue = "999") Integer pageSize) {
		return deviceService.selectDeviceList(pageNum,pageSize);
	}
	//解决
	@ApiOperation(value = "设备搜索操作接口", notes = "设备搜索")
	@PostMapping(value = "search.do")
	public PageResponseBean<DeviceListVo> searchDevice(@RequestParam(required = false) @ApiParam("设备编号") String code, @RequestParam(required = false) @ApiParam("城市ID") String cityId,
													   @RequestParam(required = false) @ApiParam("区域ID") String areaId, @RequestParam(required = false) @ApiParam("地址关键词") String address,
													   @RequestParam @ApiParam("楼宇id") Integer buildingId, @RequestParam @ApiParam("设备分组id") Integer groupId,
													   @RequestParam(defaultValue = "1") Integer pageNum,
													   @RequestParam(defaultValue = "999") Integer pageSize) {
		return deviceService.selectDeviceByCodeAreaAddress(code,cityId,areaId,address,buildingId,groupId,pageNum,pageSize);
	}

	@ApiOperation(value = "设备查询操作接口", notes = "设备查询")
	@PostMapping(value = "get.do")
	public ResponseEntity<DeviceVo> getOne(@RequestBody Integer id) {
		return deviceService.selectDevice(id);
	}

	@ApiOperation(value = "设备解除绑定接口", notes = "设备解除绑定")
	@PostMapping(value = "unbound.do")
	public ResponseEntity unbound(@RequestParam Integer id, HttpServletRequest request) {
		return deviceService.unboundDevice(id, super.getSessionUser(request).getAccount());
	}

	@ApiOperation(value = "添加设备接口", notes = "设备新增")
	@PostMapping(value = "add.do")
	public ResponseEntity addDevice(@RequestBody DeviceAddRequestBean bean, HttpServletRequest request) {
		return deviceService.addDevice(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "通过Excel批量上传设备", notes = "批量新增设备")
	@PostMapping(value = "batchAddDevice.do")
	public ResponseEntity batchAddDevice(@RequestBody List<DeviceAddRequestBean> beans, HttpServletRequest request) {
		int success=0, fail=0;
		for (DeviceAddRequestBean bean : beans) {
			ResponseEntity  response= deviceService.addDevice(super.getSessionUser(request).getAccount(), bean);
			if(response.isSuccess()) {
				success++;
			}
			else {
				fail++;
			}
		}
		return ResponseEntityUtil.success("设备批量操作结果：成功"+success+"条，失败"+fail+"条");

	}

	@ApiOperation(value = "修改设备信息接口", notes = "设备编辑")
	@PostMapping(value = "update.do")
	public ResponseEntity uptDevice(@RequestBody DeviceUptRequestBean bean, HttpServletRequest request) {
		return deviceService.uptDevice(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "删除设备接口", notes = "设备删除")
	@PostMapping(value = "delete.do")
	public ResponseEntity<String> delDevice(@RequestParam Integer id, HttpServletRequest request) {
		return deviceService.delDevice(id, super.getSessionUser(request).getAccount());
	}
}
