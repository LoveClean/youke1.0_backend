package com.media.ops.backend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.util.ResponseEntity;

public interface AreaService {
	ResponseEntity<Area> selectByAreaId(String areaId);
	ResponseEntity<List<Area>> selectAreasByCityId(@Param("cityId") String cityId);
}
