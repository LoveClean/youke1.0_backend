package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Title: PageRequestBean.java
 * @Package cc.uworks.library.controller.request
 * @author liyuchang
 * @Description: 分页请求数据
 * @date 2017年3月31日
 * @version V1.0
 */
@ApiModel
public class PageRequestBean implements Serializable {

  private static final long serialVersionUID = -8822923831226295853L;
  private static final int DEFAULT_PAGE = 1;
  private static final int DEFAULT_SIZE = 10;
//  private static final String SORT = "ID";
  //ASC升序，DESC降序
//  private static final String ORDER = "DESC";
  
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

  /**
   * 排序字段
   */
//  @ApiModelProperty(value = "排序字段，不需要传递")
//  private String sort;

  /**
   * ASC升序，DESC降序
   */
//  @ApiModelProperty(value = "ASC升序，DESC降序")
//  private String order;
  
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
    this.pageSize = (pageSize <= 0 ||  pageSize >= 100) ? 10 : pageSize;
  }
//  public String getSort() {
//    return StringUtils.isNotBlank(sort) ? sort : SORT;
//  }
//
//  public void setSort(String sort) {
//    this.sort = sort;
//  }
//
//  public String getOrder() {
//    return StringUtils.isNotBlank(order) ? order : ORDER;
//  }
//
//  public void setOrder(String order) {
//    this.order = order;
//  }

//  public String getOrderBy() {
//    return getSort() + " " + getOrder();
//  }
}
