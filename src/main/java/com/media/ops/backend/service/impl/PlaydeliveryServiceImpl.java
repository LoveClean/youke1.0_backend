package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PlayDeliverySearchRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryAddRequestBean;
import com.media.ops.backend.controller.request.PlaydeliveryUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.dao.entity.Building;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.entity.Playdelivery;
import com.media.ops.backend.dao.mapper.AreaMapper;
import com.media.ops.backend.dao.mapper.BuildingMapper;
import com.media.ops.backend.dao.mapper.CityMapper;
import com.media.ops.backend.dao.mapper.DevicegroupMapper;
import com.media.ops.backend.dao.mapper.PlayMapper;
import com.media.ops.backend.dao.mapper.PlaydeliveryMapper;
import com.media.ops.backend.service.PlaydeliveryService;
import com.media.ops.backend.util.DateUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.DeviceListVo;
import com.media.ops.backend.vo.PlaydeliveryDetailVo;
import com.media.ops.backend.vo.VmPlayDeliveryVo;

@Service
public class PlaydeliveryServiceImpl implements PlaydeliveryService {

	@Autowired
	private PlaydeliveryMapper playdeliveryMapper;
	@Autowired
	private PlayMapper playMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DevicegroupMapper devicegroupMapper;
	@Autowired
	private BuildingMapper buildingMapper;

	public ResponseEntity createPlayDelivery(String createby, PlaydeliveryAddRequestBean bean) {
		
		if(bean.getPlayid()==null) {
			return ResponseEntityUtil.fail("没有选择直播");
		}
		Play play= playMapper.selectByPrimaryKey(bean.getPlayid());

		if(play==null) {
			return ResponseEntityUtil.fail("没有查询到直播信息");
		}
		if(play.getStatus()>=3) {
			return ResponseEntityUtil.fail("该直播已经结束，无法创建投放");
		}
		
		
		List<VmPlayDeliveryVo> vmPlayDeliveryVos= playdeliveryMapper.selectByGroupBeginEndTime(
				bean.getDelivertype(), bean.getAreaid(), bean.getGroupid(), 
				DateUtil.format(play.getBegintime(), DateUtil.DEFAULT_PATTERN), DateUtil.format(play.getEndtime(), DateUtil.DEFAULT_PATTERN));
		
		if(CollectionUtils.isNotEmpty(vmPlayDeliveryVos)) {
			return ResponseEntityUtil.fail("选择的投放设备在该时段还有直播没结束！");
		}
		
		Playdelivery addDelivery = new Playdelivery();
		addDelivery.setPlayid(bean.getPlayid());
		addDelivery.setDelivertype(bean.getDelivertype());
		addDelivery.setAreaid(bean.getAreaid());
		addDelivery.setGroupid(bean.getGroupid());

		addDelivery.setCreateBy(createby);
		addDelivery.setUpdateBy(createby);

		int resultCount = playdeliveryMapper.insertSelective(addDelivery);

		if (resultCount <= 0) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("newData", addDelivery);
		return ResponseEntityUtil.success(result);
	}

