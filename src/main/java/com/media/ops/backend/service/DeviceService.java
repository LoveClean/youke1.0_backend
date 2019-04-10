package com.media.ops.backend.service;


import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;


public interface DeviceService {

	public PageResponseBean<DeviceListVo> selectDeviceList(Integer pageNum,Integer pageSize);
	
	public PageResponseBean<DeviceListVo> selectDeviceByCodeAreaAddress( String code2, String cityId2, String areaId2, String address2,
																   Integer buildingId2, Integer groupId2, Integer pageNum2, Integer pageSize2);
	
	public ResponseEntity<DeviceVo> selectDevice(Integer id);
	
	public ResponseEntity addDevice(String createby, DeviceAddRequestBean bean );
	
	public ResponseEntity uptDevice(String updateby, DeviceUptRequestBean bean );
	
	public ResponseEntity<String> delDevice(Integer id,String updateby);

	public ResponseEntity unboundDevice(Integer id,String updateby);
}
