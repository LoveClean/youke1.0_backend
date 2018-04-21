package com.media.ops.backend.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class AdSearchRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975429760176343147L;

	  private static final int DEFAULT_PAGE = 1;
	  private static final int DEFAULT_SIZE = 999;
	  
	@ApiModelProperty(value = "广告分组",required=true)
	private Integer groupId;
	
	@ApiModelProperty(value = "关键词")
	private String keyword;
	
	 /**
	   * 当前页，默认1
	   */
	  @Min(value = 1, message = "pageNum cannot be less then 1")
	  @ApiModelProperty(value = "当前页，首页为1")
	  private int pageNum = DEFAULT_PAGE;

	  /**
	   * 每页多少条，默认10条
	   * 0查全部
	   */
	  @Min(value = 0, message = "pageSize cannot be less then 0")
	  @Max(value = 500, message = "pageSize cannot be more then 20")
	  @ApiModelProperty(value = "每页显示条数，须大于0，默认10条")
	  private int pageSize = DEFAULT_SIZE;
	  
	  public int getPageNum() {
		    return pageNum;
		  }

		  public void setPageNum(int pageNum) {
		    this.pageNum = pageNum <= 0 ? 1 : pageNum;
		  }

		  public int getPageSize() {
		    return pageSize;
		  }

		  public void setPageSize(int pageSize) {
		    this.pageSize = (pageSize <= 0 ||  pageSize >= 1000) ? 999 : pageSize;
		  }

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	
	
}
