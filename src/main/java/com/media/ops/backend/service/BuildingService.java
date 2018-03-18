package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.BuildingAddRequestBean;
import com.media.ops.backend.controller.request.BuildingUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.BuildingVo;

public interface BuildingService {

	public ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean);
	
	public ResponseEntity updateBuilding(String updateby, BuildingUptRequestBean bean);
	
	public ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId);
	
	public PageResponseBean<BuildingVo> selectList(PageRequestBean bean);
	
	public ResponseEntity<BuildingVo> selectBuildingById(Integer id);
}
