package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class UserAccountStatusRequestBean {

	 @ApiModelProperty(value = "工号")
		private  String  account;
		
	    @ApiModelProperty(value = "状态码，1为启用，2为禁用")
		private Integer status;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
	    
	    
}
