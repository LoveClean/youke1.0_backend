package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.controller.request.PlayAddRequestBean;
import com.media.ops.backend.controller.request.PlayUpdateRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlayVo;

public interface PlayService {
	
	public ResponseEntity<List<Play>> GetPlays(String begintime, String endtime);
	
	public ResponseEntity<String> add(String createby,PlayAddRequestBean bean);
	
	public ResponseEntity<String> update(String updateby,PlayUpdateRequestBean bean);
	
	public ResponseEntity<String> delete(String updateby,Integer id);
	
	public ResponseEntity<PlayVo> selectByKey(Integer id);
	
	public ResponseEntity<List<PlayVo>> selectPlayListWithStatusAndPlayerId(Integer playerId,Integer status);
	
	public ResponseEntity<List<PlayVo>> selectPlayList();

	public PageResponseBean<PlayVo> selectPlayListByPlayerId(int pageNum, int pageSize, Integer playerId);
}