package com.media.ops.backend.service;

import com.media.ops.backend.util.ResponseEntity;

public interface OthersService {
    //获取楼宇总数
    ResponseEntity sumBuild();
    //获取广告总数
    ResponseEntity sumAd();
    //获取直播总数
    ResponseEntity sumPlay();
    //获取设备总数
    ResponseEntity sumDevice();
    //获取素材总数
    ResponseEntity sumMaterial();
}
