package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicetype;
import com.media.ops.backend.util.ResponseEntity;

public interface DeviceTypeService {
	ResponseEntity addType(Devicetype devicetype);
	ResponseEntity<String> delType(Integer id);
	
	ResponseEntity<List<Devicetype>> selectAll();
}
