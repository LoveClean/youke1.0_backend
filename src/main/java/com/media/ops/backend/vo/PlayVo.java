package com.media.ops.backend.vo;

import java.util.Date;

public class PlayVo {

	    private String title;

	    private Integer type;

	    private Integer playerid;
	    
	    private PlayerVo playerVo;
	    
	    private String picpath;
	    
	    private Date begintime;
	    
	    private Date endtime;
	    
	    private String replayaddress;

	    private String realaddress;
	    
	    private String streamaddress;

	    private Integer status;
	    

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getPlayerid() {
			return playerid;
		}

		public void setPlayerid(Integer playerid) {
			this.playerid = playerid;
		}

		public String getReplayaddress() {
			return replayaddress;
		}

		public void setReplayaddress(String replayaddress) {
			this.replayaddress = replayaddress;
		}

		public String getRealaddress() {
			return realaddress;
		}

		public void setRealaddress(String realaddress) {
			this.realaddress = realaddress;
		}

		public String getPicpath() {
			return picpath;
		}

		public void setPicpath(String picpath) {
			this.picpath = picpath;
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

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public PlayerVo getPlayerVo() {
			return playerVo;
		}

		public void setPlayerVo(PlayerVo playerVo) {
			this.playerVo = playerVo;
		}

		public String getStreamaddress() {
			return streamaddress;
		}

		public void setStreamaddress(String streamaddress) {
			this.streamaddress = streamaddress;
		}
		
		



}
