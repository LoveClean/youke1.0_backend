package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BatchAddPlayDeliveryAddRequestBean {
    @NotNull(message = "直播id不能为空")
    @ApiModelProperty(value = "直播id，必填", required = true)
    private Integer playid;//直播id

    @NotNull(message = "投放类型不能为空")
    @ApiModelProperty(value = "投放类型deliverytype，必填-0按设备分组，1按楼宇", required = true)
    private Integer delivertype; //投放类型

    private Integer groupid; //设备分组id

    private List<String> areaAndBuildingList;

    public Integer getPlayid() {
        return playid;
    }

    public void setPlayid(Integer playid) {
        this.playid = playid;
    }

    public Integer getDelivertype() {
        return delivertype;
    }

    public void setDelivertype(Integer delivertype) {
        this.delivertype = delivertype;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public List<String> getAreaAndBuildingList() {
        return areaAndBuildingList;
    }

    public void setAreaAndBuildingList(List<String> areaAndBuildingList) {
        this.areaAndBuildingList = areaAndBuildingList;
    }
}
