package com.media.ops.backend.service.impl;

import com.media.ops.backend.dao.mapper.*;
import com.media.ops.backend.service.OthersService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OthersServiceImpl implements OthersService {
    @Resource
    BuildingMapper buildingMapper;
    @Resource
    AdMapper adMapper;
    @Resource
    DeviceMapper deviceMapper;
    @Resource
    MaterialMapper materialMapper;
    @Resource
    PlayMapper playMapper;

    @Override
    public ResponseEntity sumBuild() {
        return ResponseEntityUtil.success(buildingMapper.sumBuild());
    }

    @Override
    public ResponseEntity sumAd() {
        return ResponseEntityUtil.success(adMapper.sumAd());
    }

    @Override
    public ResponseEntity sumPlay() {
        return ResponseEntityUtil.success(playMapper.sumPlay());
    }

    @Override
    public ResponseEntity sumDevice() {
        return ResponseEntityUtil.success(deviceMapper.sumDevice());
    }

    @Override
    public ResponseEntity sumMaterial() {
        return ResponseEntityUtil.success(materialMapper.sumMaterial());
    }
}
