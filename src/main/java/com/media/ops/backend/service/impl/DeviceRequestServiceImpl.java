package com.media.ops.backend.service.impl;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.mapper.DeviceMapper;
import com.media.ops.backend.dao.mapper.UserMapper;
import com.media.ops.backend.service.DeviceRequestService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by linfs on 2018-03-01.
 */

@Service
public class DeviceRequestServiceImpl implements DeviceRequestService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public ResponseEntity<Device> GetDevice(String code)
    {
        Device device = deviceMapper.selectByCode(code);
        if(device==null) {
            return ResponseEntityUtil.fail("设备编号错误");
        }
        return ResponseEntityUtil.success(device);
    }
}
