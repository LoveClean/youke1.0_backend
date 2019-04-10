package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.*;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.BuildingFloorVo;
import com.media.ops.backend.vo.BuildingVo;
import com.media.ops.backend.vo.FloorDeviceVo;
import com.media.ops.backend.vo.TestProvinceVo;

import java.util.List;

public interface BuildingService {

    //////////////////////////// 楼宇操作////////////////////////////////////////
    ResponseEntity createBuilding(String createby, BuildingAddRequestBean bean);

    ResponseEntity updateBuilding(String updateby, BuildingUptRequestBean bean);

    ResponseEntity<String> deleteBuilding(String updateby, Integer buildingId);

    PageResponseBean<BuildingVo> selectList(Integer pageNum, Integer pageSize);

    ResponseEntity<List<BuildingVo>> selectBuildingByAreaId(String areaId);

    ResponseEntity<BuildingVo> selectBuildingById(Integer id);

    PageResponseBean<BuildingVo> selectBuildingsbyKey(String provinceId2, String cityId2, String areaId2,
                                                      String buildingKey2, Integer pageNum2, Integer pageSize2);

    List<TestProvinceVo> selectList2();

    List<TestProvinceVo> selectList3();

    ///////////////////////////// 楼层操作///////////////////////////////////
    ResponseEntity createBuildingFloor(String createby, BuildingFloorAddRequestBean bean);

    ResponseEntity batchInsertFloor(String createby, List<BuildingFloorAddRequestBean> beans);

    ResponseEntity updateBuildingFloor(String updateby, BuildingFloorUptRequestBean bean);

    ResponseEntity<String> delBuildingFloor(String updateby, Integer Id);

    ResponseEntity<String> delFloorByBuildingId(String updateby, Integer buildingId);

    public PageResponseBean<BuildingFloorVo> selectFloorsByBuildingId(Integer buildingId, Integer pageNum, Integer pageSize);

    public ResponseEntity<BuildingFloorVo> selectFloorById(Integer id);

    ////////////////////////////// 楼层设备操作//////////////////////////////////////////
    ResponseEntity addFloorDevice(String createby, FloorDeviceAddRequestBean bean);

    ResponseEntity batchFloorDevice(String createby, List<FloorDeviceAddRequestBean> beans);

    ResponseEntity updateFloorDevice(String updateby, FloorDeviceUptRequestBean bean);

    ResponseEntity<String> delFloorDevice(String updateby, Integer Id);

    ResponseEntity<String> delFloorDevices(String updateby, List<Integer> ids);

    ResponseEntity<String> delDevicesByFloorId(String updateby, Integer floorId);

    ResponseEntity<List<FloorDeviceVo>> selecDevicesByFloorId(Integer floorId);

    ResponseEntity<FloorDeviceVo> selectFloorDeviceById(Integer id);

}
