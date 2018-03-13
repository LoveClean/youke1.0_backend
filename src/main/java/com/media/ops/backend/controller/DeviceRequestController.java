package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.service.DeviceRequestService;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.util.ResponseEntity;

import com.media.ops.backend.vo.AdMaterialListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 林逢升
 * @version 1.0
 * @Date 2018/3/1 10:30
 */
@Api(description="设备数据请求接口",produces = "application/json")
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/devicerequest/")
public class DeviceRequestController {

    @Autowired
    private DeviceRequestService deviceRequestService;

    @ApiOperation(value = "设备查询",notes = "设备查询")
    @PostMapping(value="getDevice.do")
    @ResponseBody
    public ResponseEntity<Device> getDevice(String code ) {
        return deviceRequestService.GetDevice(code);
    }
    @ApiOperation(value = "设备绑定",notes = "设备绑定")
    @PostMapping(value="bindDevice.do")
    @ResponseBody
    public ResponseEntity<String> bindDevice(String code,String mac ) {
        return deviceRequestService.BindDevice(code,mac);
    }
    @ApiOperation(value = "获取当前广告",notes = "获取当前广告")
    @PostMapping(value="getAd.do")
    @ResponseBody
    public ResponseEntity<List<AdMaterialListVo>> getAd(String mac) {
        return deviceRequestService.GetAd(mac);
    }

    @Autowired
    private PlayService playService;
    @ApiOperation(value = "获取时间段范围内的直播记录",notes = "获取时间段范围内的直播记录")
    @PostMapping(value="getPlaysByTime.do")
    @ResponseBody
    public ResponseEntity<List<Play>> getPlaysByTime(String begintime, String endtime) {
        return playService.GetPlays(begintime,endtime);
    }

}
