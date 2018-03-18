package com.media.ops.backend.vo;

import java.util.Date;

public class AddeliveryDetailVo {
	   private Integer id;

	    private Integer adid;//广告id
	    private String adName;
	    
	    private Integer adtype; //广告类型，0为正常，1为插播

	    private Integer delivertype; //投放类型

	    private String areaid;//区域ID，选填
	    private String areaName;
	    private String cityName;

	    private Integer buildingOrGroupId; //楼宇ID或设备分组ID
	    private String buildingOrGroupName;

	    private Date begintime;  //开始时间

	    private Date endtime;  //结束时间

		
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
		
		

		public String getAdName() {
			return adName;
		}

		public void setAdName(String adName) {
			this.adName = adName;
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
			this.areaid = areaid;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public Integer getBuildingOrGroupId() {
			return buildingOrGroupId;
		}

		public void setBuildingOrGroupId(Integer buildingOrGroupId) {
			this.buildingOrGroupId = buildingOrGroupId;
		}

		public String getBuildingOrGroupName() {
			return buildingOrGroupName;
		}

		public void setBuildingOrGroupName(String buildingOrGroupName) {
			this.buildingOrGroupName = buildingOrGroupName;
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
	    

}
