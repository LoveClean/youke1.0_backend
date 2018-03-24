package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.MaterialUptRequestBean;
import com.media.ops.backend.controller.request.MaterialQueryRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.MaterialVo;

public interface MaterialService {

	public PageResponseBean<MaterialVo> selectMaterialList(PageRequestBean bean);
	
	public PageResponseBean<MaterialVo> selectMaterialListByIds(MaterialQueryRequestBean bean);
	
	public PageResponseBean<MaterialVo> selectMaterialByKeywordTypeGroup(String keyword, String type, Integer groupId,
			Integer pageNum, Integer pageSize);
	
	public ResponseEntity<MaterialVo> selectMaterial(Integer id);
	
	public ResponseEntity addMaterial(String createby,  MaterialAddRequestBean bean );
	
	public ResponseEntity uptMaterial(String updateby, MaterialUptRequestBean bean );
	
	public ResponseEntity<String> delMaterial(String updateby,Integer id);
	
	
}
