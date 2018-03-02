package com.media.ops.backend.vo;

import java.util.Date;

/**
 * Created by linfs on 2018-03-02.
 * 当前投放的广告，用于设备查询
 */
public class AddeliveryVo extends AdVo {
    private Date begintime;

    private Date endtime;

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
