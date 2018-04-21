package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.AdDeliverySearchRequestBean;
import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Ad;
import com.media.ops.backend.dao.entity.Addelivery;
import com.media.ops.backend.dao.entity.Area;
import com.media.ops.backend.dao.entity.Building;
import com.media.ops.backend.dao.entity.City;
import com.media.ops.backend.dao.entity.Devicegroup;
import com.media.ops.backend.dao.entity.Playdelivery;
import com.media.ops.backend.dao.mapper.AdMapper;
import com.media.ops.backend.dao.mapper.AddeliveryMapper;
import com.media.ops.backend.dao.mapper.AreaMapper;
import com.media.ops.backend.dao.mapper.BuildingMapper;
import com.media.ops.backend.dao.mapper.CityMapper;
import com.media.ops.backend.dao.mapper.DevicegroupMapper;
import com.media.ops.backend.service.AddeliveryService;
import com.media.ops.backend.util.DateUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AddeliveryDetailVo;

@Service
public class AddeliveryServiceImpl implements AddeliveryService {

	@Autowired
	private AddeliveryMapper addeliveryMapper;
	@Autowired
	private AdMapper adMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DevicegroupMapper devicegroupMapper;
	@Autowired
	private BuildingMapper buildingMapper;

	public ResponseEntity createAdDelivery(String createby, AddeliveryAddRequestBean bean) {

		Addelivery addelivery = new Addelivery();
		addelivery.setAdid(bean.getAdid());
		addelivery.setAdtype(bean.getAdtype());
		addelivery.setDelivertype(bean.getDelivertype());
		addelivery.setAreaid(bean.getAreaid());
		addelivery.setGroupid(bean.getGroupid());
		addelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
		addelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
		addelivery.setCreateBy(createby);
		addelivery.setUpdateBy(createby);

		int resultCount = addeliveryMapper.insertSelective(addelivery);

		if (resultCount <= 0) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("newData", addelivery);
		return ResponseEntityUtil.success(result);
	}

	public ResponseEntity updateAdDelivery(String updateby, AddeliveryUptRequestBean bean) {
		Addelivery uptAddelivery = new Addelivery();
		uptAddelivery.setId(bean.getId());
		uptAddelivery.setAdtype(bean.getAdtype());
		uptAddelivery.setDelivertype(bean.getDelivertype());
		uptAddelivery.setAreaid(bean.getAreaid());
		uptAddelivery.setGroupid(bean.getGroupid());
		uptAddelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
		uptAddelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
		uptAddelivery.setUpdateBy(updateby);

		int resultCount = addeliveryMapper.updateByPrimaryKeySelective(uptAddelivery);
		if (resultCount > 0) {
			return ResponseEntityUtil.success(addeliveryMapper.selectByPrimaryKey(uptAddelivery.getId()));
		}

		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public ResponseEntity emergentAdDelivery(String account, AddeliveryEmergentRequestBean bean) {
		Addelivery uptAddelivery = new Addelivery();
		uptAddelivery.setId(bean.getId());
		uptAddelivery.setAdtype(Const.AdTypeEnum.EMERGENT_PLAY);
		uptAddelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
		uptAddelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
		uptAddelivery.setUpdateBy(account);

		int resultCount = addeliveryMapper.updateByPrimaryKeySelective(uptAddelivery);

		if (resultCount > 0) {
			return ResponseEntityUtil.success(addeliveryMapper.selectByPrimaryKey(uptAddelivery.getId()));
		}

		return ResponseEntityUtil.fail(Errors.SYSTEM_UPDATE_ERROR);
	}

	@Override
	public PageResponseBean<AddeliveryDetailVo> selectList(PageRequestBean bean) {
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Addelivery> addeliveries = addeliveryMapper.selectList();
		List<AddeliveryDetailVo> addeliveryDetailVos = Lists.newArrayList();
		for (Addelivery addelivery : addeliveries) {
			AddeliveryDetailVo addeliveryDetailVo = assembleAddeliveryDetailVo(addelivery);
			addeliveryDetailVos.add(addeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(addeliveries);
		pageInfo.setList(addeliveryDetailVos);

		return new PageResponseBean<AddeliveryDetailVo>(pageInfo);
	}

	private AddeliveryDetailVo assembleAddeliveryDetailVo(Addelivery addelivery) {
		AddeliveryDetailVo addeliveryDetailVo = new AddeliveryDetailVo();
		addeliveryDetailVo.setId(addelivery.getId());

		Ad ad = adMapper.selectByPrimaryKey(addelivery.getAdid());
		if (ad != null) {
			addeliveryDetailVo.setAdid(ad.getId());
			addeliveryDetailVo.setAdName(ad.getName());
		}

		addeliveryDetailVo.setAdtype(addelivery.getAdtype());
		addeliveryDetailVo.setDelivertype(addelivery.getDelivertype());

		// 获取区域名称，城市名称
		Area area = areaMapper.selectByAreaId(addelivery.getAreaid());
		if (area != null) {
			addeliveryDetailVo.setAreaid(area.getAreaid());
			addeliveryDetailVo.setAreaName(area.getArea());

			City city = cityMapper.selectByCityId(addelivery.getAreaid().substring(0, 4) + "00");
			if (city != null) {
				addeliveryDetailVo.setCityName(city.getCity());
			}
		}
		addeliveryDetailVo.setBuildingOrGroupId(addelivery.getGroupid());
		if (addelivery.getDelivertype().intValue() == Const.AdDeliveryTypeEnum.BY_BUILDING) {
			Building building = buildingMapper.selectByPrimaryKey(addelivery.getGroupid());
			addeliveryDetailVo.setBuildingOrGroupName(building.getName());
		} else {
			Devicegroup devicegroup = devicegroupMapper.selectByPrimaryKey(addelivery.getGroupid());
			addeliveryDetailVo.setBuildingOrGroupName(devicegroup.getName());
		}
		addeliveryDetailVo.setBegintime(addelivery.getBegintime());
		addeliveryDetailVo.setEndtime(addelivery.getEndtime());

		return addeliveryDetailVo;
	}

	@Override
	public ResponseEntity<String> delAddelivery(Integer id, String updateby) {
		if(id==null) {
			return ResponseEntityUtil.fail("没有该条投放记录");
		}
		int resultCount=addeliveryMapper.deleteByPrimaryKey(id);
		return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public PageResponseBean<AddeliveryDetailVo> selectDeliveryByKeys(AdDeliverySearchRequestBean bean) {
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
		
		List<Addelivery> addeliveries= addeliveryMapper.selectByKeys(
						StringUtils.isBlank(areaId)?null:areaId,  deliveryType==0?0:1, groupId==0?null:groupId);
		List<AddeliveryDetailVo> addeliveryDetailVos = Lists.newArrayList();
		for (Addelivery addelivery : addeliveries) {
			AddeliveryDetailVo addeliveryDetailVo = assembleAddeliveryDetailVo(addelivery);
			addeliveryDetailVos.add(addeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(addeliveries);
		pageInfo.setList(addeliveryDetailVos);

		return new PageResponseBean<AddeliveryDetailVo>(pageInfo);

	}

}