	public ResponseEntity updatePlayDelivery(String updateby, PlaydeliveryUptRequestBean bean) {
		
		if(bean.getPlayid()==null) {
			return ResponseEntityUtil.fail("没有选择直播");
		}
		Play play= playMapper.selectByPrimaryKey(bean.getPlayid());

		if(play==null) {
			return ResponseEntityUtil.fail("没有查询到直播信息");
		}
		if(play.getStatus()>=3) {
			return ResponseEntityUtil.fail("该直播已经结束，无法修改");
		}
		
		List<VmPlayDeliveryVo> vmPlayDeliveryVos= playdeliveryMapper.selectByGroupBeginEndTime(
				bean.getDelivertype(), bean.getAreaid(), bean.getGroupid(), 
				play.getBegintime().toString(), play.getEndtime().toString());
		
		if(CollectionUtils.isNotEmpty(vmPlayDeliveryVos)) {
			return ResponseEntityUtil.fail("选择的投放设备在该时段还有直播没结束！");
		}		
		
		
		Playdelivery uptDelivery = new Playdelivery();
		uptDelivery.setId(bean.getId());
		uptDelivery.setPlayid(bean.getPlayid());
		uptDelivery.setDelivertype(bean.getDelivertype());
		uptDelivery.setAreaid(bean.getAreaid());
		uptDelivery.setGroupid(bean.getGroupid());
		uptDelivery.setUpdateBy(updateby);

		int resultCount = playdeliveryMapper.updateByPrimaryKeySelective(uptDelivery);
		if (resultCount > 0) {
			return ResponseEntityUtil.success(playdeliveryMapper.selectByPrimaryKey(uptDelivery.getId()));
		}

		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public PageResponseBean<PlaydeliveryDetailVo> selectList(PageRequestBean bean) {
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Playdelivery> playdeliveries = playdeliveryMapper.selectList();
		List<PlaydeliveryDetailVo> playdeliveryDetailVos = Lists.newArrayList();
		for (Playdelivery playdelivery : playdeliveries) {
			PlaydeliveryDetailVo playdeliveryDetailVo = assemblePlaydeliveryDetailVo(playdelivery);
			playdeliveryDetailVos.add(playdeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(playdeliveries);
		pageInfo.setList(playdeliveryDetailVos);

		return new PageResponseBean<PlaydeliveryDetailVo>(pageInfo);
	}

	private PlaydeliveryDetailVo assemblePlaydeliveryDetailVo(Playdelivery playdelivery) {
		PlaydeliveryDetailVo playdeliveryDetailVo = new PlaydeliveryDetailVo();
		playdeliveryDetailVo.setId(playdelivery.getId());
		

		Play play = playMapper.selectByPrimaryKey(playdelivery.getPlayid());
		if (play != null) {
			playdeliveryDetailVo.setPlayid(play.getId());
			playdeliveryDetailVo.setPlayTitle(play.getTitle());
			playdeliveryDetailVo.setPlayStatus(play.getStatus());
		}

		playdeliveryDetailVo.setDelivertype(playdelivery.getDelivertype());

		// 获取区域名称，城市名称
		Area area = areaMapper.selectByAreaId(playdelivery.getAreaid());
		if (area != null) {
			playdeliveryDetailVo.setAreaid(area.getAreaid());
			playdeliveryDetailVo.setAreaName(area.getArea());

			City city = cityMapper.selectByCityId(playdelivery.getAreaid().substring(0, 4) + "00");
			if (city != null) {
				playdeliveryDetailVo.setCityName(city.getCity());
			}
		}
		playdeliveryDetailVo.setBuildingOrGroupId(playdelivery.getGroupid());
		if (playdelivery.getDelivertype().intValue() == Const.AdDeliveryTypeEnum.BY_BUILDING) {
			Building building = buildingMapper.selectByPrimaryKey(playdelivery.getGroupid());
			playdeliveryDetailVo.setBuildingOrGroupName(building.getName());
		} else {
			Devicegroup devicegroup = devicegroupMapper.selectByPrimaryKey(playdelivery.getGroupid());
			playdeliveryDetailVo.setBuildingOrGroupName(devicegroup.getName());
		}


		return playdeliveryDetailVo;
	}

	@Override
	public ResponseEntity<String> delPlaydelivery(Integer id, String updateby) {
		if(id==null) {
			return ResponseEntityUtil.fail("没有该条投放记录");
		}
		int resultCount=playdeliveryMapper.deleteByPrimaryKey(id);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public PageResponseBean<PlaydeliveryDetailVo> selectDeliveryByKeys(PlayDeliverySearchRequestBean bean) {
		String cityId= bean.getCityId();
		String areaId=bean.getAreaId();
		Integer deliveryType= bean.getDeliveryType();
		Integer groupId= bean.getGroupId();
		Integer pageNum=bean.getPageNum();
		Integer pageSize=bean.getPageSize();
		
		if(StringUtils.isNotBlank(cityId) && StringUtils.isBlank(areaId)) {
			cityId = cityId.substring(0, 4);
			areaId=new StringBuilder().append(cityId).append("%").toString();
		}
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Playdelivery> playdeliveries= playdeliveryMapper.selectByKeys(
						StringUtils.isBlank(areaId)?null:areaId,  deliveryType==0?0:1, groupId==0?null:groupId);
		
		List<PlaydeliveryDetailVo> playdeliveryDetailVos = Lists.newArrayList();
		for (Playdelivery playdelivery : playdeliveries) {
			PlaydeliveryDetailVo playdeliveryDetailVo = assemblePlaydeliveryDetailVo(playdelivery);
			playdeliveryDetailVos.add(playdeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(playdeliveries);
		pageInfo.setList(playdeliveryDetailVos);

		return new PageResponseBean<PlaydeliveryDetailVo>(pageInfo);
	}


}
