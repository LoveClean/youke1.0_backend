package com.media.ops.backend.dao.entity;

import java.util.Date;

public class Addelivery {
    private Integer id;

    private Integer adid;//广告id
    
    private Integer adtype; //广告类型，0为普通广告，1为插播

    private Integer delivertype; //投放类型

    private String areaid;//区域ID，选填

    private Integer groupid; //楼宇ID或设备分组ID

    private Date begintime;  //开始时间

    private Date endtime;  //结束时间

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

	@Override
	public String toString() {
		return "Addelivery [id=" + id + ", adid=" + adid + ", adtype=" + adtype + ", delivertype=" + delivertype
				+ ", areaid=" + areaid + ", groupid=" + groupid + ", begintime=" + begintime + ", endtime=" + endtime
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", delFlag=" + delFlag + ", getId()=" + getId() + ", getAdid()=" + getAdid()
				+ ", getAdtype()=" + getAdtype() + ", getDelivertype()=" + getDelivertype() + ", getAreaid()="
				+ getAreaid() + ", getGroupid()=" + getGroupid() + ", getBegintime()=" + getBegintime()
				+ ", getEndtime()=" + getEndtime() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()="
				+ getCreateDate() + ", getUpdateBy()=" + getUpdateBy() + ", getUpdateDate()=" + getUpdateDate()
				+ ", getRemarks()=" + getRemarks() + ", getDelFlag()=" + getDelFlag() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}