package com.media.ops.backend.service;


import com.github.pagehelper.PageInfo;
import com.media.ops.backend.controller.request.DeviceAddRequestBean;
import com.media.ops.backend.controller.request.DeviceSearchRequestBean;
import com.media.ops.backend.controller.request.DeviceUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.DeviceVo;


public interface DeviceService {

	public PageResponseBean<DeviceListVo> selectDeviceList(PageRequestBean bean);
	
	public ResponseEntity<PageInfo> selectDeviceByCodeAreaAddress(DeviceSearchRequestBean bean);
	
	public ResponseEntity<DeviceVo> selectDevice(Integer id);
	
	public ResponseEntity<String> addDevice( DeviceAddRequestBean bean );
	
	public ResponseEntity<String> uptDevice( DeviceUptRequestBean bean );
	
	public ResponseEntity<String> delDevice(Integer id);
}
