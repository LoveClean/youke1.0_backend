package com.media.ops.backend.service;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdMaterialListVo;

import java.util.List;
import java.util.Map;

/**
 * Created by linfs on 2018-03-01.
 */

public interface DeviceRequestService {

    ResponseEntity<Device> GetDevice(String code);
    //绑定设备
    ResponseEntity<String> BindDevice(String code,String mac);
    //获取设备的当前投放广告
    ResponseEntity<List<AdMaterialListVo>> GetAd(String mac);
}

