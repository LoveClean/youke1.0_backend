package com.media.ops.backend.service.impl;

import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.mapper.PlayMapper;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linfs on 2018-03-02.
 */
@Service
public class PlayServiceImpl implements PlayService {
    @Resource
    private PlayMapper playMapper;

    @Override
    public ResponseEntity<List<Play>> GetPlays(String begintime, String endtime) {
        List<Play> list= playMapper.selectByTime(begintime,endtime);
        if (list==null)
            return ResponseEntityUtil.fail("查询直播信息失败");
        return ResponseEntityUtil.success(list);
    }
}
