package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.*;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Building;
import com.media.ops.backend.service.BuildingService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;
import com.media.ops.backend.vo.FloorDeviceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description="楼宇操作接口描述",produces = "application/json")
@RestController
@RequestMapping("/building/")
public class BuildingController extends BaseController{
	
	@Autowired
	private BuildingService buildingService;

	@ApiOperation(value = "获取楼宇列表接口", notes = "楼宇列表")
	@PostMapping(value = "get_building_list.do")
	public PageResponseBean<BuildingVo> getBuildingList(@RequestParam(defaultValue = "1") Integer pageNum,
														@RequestParam(defaultValue = "999") Integer pageSize) {
		return buildingService.selectList(pageNum,pageSize);
	}

	@ApiOperation(value = "获取所有楼宇列表(Tree)", notes = "获取所有楼宇列表,按省市区封装为树形结构")
	@GetMapping(value = "get_building_list2.do")
	public Map<String, Object> getBuildingList2(){
		Map map = new HashMap();
		map.put("code",0);
		map.put("data",buildingService.selectList2());
		return map;
	}

	@ApiOperation(value = "获取有楼宇的省市区(Tree)", notes = "获取所有“下设有楼宇”的县区列表,按省市区封装为树形结构，不显示楼宇")
	@GetMapping(value = "get_building_list3.do")
	public Map<String, Object> getBuildingList3(){
		Map map = new HashMap();
		map.put("code",0);
		map.put("data",buildingService.selectList3());
		return map;
	}
	
	@ApiOperation(value = "根据id查询楼宇接口", notes = "查询楼宇")
	@PostMapping(value = "get_building.do")
	public ResponseEntity<BuildingVo> getBuilding(@RequestBody Integer buildingId ){
		return buildingService.selectBuildingById(buildingId);
	}
	//
	@ApiOperation(value = "根据areaId查询楼宇列表接口", notes = "查询区域内楼宇列表")
	@PostMapping(value = "get_building_areaId.do")
	public ResponseEntity<List<BuildingVo>> getBuildingByAreaId(@RequestBody String areaId ){
		return buildingService.selectBuildingByAreaId(areaId);
	}
	//解决
	@ApiOperation(value = "根据areaId,楼宇名称查询楼宇列表接口", notes = "根据区域、名称查询楼宇列表")
	@PostMapping(value = "get_building_areaId_name.do")
	public PageResponseBean<BuildingVo> getBuildingByAreaIdBuildingName(@RequestParam(required = false) String provinceId,@RequestParam(required = false) String cityId,
														  @RequestParam(required = false) String areaId,@RequestParam(required = false) String buildingKey,
														  @RequestParam Integer pageNum,@RequestParam Integer pageSize){
		return buildingService.selectBuildingsbyKey(provinceId,cityId,areaId,buildingKey,pageNum,pageSize);
	}
	
	@ApiOperation(value = "添加楼宇操作接口",notes = "添加楼宇")
	@PostMapping(value="addBuilding.do")
	public ResponseEntity addBuilding(@RequestBody BuildingAddRequestBean bean,HttpServletRequest request) {
		return buildingService.createBuilding(super.getSessionUser(request).getAccount(), bean);
	}
	
	@ApiOperation(value = "楼宇楼层同时添加操作接口",notes = "同时添加楼宇楼层")
	@PostMapping(value="batchBuilding_Floor.do")
	public ResponseEntity batchBuildingAndFloor(@RequestBody BuildingFloorBatchRequestBean bean,HttpServletRequest request) {
		BuildingAddRequestBean  buildingBean=new BuildingAddRequestBean();
		buildingBean.setName(bean.getName());
		buildingBean.setAreaid(bean.getAreaid());
		buildingBean.setAddress(bean.getAddress());
		
		ResponseEntity  buildingResponse=buildingService.createBuilding(super.getSessionUser(request).getAccount(), buildingBean);
		if(buildingResponse.isSuccess()) {
			Building building= (Building)buildingResponse.getData();
			if(building!=null) {
				Integer buildingId= building.getId();
				
				List<BuildingFloorAddRequestBean> floorBeans= bean.getBuildingFloorAddRequestBeans();
				for (BuildingFloorAddRequestBean floorBean : floorBeans) {
					floorBean.setBuildingid(buildingId);
				}
			   return 	buildingService.batchInsertFloor(super.getSessionUser(request).getAccount(), floorBeans);
			}
		}
		return ResponseEntityUtil.fail("添加失败");
		
		
	}

	@ApiOperation(value = "修改楼宇操作接口",notes = "修改楼宇信息")
	@PostMapping(value="uptBuilding.do")
	public ResponseEntity uptBuilding(@RequestBody BuildingUptRequestBean bean,HttpServletRequest request) {
		return buildingService.updateBuilding (super.getSessionUser(request).getAccount(), bean);
	}
	@ApiOperation(value = "删除楼宇操作接口",notes = "删除楼宇信息")
	@PostMapping(value="delBuilding.do")	
	public ResponseEntity delBuilding(@RequestParam Integer buildingId,HttpServletRequest request) {
		return buildingService.deleteBuilding(super.getSessionUser(request).getAccount(), buildingId);
	}
	///////////////////////////////楼层操作///////////////////////////////////////////////
	
	@ApiOperation(value = "添加楼层操作接口",notes = "添加楼层")
	@PostMapping(value="addFloor.do")
	public ResponseEntity addFloor(@RequestBody BuildingFloorAddRequestBean bean,HttpServletRequest request) {
		return buildingService.createBuildingFloor(super.getSessionUser(request).getAccount(), bean);
	}

	@ApiOperation(value = "批量添加楼层操作接口",notes = "批量添加楼层")
	@PostMapping(value="batch_add_floor.do")
	public ResponseEntity batchAddFloor(@RequestBody List<BuildingFloorAddRequestBean> beans,HttpServletRequest request) {
		return buildingService.batchInsertFloor(super.getSessionUser(request).getAccount(), beans);
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
	//解决
	@ApiOperation(value = "根据楼宇ID获取楼层列表接口", notes = "根据楼宇ID获取楼层列表")
	@PostMapping(value = "get_floor_list.do")
	public PageResponseBean<BuildingFloorVo> getFloorList(@RequestParam Integer buildingId,@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
		return buildingService.selectFloorsByBuildingId(buildingId,pageNum,pageSize);
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
	
	@ApiOperation(value = "批量添加设备点操作接口",notes = "批量添加设备点")
	@PostMapping(value="batch_floor_device.do")
	public ResponseEntity batchFloorDevice(@RequestBody List<FloorDeviceAddRequestBean> beans,HttpServletRequest request) {
		return buildingService.batchFloorDevice(super.getSessionUser(request).getAccount(), beans);
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
	
	@ApiOperation(value = "批量删除设备点操作接口",notes = "批量删除设备点")
	@PostMapping(value="batch_del_floor_device.do")
	public ResponseEntity batchDelFloorDevice(@RequestBody List<Integer> ids,HttpServletRequest request) {
		return buildingService.delFloorDevices(super.getSessionUser(request).getAccount(), ids);
	}
}
