package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by linfs on 2018-03-02.
 * 说明：
 *  getPlaysByTime为电视设备接口用到的，其它为网站或手机APP用到的
 */
@Api(description="直播接口",produces = "application/json")

@RestController
@RequestMapping("play")
public class PlayController {
    @Autowired
    private PlayService playService;
    
    @ApiOperation(value = "获取时间段范围内的直播记录",notes = "获取时间段范围内的直播记录")
    @PostMapping(value="getPlaysByTime.do")
    @ResponseBody
    public ResponseEntity<List<Play>> getPlaysByTime(String begintime, String endtime) {
        return playService.GetPlays(begintime,endtime);
    }

}
