package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.controller.request.AdMaterialAddRequestBean;
import com.media.ops.backend.controller.request.AdMaterialUptRequestBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.AdMaterialVo;

public interface AdMaterialService {

	public ResponseEntity  addAdMaterial(String createby, AdMaterialAddRequestBean bean);
	
	public ResponseEntity batchInsertAdmaterial(String createby, List<AdMaterialAddRequestBean> beans);

	public ResponseEntity updateAdMaterial(String updateby, AdMaterialUptRequestBean bean);
	
	public ResponseEntity batchUpdateAdMaterial(String updateby, List<AdMaterialUptRequestBean> beans);
	
	public ResponseEntity<String> delAdMaterial(Integer id, String updateby);
	
	public ResponseEntity  delAdMaterialByAdId(Integer adId, String updateby);
	
	public List<AdMaterialVo> selectListByAdId(Integer adId);
	
	
}
