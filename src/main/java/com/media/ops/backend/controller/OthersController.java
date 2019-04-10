package com.media.ops.backend.controller;

import com.media.ops.backend.service.OthersService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "统计数据", produces = "application/json")
@RestController
@RequestMapping("/others/")
public class OthersController {
    @Autowired
    private OthersService othersService;

    @ApiOperation(value = "楼宇总数", notes = "楼宇总数")
    @GetMapping(value = "sumBuild")
    public ResponseEntity sumBuild() {
        return othersService.sumBuild();
    }

    @ApiOperation(value = "广告总数", notes = "广告总数")
    @GetMapping(value = "sumAd")
    public ResponseEntity sumAd() {
        return othersService.sumAd();
    }

    @ApiOperation(value = "直播总数", notes = "直播总数")
    @GetMapping(value = "sumPlay")
    public ResponseEntity sumPlay() {
        return othersService.sumPlay();
    }

    @ApiOperation(value = "设备总数", notes = "设备总数")
    @GetMapping(value = "sumDevice")
    public ResponseEntity sumDevice() {
        return othersService.sumDevice();
    }

    @ApiOperation(value = "素材总数", notes = "素材总数")
    @GetMapping(value = "sumMaterial")
    public ResponseEntity sumMaterial() {
        return othersService.sumMaterial();
    }
}
