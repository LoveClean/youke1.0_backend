package com.media.ops.backend.service;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.util.ResponseEntity;

import java.util.Map;

/**
 * Created by linfs on 2018-03-01.
 */

public interface DeviceRequestService {

    ResponseEntity<Device> GetDevice(String code);

}

