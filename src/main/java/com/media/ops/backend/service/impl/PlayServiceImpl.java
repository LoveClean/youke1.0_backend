package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import com.beust.jcommander.internal.Lists;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PlayAddRequestBean;
import com.media.ops.backend.controller.request.PlayUpdateRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.dao.mapper.PlayMapper;
import com.media.ops.backend.dao.mapper.SysparaMapper;
import com.media.ops.backend.dao.mapper.UserMapper;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.util.DateUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.PlayVo;
import com.media.ops.backend.vo.PlayerVo;
import com.media.ops.backend.vo.UserVo;

@Service
public class PlayServiceImpl implements PlayService {

	@Resource
	private PlayMapper playMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private SysparaMapper sysparaMapper;

	@Override
	public ResponseEntity<List<Play>> GetPlays(String begintime, String endtime) {
		List<Play> plays = playMapper.selectByTime(begintime, endtime);
		if (plays == null) {
			return ResponseEntityUtil.fail("查询直播信息失败");
		}
		return ResponseEntityUtil.success(plays);
	}

	@Override
	public ResponseEntity add(String createby, PlayAddRequestBean bean) {
		if (bean == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		List<Play>  plays =playMapper.selectByPlayerBeginEndTime(bean.getPlayerid(), bean.getBegintime(), bean.getEndtime());
		if(CollectionUtils.isNotEmpty(plays)) {
			return ResponseEntityUtil.fail("直播员在该时段还有尚未结束的直播，请检查！");
		}
		
		Play play = new Play();
		play.setPicpath(bean.getPicpath());
		play.setCreateBy(createby);
		play.setPlayerid(bean.getPlayerid());
		play.setRealaddress(bean.getRealaddress());
		play.setStatus(1);
		play.setTitle(bean.getTitle());
		play.setType(bean.getType());
		play.setUpdateBy(createby);
		play.setDelFlag(Const.DelFlagEnum.NORMAL);
		play.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
		play.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
		int resultCount = playMapper.insert(play);

		if (resultCount == 0) {
			return ResponseEntityUtil.fail("添加直播失败");
		}
		
		play.setStreamaddress(sysparaMapper.selectByName("PRE_STREAM").getValue()+play.getId());
		resultCount=playMapper.updateStreamAddressById(play.getStreamaddress(), play.getId());

		if (resultCount == 0) {
			return ResponseEntityUtil.fail("推流地址修改失败");
		}
		
		Map<String, Object> result = Maps.newHashMap();
		result.put("newPlay", play);
		return ResponseEntityUtil.success(result);
	}

	
	@Override
	public ResponseEntity update(String updateby, PlayUpdateRequestBean bean) {
		if (bean==null || bean.getId()==null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		
		Play playInfo = playMapper.selectByPrimaryKey(bean.getId());
		if (playInfo == null) {
			return ResponseEntityUtil.fail("该直播不存在或已删除");
		}
		if(playInfo.getStatus()>=2) {
			return ResponseEntityUtil.fail("该直播在进行中或已完成，不可以修改");
		}
		
		List<Play>  plays =playMapper.selectByPlayerBeginEndTime(bean.getPlayerid(), bean.getBegintime(), bean.getEndtime());
		if(CollectionUtils.isNotEmpty(plays)) {
			return ResponseEntityUtil.fail("直播员在该时段还有尚未结束的直播，请检查！");
		}

		
		Play play = new Play();
		play.setPicpath(bean.getPicpath());
		play.setRealaddress(bean.getRealaddress());
		play.setStatus(bean.getStatus());
		play.setTitle(bean.getTitle());
		play.setType(bean.getType());
		play.setUpdateBy(updateby);
		play.setId(bean.getId());
		play.setTitle(bean.getTitle());
		play.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
		play.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
		play.setPlayerid(bean.getPlayerid());

		int resultCount = playMapper.updateByPrimaryKeySelective(play);
		if (resultCount > 0) {
			return ResponseEntityUtil.success(playMapper.selectByPrimaryKey(play.getId()));
		}
		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity<String> delete(String updateby, Integer id) {
		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Play play = new Play();
		play.setId(id);
		play.setUpdateBy(updateby);
		play.setDelFlag(Const.DelFlagEnum.DELETED);
		int resultCount = playMapper.updateByPrimaryKeySelective(play);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<PlayVo> selectByKey(Integer id) {
		if (id == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Play play = playMapper.selectByPrimaryKey(id);
		if (play == null) {
			return ResponseEntityUtil.fail("该直播不存在或已删除");
		}
		PlayVo playVo = assemblePlayVo(play);
		return ResponseEntityUtil.success(playVo);
	}

	private PlayVo assemblePlayVo(Play play) {
		PlayVo playVo = new PlayVo();

		playVo.setId(play.getId());
		playVo.setTitle(play.getTitle());
		playVo.setType(play.getType());

		User user = userMapper.selectByPrimaryKey(play.getPlayerid());
		if (user != null) {
			playVo.setPlayerid(play.getPlayerid());
			PlayerVo playerVo = assemblePlayerVo(user);

			playVo.setPlayerVo(playerVo);
		}

		playVo.setRealaddress(play.getRealaddress());
		playVo.setPicpath(play.getPicpath());
		playVo.setBegintime(play.getBegintime());
		playVo.setEndtime(play.getEndtime());
		playVo.setReplayaddress(play.getReplayaddress());
		playVo.setStreamaddress(play.getStreamaddress());

		playVo.setStatus(play.getStatus());

		return playVo;
	}

	@Override
	public PageResponseBean<PlayVo> selectPlayListByPlayerId(int pageNum, int pageSize, Integer playerId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Play> plays = (List<Play>) playMapper.selectByPlayerId(playerId);
		List<PlayVo> playVos = Lists.newArrayList();
		for (Play play : plays) {
			PlayVo playVo2 = assemblePlayVo(play);
			playVos.add(playVo2);
		}
		PageInfo<PlayVo> pageInfo = new PageInfo<>();
		pageInfo.setList(playVos);
		return new PageResponseBean<PlayVo>(pageInfo);
	}

	@Override
	public ResponseEntity<List<PlayVo>> selectPlayListWithStatusAndPlayerId(Integer playerId, Integer status) {
		if (playerId == null || status == null) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		List<Play> plays = playMapper.selectByPlayIdAndStatus(playerId, status);
		if (plays.size() == 0) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_DATA_NOT_FOUND);
		}
		List<PlayVo> playVos = Lists.newArrayList();
		for (Play play : plays) {
			PlayVo playVo = assemblePlayVo(play);
			playVos.add(playVo);
		}
		return ResponseEntityUtil.success(playVos);
	}

	@Override
	public ResponseEntity<List<PlayVo>> selectPlayList() {

		List<Play> plays = playMapper.selectList();
		if (plays.size() == 0) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_DATA_NOT_FOUND);
		}
		List<PlayVo> playVos = Lists.newArrayList();
		for (Play play : plays) {
			PlayVo playVo2 = assemblePlayVo(play);
			playVos.add(playVo2);
		}
		return ResponseEntityUtil.success(playVos);
	}
	
	public ResponseEntity<List<PlayerVo>> selectPlayerList(){
		List<User> players= userMapper.selectPlayerList();
		List<PlayerVo> playerVos=Lists.newArrayList();
		for (User user : players) {
			PlayerVo playerVo= assemblePlayerVo(user);
			playerVos.add(playerVo);
		}
		return ResponseEntityUtil.success(playerVos);
	}
	
	private PlayerVo  assemblePlayerVo(User user) {
		PlayerVo playerVo = new PlayerVo();
		playerVo.setId(user.getId());
		playerVo.setAccount(user.getAccount());
		playerVo.setEmail(user.getEmail());
		playerVo.setPhone(user.getPhone());
		playerVo.setTruename(user.getTruename());
		return playerVo;
	}

}
