package com.media.ops.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.controller.request.SysparaAddRequestBean;
import com.media.ops.backend.controller.request.SysparaUptRequestBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.entity.Syspara;
import com.media.ops.backend.dao.mapper.SysparaMapper;
import com.media.ops.backend.service.SysparaService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.PlayVo;
import com.media.ops.backend.vo.SysparaVo;

@Service
public class SysparaServiceImpl implements SysparaService {
	
	@Autowired
	private SysparaMapper sysparaMapper;

	@Override
	public ResponseEntity addSyspara(String createby, SysparaAddRequestBean bean) {
	       if(StringUtils.isEmpty(createby) ||  StringUtils.isEmpty(bean.getName())) {
	    	   return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
	       }
	       Syspara syspara=new Syspara();
	       syspara.setName(bean.getName());
	       syspara.setValue(bean.getValue());
	       syspara.setNote(bean.getNote());
	       syspara.setCreateBy(createby);
	       syspara.setUpdateBy(createby);
	       
	       int resultCount= sysparaMapper.insert(syspara);
	       if(resultCount>0) {
	    	   Map<String, Object> result= Maps.newHashMap();
				result.put("sysPara", syspara);
				return ResponseEntityUtil.success(result);
	       }
	       
		return ResponseEntityUtil.fail("添加参数失败");
	}

	@Override
	public ResponseEntity<String> delSyspara(String updateby, Integer id) {
	       if(StringUtils.isEmpty(updateby) ||  id==null) {
	    	   return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
	       }
	       Syspara syspara=new Syspara();
	       syspara.setUpdateBy(updateby);
	       syspara.setDelFlag(Const.DelFlagEnum.DELETED);
	       syspara.setId(id);
	       
	       int resultCount= sysparaMapper.updateByPrimaryKeySelective(syspara);
	       return ResponseEntityUtil.delMessage(resultCount);
	}

	@Override
	public ResponseEntity<String> uptSyspara(String updateby, SysparaUptRequestBean bean) {
	       if(StringUtils.isEmpty(updateby)) {
	    	   return ResponseEntityUtil.fail(Errors.SYSTEM_NOT_LOGIN);
	       }
	       Syspara syspara=new Syspara();
	       syspara.setId(bean.getId());
	       syspara.setName(bean.getName());
	       syspara.setValue(bean.getValue());
	       syspara.setNote(bean.getNote());
	       syspara.setUpdateBy(updateby);
	       
	       int resultCount= sysparaMapper.updateByPrimaryKeySelective(syspara);
	       return ResponseEntityUtil.updMessage(resultCount);
	}

	@Override
	public ResponseEntity<List<SysparaVo>> selectAll() {
		List<Syspara> sysparas= sysparaMapper.selectAll();
		List<SysparaVo> sysparaVos=Lists.newArrayList();
		for (Syspara syspara : sysparas) {
			SysparaVo sysparaVo= assembleSysparaVo(syspara);
			sysparaVos.add(sysparaVo);
		}
	    return ResponseEntityUtil.success(sysparaVos);
	}
	
	private SysparaVo assembleSysparaVo(Syspara syspara) {
		SysparaVo sysparaVo=new SysparaVo();
		sysparaVo.setId(syspara.getId());
		sysparaVo.setName(syspara.getName());
		sysparaVo.setValue(syspara.getValue());
		sysparaVo.setNote(syspara.getNote());
		
		return sysparaVo;
	}

	@Override
	public ResponseEntity<SysparaVo> selectByName(String sysName) {
	
		if (StringUtils.isEmpty(sysName)) {
			return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
		}
		Syspara syspara= sysparaMapper.selectByName(sysName);
		if (syspara == null) {
			return ResponseEntityUtil.fail("没有这个参数");
		}
		SysparaVo sysparaVo= assembleSysparaVo(syspara);
		return ResponseEntityUtil.success(sysparaVo);
	}



	
}
