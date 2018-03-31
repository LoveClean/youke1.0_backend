package com.media.ops.backend.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.controller.request.BuildingAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorUptRequestBean;
import com.media.ops.backend.controller.request.BuildingSearchRequestBean;
import com.media.ops.backend.controller.request.BuildingUptRequestBean;
import com.media.ops.backend.controller.request.DeviceSearchRequestBean;
import com.media.ops.backend.controller.request.FloorDeviceAddRequestBean;
import com.media.ops.backend.controller.request.FloorDeviceUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AreaBuildingVo;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;
import com.media.ops.backend.vo.FloorDeviceVo;

public interface BuildingService {

	//////////////////////////// 楼宇操作////////////////////////////////////////
	public ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean);

	public ResponseEntity updateBuilding(String updateby, BuildingUptRequestBean bean);

	public ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId);

	public PageResponseBean<BuildingVo> selectList(PageRequestBean bean);
	
	public ResponseEntity<List<AreaBuildingVo>> selectBuildingByAreaId(String areaId);

	public ResponseEntity<BuildingVo> selectBuildingById(Integer id);
	
	public ResponseEntity<PageInfo> selectBuildingsbyKey(BuildingSearchRequestBean bean);

	///////////////////////////// 楼层操作///////////////////////////////////
	public ResponseEntity createBuildingFloor(String createby, BuildingFloorAddRequestBean bean);
	
	public ResponseEntity batchInsertFloor(String createby, List<BuildingFloorAddRequestBean> beans);

	public ResponseEntity updateBuildingFloor(String updateby, BuildingFloorUptRequestBean bean);

	public ResponseEntity<String> delBuildingFloor(String updateby, Integer Id);

	public ResponseEntity<String> delFloorByBuildingId(String updateby, Integer buildingId);

	public ResponseEntity<List<BuildingFloorVo>> selectFloorsByBuildingId(Integer buildingId);

	public ResponseEntity<BuildingFloorVo> selectFloorById(Integer id);

	////////////////////////////// 楼层设备操作//////////////////////////////////////////
	public ResponseEntity addFloorDevice(String createby, FloorDeviceAddRequestBean bean);

	public ResponseEntity updateFloorDevice(String updateby, FloorDeviceUptRequestBean bean);

	public ResponseEntity<String> delFloorDevice(String updateby, Integer Id);

	public ResponseEntity<String> delDevicesByFloorId(String updateby, Integer floorId);

	public ResponseEntity<List<FloorDeviceVo>> selecDevicesByFloorId(Integer floorId);

	public ResponseEntity<FloorDeviceVo> selectFloorDeviceById(Integer id);

}
