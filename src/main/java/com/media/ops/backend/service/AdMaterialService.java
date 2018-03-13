package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.util.ResponseEntity;

public interface AdMaterialService {

	public ResponseEntity  addAdMaterial(String createby, AdMaterialAddRequestBean bean);
}
