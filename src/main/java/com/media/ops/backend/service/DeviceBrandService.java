package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.dao.entity.Devicebrand;
import com.media.ops.backend.util.ResponseEntity;

public interface DeviceBrandService {

	ResponseEntity<String> addBrand(Devicebrand devicebrand);
	ResponseEntity<String> delBrand(String brand);
	
	ResponseEntity<List<Devicebrand>> selectAll();
}
