package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.MaterailAddRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.MaterailUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.MaterialVo;

public interface MaterialService {

	public PageResponseBean<MaterialVo> selectMaterialList(PageRequestBean bean);
	
	public PageResponseBean<MaterialVo> selectMaterialByKeywordGroup(String keyword, Integer groupId,
			Integer pageNum, Integer pageSize);
	
	public ResponseEntity<MaterialVo> selectMaterial(Integer id);
	
	public ResponseEntity<String> addMaterial( MaterailAddRequestBean bean );
	
	public ResponseEntity<String> uptMaterial( MaterailUptRequestBean bean );
	
	public ResponseEntity<String> delMaterial(Integer id);
	
	
}
