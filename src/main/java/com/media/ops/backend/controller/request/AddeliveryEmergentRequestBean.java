package com.media.ops.backend.controller.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AddeliveryEmergentRequestBean {
	@NotNull(message = "投放记录id不能为空")
	@ApiModelProperty(value = "id，必填", required = true)
	private Integer id; //id
	
	@NotBlank(message = "开始播放时间不能空")
	@ApiModelProperty(value = "开始投放时间，必填", required = true)
    private String begintime;  //开始时间

	@NotBlank(message = "结束投放时间不能空")
	@ApiModelProperty(value = "结束投放时间，必填", required = true)
    private String endtime;  //结束时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	

}
