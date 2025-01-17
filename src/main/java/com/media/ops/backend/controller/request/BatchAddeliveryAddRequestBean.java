package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BatchAddeliveryAddRequestBean {

    @NotNull(message = "广告id不能为空")
    @ApiModelProperty(value = "广告id，必填", required = true)
    private Integer adid;//广告id

    @NotNull(message = "广告类型不能为空")
    @ApiModelProperty(value = "广告类型adtype，必填-0为正常，1为插播", required = true)
    private Integer adtype; //广告类型，0为普通广告，1为插播

    @NotNull(message = "投放类型不能为空")
    @ApiModelProperty(value = "投放类型deliverytype，必填-0按设备分组，1按楼宇", required = true)
    private Integer delivertype; //投放类型

    @NotBlank(message = "开始播放时间不能空")
    @ApiModelProperty(value = "开始投放时间，必填", required = true)
    private String begintime;  //开始时间

    @NotBlank(message = "结束投放时间不能空")
    @ApiModelProperty(value = "结束投放时间，必填", required = true)
    private String endtime;  //结束时间

    private String groupid;  //设备分组id

    private List<String> areaAndBuildingList;

    public Integer getAdid() {
        return adid;
    }

    public void setAdid(Integer adid) {
        this.adid = adid;
    }

    public Integer getAdtype() {
        return adtype;
    }

    public void setAdtype(Integer adtype) {
        this.adtype = adtype;
    }

    public Integer getDelivertype() {
        return delivertype;
    }

    public void setDelivertype(Integer delivertype) {
        this.delivertype = delivertype;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public List<String> getAreaAndBuildingList() {
        return areaAndBuildingList;
    }

    public void setAreaAndBuildingList(List<String> areaAndBuildingList) {
        this.areaAndBuildingList = areaAndBuildingList;
    }
}
