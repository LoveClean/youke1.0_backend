package com.media.ops.backend.service;

import com.media.ops.backend.controller.request.PlayAddRequestBean;
import com.media.ops.backend.controller.request.PlayUpdateRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlayVo;
import com.media.ops.backend.vo.PlayerVo;

import java.util.List;

public interface PlayService {
	
	public ResponseEntity<List<Play>> GetPlays(String begintime, String endtime);
	
	public ResponseEntity add(String createby, PlayAddRequestBean bean);

	public ResponseEntity update(String updateby, PlayUpdateRequestBean bean);

	public ResponseEntity<String> delete(String updateby, Integer id);

	public ResponseEntity<PlayVo> selectByKey(Integer id);

	public PageResponseBean<PlayVo> selectPlayListByKeys(Integer playerId, Integer status, String playTitle, String beginTime, String endTime, int pageNum, int pageSize);

	public PageResponseBean<PlayVo> selectPlayList(int pageNum, int pageSize);
	
	public ResponseEntity<List<PlayVo>> selectUnfinishedPlayList();
	
	public ResponseEntity<List<PlayerVo>> selectPlayerList();

	public PageResponseBean<PlayVo> selectPlayListByPlayerId(int pageNum, int pageSize, Integer playerId);
}