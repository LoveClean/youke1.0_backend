package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.controller.request.MaterialUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.MaterialVo;

import java.util.List;

public interface MaterialService {

	public PageResponseBean<MaterialVo> selectMaterialList(int pageNum, int pageSize);

	public PageResponseBean<MaterialVo> selectMaterialListByIds(String ids, int pageNum, int pageSize);

	public PageResponseBean<MaterialVo> selectMaterialByKeywordTypeGroup(String keyword, String type, Integer groupId,
                                                                         Integer pageNum, Integer pageSize);

	public ResponseEntity<MaterialVo> selectMaterial(Integer id);

	public ResponseEntity addMaterial(String createby, MaterialAddRequestBean bean);

	public ResponseEntity uptMaterial(String updateby, MaterialUptRequestBean bean);

	public ResponseEntity<String> delMaterial(String updateby, Integer id);

	public ResponseEntity<List<MaterialVo>> selectMaterialMusicList();

	public PageResponseBean<MaterialVo> selectMaterialListExMu(int pageNum, int pageSize);

	public PageResponseBean<MaterialVo> selectMaterialByKeywordTypeGroupExMu(String keyword, String type, Integer groupId,Integer pageNum, Integer pageSize);
	
}
