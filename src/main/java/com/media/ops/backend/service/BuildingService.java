package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.controller.request.BuildingAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorAddRequestBean;
import com.media.ops.backend.controller.request.BuildingFloorUptRequestBean;
import com.media.ops.backend.controller.request.BuildingUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;

public interface BuildingService {

	public ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean);
	
	public ResponseEntity updateBuilding(String updateby, BuildingUptRequestBean bean);
	
	public ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId);
	
	public PageResponseBean<BuildingVo> selectList(PageRequestBean bean);
	
	public ResponseEntity<BuildingVo> selectBuildingById(Integer id);
	
	////////////////////////////////////////////////////////////////////////
	public ResponseEntity createBuildingFloor(String createby, BuildingFloorAddRequestBean bean);
	
	public ResponseEntity updateBuildingFloor(String updateby, BuildingFloorUptRequestBean bean);
	
	public ResponseEntity<String> delBuildingFloor(String updateby, Integer Id);
	
	public ResponseEntity<String> delFloorByBuildingId(String updateby, Integer buildingId);
	
	public ResponseEntity<List<BuildingFloorVo>> selectFloorsByBuildingId(Integer buildingId);
	
	
}
