package com.media.ops.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.AddeliveryAddRequestBean;
import com.media.ops.backend.controller.request.AddeliveryEmergentRequestBean;
import com.media.ops.backend.controller.request.AddeliveryUptRequestBean;
import com.media.ops.backend.controller.request.BatchAddeliveryAddRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.*;
import com.media.ops.backend.dao.mapper.*;
import com.media.ops.backend.exception.BusinessException;
import com.media.ops.backend.service.AddeliveryService;
import com.media.ops.backend.util.DateUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.util.StringUtil;
import com.media.ops.backend.vo.AddeliveryDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<Addelivery> addeliverieList=new ArrayList<Addelivery>();
		List<Integer> groupIds= bean.getGroupid();
		for (Integer groupId : groupIds) {
			Addelivery addelivery = new Addelivery();
			addelivery.setAdid(bean.getAdid());
			addelivery.setAdtype(bean.getAdtype());
			addelivery.setDelivertype(bean.getDelivertype());
			addelivery.setAreaid(bean.getAreaid());
			addelivery.setGroupid(groupId);
			addelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
			addelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
			addelivery.setCreateBy(createby);
			addelivery.setUpdateBy(createby);
			
			addeliverieList.add(addelivery);
		}
		
		int resultCount= addeliveryMapper.batchInsert(addeliverieList);
		if(resultCount< addeliverieList.size()) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("newData", addeliverieList);
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
	public PageResponseBean<AddeliveryDetailVo> selectList(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Addelivery> addeliveries = addeliveryMapper.selectList();
		List<AddeliveryDetailVo> addeliveryDetailVos = Lists.newArrayList();
		for (Addelivery addelivery : addeliveries) {
			AddeliveryDetailVo addeliveryDetailVo = assembleAddeliveryDetailVo(addelivery);
			addeliveryDetailVos.add(addeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(addeliveries);
		pageInfo.setList(addeliveryDetailVos);
		PageResponseBean<AddeliveryDetailVo> list = new PageResponseBean<AddeliveryDetailVo>(pageInfo);
		list.setCode(0);
		list.setHttpStatus(200);
		return list;
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
	public PageResponseBean<AddeliveryDetailVo> selectDeliveryByKeys( String cityId,  String areaId,  Integer deliveryType,
																	  Integer groupId,  Integer pageNum,  Integer pageSize) {
		String cityId2 = cityId;
		String areaId2= areaId;
		Integer deliveryType2 = deliveryType;
		Integer groupId2= groupId;
		Integer pageNum2= pageNum;
		Integer pageSize2= pageSize;
		
		if(StringUtils.isNotBlank(cityId2) && StringUtils.isBlank(areaId2)) {
			cityId2 = cityId2.substring(0, 4);
			areaId2=new StringBuilder().append(cityId2).append("%").toString();
		}
		
		PageHelper.startPage(pageNum2, pageSize2);
		
		List<Addelivery> addeliveries= addeliveryMapper.selectByKeys(
						StringUtils.isBlank(areaId2)?null:areaId2,  deliveryType2==0?0:1, groupId2==0?null:groupId2);
		List<AddeliveryDetailVo> addeliveryDetailVos = Lists.newArrayList();
		for (Addelivery addelivery : addeliveries) {
			AddeliveryDetailVo addeliveryDetailVo = assembleAddeliveryDetailVo(addelivery);
			addeliveryDetailVos.add(addeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(addeliveries);
		pageInfo.setList(addeliveryDetailVos);
		PageResponseBean<AddeliveryDetailVo> list = new PageResponseBean<AddeliveryDetailVo>(pageInfo);
		list.setHttpStatus(200);
		list.setCode(0);
		return list;

	}


	@Override
	public ResponseEntity<String> BatchAddelivery(BatchAddeliveryAddRequestBean bean, String createby) {
		List<String> list = bean.getAreaAndBuildingList();
		List<Addelivery> addeliverieList=new ArrayList<Addelivery>();
		if(bean.getDelivertype()==1){//按楼宇投放
            for (String areaAndBuilding : list) {
                Addelivery addelivery = new Addelivery();
                String[] detailAreaArr = areaAndBuilding.split("-");
                String areaId = detailAreaArr[0];
                Integer buildingId = Integer.parseInt(detailAreaArr[1]);
                addelivery.setAdid(bean.getAdid());
                addelivery.setAdtype(bean.getAdtype());
                addelivery.setDelivertype(bean.getDelivertype());
                addelivery.setAreaid(areaId);
                addelivery.setGroupid(buildingId);
                addelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
                addelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
                addelivery.setCreateBy(createby);
                addelivery.setUpdateBy(createby);

                addeliverieList.add(addelivery);
            }
        }else if(bean.getDelivertype()==0){//按设备分组投放
            for (String areaAndBuilding : list) {
                Addelivery addelivery = new Addelivery();

                addelivery.setAdid(bean.getAdid());
                addelivery.setAdtype(bean.getAdtype());
                addelivery.setDelivertype(bean.getDelivertype());
                addelivery.setAreaid(areaAndBuilding);
                addelivery.setGroupid(Integer.parseInt(bean.getGroupid()));
                addelivery.setBegintime(DateUtil.stringToDate(bean.getBegintime(), DateUtil.DEFAULT_PATTERN));
                addelivery.setEndtime(DateUtil.stringToDate(bean.getEndtime(), DateUtil.DEFAULT_PATTERN));
                addelivery.setCreateBy(createby);
                addelivery.setUpdateBy(createby);

                addeliverieList.add(addelivery);
            }
        }

		int resultCount= addeliveryMapper.batchInsert(addeliverieList);
		if(resultCount< addeliverieList.size()) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_INSERT_FAIL);
		}
		return ResponseEntityUtil.success("批量投放广告成功");
	}

	@Override
	public ResponseEntity<String> BatchDeldelivery(String ids, String updateby) {
		if (StringUtil.isEmpty(ids) || StringUtil.isEmpty(updateby)) {
			return ResponseEntityUtil.fail("数据不完整，ids与操作人不能为空");
		}
		System.out.println(ids);
		String[] temp = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			Integer id = Integer.parseInt(temp[i]);
			list.add(id);
		}
		int returnCount = addeliveryMapper.batchDelete(list,updateby);
		if (returnCount < 0) {
			return ResponseEntityUtil.fail("批量删除失败");
		}
		return ResponseEntityUtil.success("批量删除成功");
	}

	@Override
	public PageResponseBean<AddeliveryDetailVo> selectDeliveryByKeys2(String areaId,Integer deliveryType,
																	  Integer groupId,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if (deliveryType==2 && groupId!=0) {
			throw new BusinessException(10,"当delivertype没有时，则groupid也将没有");
		}
		List<Addelivery> addeliveries= addeliveryMapper.selectByKeys2(
				StringUtils.isBlank(areaId)?null:areaId, deliveryType==0?0:(deliveryType==1?1:2),groupId==0?null:groupId);
		List<AddeliveryDetailVo> addeliveryDetailVos = Lists.newArrayList();
		for (Addelivery addelivery : addeliveries) {
			AddeliveryDetailVo addeliveryDetailVo = assembleAddeliveryDetailVo(addelivery);
			addeliveryDetailVos.add(addeliveryDetailVo);
		}
		PageInfo pageInfo = new PageInfo(addeliveries);
		pageInfo.setList(addeliveryDetailVos);
		PageResponseBean<AddeliveryDetailVo> list = new PageResponseBean<AddeliveryDetailVo>(pageInfo);
		list.setHttpStatus(200);
		list.setCode(0);
		return list;
	}

}
