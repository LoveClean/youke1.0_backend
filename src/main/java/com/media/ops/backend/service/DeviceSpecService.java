package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicespec;
import com.media.ops.backend.util.ResponseEntity;

public interface DeviceSpecService {

	ResponseEntity<String> addSpec(Devicespec devicespec);
	ResponseEntity<String> delSpec(String spec);
	
	ResponseEntity<List<Devicespec>> selectAll();
}
