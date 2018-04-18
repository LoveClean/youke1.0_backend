package com.media.ops.backend.vo;

import java.util.Date;

public class PlaydeliveryDetailVo {
	   private Integer id;

	    private Integer playid;//广告id
	    private String playTitle;
	    private Integer playStatus;  //直播状态
	    
	    private Integer delivertype; //投放类型

	    private String areaid;//区域ID，选填
	    private String areaName;
	    private String cityName;

	    private Integer buildingOrGroupId; //楼宇ID或设备分组ID
	    private String buildingOrGroupName;

	
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getPlayid() {
			return playid;
		}

		public void setPlayid(Integer playid) {
			this.playid = playid;
		}



		public String getPlayTitle() {
			return playTitle;
		}

		public void setPlayTitle(String playTitle) {
			this.playTitle = playTitle;
		}

		public Integer getPlayStatus() {
			return playStatus;
		}

		public void setPlayStatus(Integer playStatus) {
			this.playStatus = playStatus;
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


	    

}
