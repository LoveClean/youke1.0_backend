package com.media.ops.backend.service;

import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.util.ResponseEntity;

import java.util.List;

/**
 * Created by linfs on 2018-03-02.
 * 直播接口
 */
public interface PlayService {
    //查询直播
    ResponseEntity<List<Play>> GetPlays(String begintime, String endtime);

}
