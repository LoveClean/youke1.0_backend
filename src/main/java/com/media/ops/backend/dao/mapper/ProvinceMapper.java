package com.media.ops.backend.dao.mapper;

import java.util.List;

import com.media.ops.backend.dao.entity.Province;

public interface ProvinceMapper {

	/**
	 * 根据省份id获取省份
	 * @param id
	 * @return  省份
	 */
    Province selectByPrimaryKey(Integer id);
    /**
     * 获取所有省份
     * @return
     */
    List<Province> selectAllProvinces();

}