package com.media.ops.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.controller.request.BuildingAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorUptRequestBean;
import com.media.ops.backend.controller.request.BuildingSearchRequestBean;
import com.media.ops.backend.controller.request.BuildingUptRequestBean;
import com.media.ops.backend.controller.request.FloorDeviceAddRequestBean;
import com.media.ops.backend.controller.request.FloorDeviceUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.BuildingService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AreaBuildingVo;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;
import com.media.ops.backend.vo.FloorDeviceVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="楼宇操作接口描述",produces = "application/json")
@RestController
@RequestMapping("/building/")
public class BuildingController extends BaseController{
	@Autowired
	private BuildingService buildingService;
	
	@ApiOperation(value = "获取楼宇列表接口", notes = "楼宇列表")
	@PostMapping(value = "get_building_list.do")
	public ResponseEntity<PageResponseBean<BuildingVo>> getBuildingList(@RequestBody PageRequestBean bean) {
		return ResponseEntityUtil.success(buildingService.selectList(bean));
	}
	
	@ApiOperation(value = "根据id查询楼宇接口", notes = "查询楼宇")
	@PostMapping(value = "get_building.do")
	public ResponseEntity<BuildingVo> getBuilding(@RequestBody Integer buildingId ){
		return buildingService.selectBuildingById(buildingId);
	}
	@ApiOperation(value = "根据areaId查询楼宇列表接口", notes = "查询区域内楼宇列表")
	@PostMapping(value = "get_building_areaId.do")
	public ResponseEntity<List<AreaBuildingVo>> getBuildingByAreaId(@RequestBody String areaId ){
		return buildingService.selectBuildingByAreaId(areaId);
	}
	
	@ApiOperation(value = "根据areaId,楼宇名称查询楼宇列表接口", notes = "根据区域、名称查询楼宇列表")
	@PostMapping(value = "get_building_areaId_name.do")
	public ResponseEntity getBuildingByAreaIdBuildingName(@RequestBody BuildingSearchRequestBean bean){
		return buildingService.selectBuildingsbyKey(bean);
	}
	
	@ApiOperation(value = "添加楼宇操作接口",notes = "添加楼宇")
	@PostMapping(value="addBuilding.do")
	public ResponseEntity addBuilding(@RequestBody BuildingAddRequestBean bean,HttpServletRequest request) {
		return buildingService.createBuilding(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "修改楼宇操作接口",notes = "修改楼宇信息")
	@PostMapping(value="uptBuilding.do")
	public ResponseEntity uptBuilding(@RequestBody BuildingUptRequestBean bean,HttpServletRequest request) {
		return buildingService.updateBuilding (super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "删除楼宇操作接口",notes = "删除楼宇信息")
	@PostMapping(value="delBuilding.do")	
	public ResponseEntity delBuilding(@RequestBody Integer buildingId,HttpServletRequest request) {
		return buildingService.deleteBuilding(super.getSessionUser(request).getAccount(), buildingId);
	}
	///////////////////////////////楼层操作///////////////////////////////////////////////
	
	@ApiOperation(value = "添加楼层操作接口",notes = "添加楼层")
	@PostMapping(value="addFloor.do")
	public ResponseEntity addFloor(@RequestBody BuildingFloorAddRequestBean bean,HttpServletRequest request) {
		return buildingService.createBuildingFloor(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "修改楼层操作接口",notes = "修改楼层信息")
	@PostMapping(value="uptFloor.do")
	public ResponseEntity uptFloor(@RequestBody BuildingFloorUptRequestBean bean,HttpServletRequest request) {
		return buildingService.updateBuildingFloor(super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "根据id删除楼层操作接口",notes = "根据id删除楼层信息")
	@PostMapping(value="delFloor.do")	
	public ResponseEntity delFloor(@RequestBody Integer floorId,HttpServletRequest request) {
		return buildingService.delBuildingFloor(super.getSessionUser(request).getAccount(), floorId);
	}	
	
	@ApiOperation(value = "根据楼宇ID获取楼层列表接口", notes = "根据楼宇ID获取楼层列表")
	@PostMapping(value = "get_floor_list.do")
	public ResponseEntity<List<BuildingFloorVo>> getFloorList(@RequestBody Integer buildingId) {
		return buildingService.selectFloorsByBuildingId(buildingId);
	}
	
	@ApiOperation(value = "根据id查询楼层接口", notes = "查询楼层信息")
	@PostMapping(value = "get_floor.do")
	public ResponseEntity<BuildingFloorVo> getFloor(@RequestBody Integer id ){
		return buildingService.selectFloorById(id);
	}
///////////////////////////////////////楼层设备操作///////////////////////////////////////////////////////	
	@ApiOperation(value = "添加楼层设备操作接口",notes = "添加楼层设备")
	@PostMapping(value="addFloorDevice.do")
	public ResponseEntity addFloorDevice(@RequestBody FloorDeviceAddRequestBean bean,HttpServletRequest request) {
		return buildingService.addFloorDevice(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "修改楼层设备操作接口",notes = "修改楼层设备信息")
	@PostMapping(value="uptFloorDevice.do")
	public ResponseEntity uptFloorDevice(@RequestBody FloorDeviceUptRequestBean bean,HttpServletRequest request) {
		return buildingService.updateFloorDevice(super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "根据id删除楼层设备操作接口",notes = "根据id删除楼层设备信息")
	@PostMapping(value="delFloorDevice.do")	
	public ResponseEntity delFloorDevice(@RequestBody Integer id,HttpServletRequest request) {
		return buildingService.delFloorDevice(super.getSessionUser(request).getAccount(), id);
	}
	
	@ApiOperation(value = "删除某楼层所有设备操作接口",notes = "删除某楼层所有设备信息")
	@PostMapping(value="del_device_floorid.do")	
	public ResponseEntity delDeviceByFloorId(@RequestBody Integer floorId,HttpServletRequest request) {
		return buildingService.delDevicesByFloorId(super.getSessionUser(request).getAccount(), floorId);
	}	
	
	@ApiOperation(value = "根据楼层ID获取楼层设备列表接口", notes = "根据楼层ID获取楼层设备列表")
	@PostMapping(value = "get_device_list.do")
	public ResponseEntity<List<FloorDeviceVo>> getDeviceList(@RequestBody Integer floorId) {
		return buildingService.selecDevicesByFloorId(floorId);
	}
	
	@ApiOperation(value = "根据id查询楼层设备接口", notes = "根据id查询楼层设备信息")
	@PostMapping(value = "get_Device.do")
	public ResponseEntity<FloorDeviceVo> getFloorDevice(@RequestBody Integer id ){
		return buildingService.selectFloorDeviceById(id);
	}
}
