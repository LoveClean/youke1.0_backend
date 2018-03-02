package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.service.DeviceRequestService;
import com.media.ops.backend.util.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 林逢升
 * @version 1.0
 * @Date 2018/3/1 10:30
 */
@Api(description="设备数据请求接口",produces = "application/json")
@RestController
@RequestMapping("devicerequest")
public class DeviceRequestController {

    @Autowired
    private DeviceRequestService deviceRequestService;

    @ApiOperation(value = "设备查询",notes = "设备查询")
    @PostMapping(value="getDevice.do")
    @ResponseBody
    public ResponseEntity<Device> getDevice(String code, HttpSession session ) {
        return deviceRequestService.GetDevice(code);
    }
}