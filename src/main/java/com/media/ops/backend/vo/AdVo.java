package com.media.ops.backend.vo;

import com.media.ops.backend.dao.entity.Ad;

/**
 * Created by linfs on 2018-03-02.
 * 广告扩展类，对应vwAd视图
 */
public class AdVo extends Ad {
    private String groupname;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
