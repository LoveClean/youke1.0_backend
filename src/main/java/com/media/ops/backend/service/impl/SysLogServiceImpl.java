package com.media.ops.backend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.SyslogSearchReqeustBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.mapper.SysinterfaceMapper;
import com.media.ops.backend.dao.mapper.SyslogMapper;
import com.media.ops.backend.dao.mapper.UserMapper;
import com.media.ops.backend.dao.entity.Sysinterface;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.SysLogService;
import com.media.ops.backend.util.IdGen;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.SyslogVo;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SyslogMapper syslogMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private SysinterfaceMapper sysinterfaceMapper;
	
	@Override
	public int add(Syslog sysLog,User user) {
		sysLog.setId(IdGen.uuid());
		sysLog.setCreateBy(user.getAccount());
		sysLog.setCreateDate(new Date());
		syslogMapper.insertSelective(sysLog);
		return 0;
	}

	public PageResponseBean<SyslogVo> sysLog(PageRequestBean bean){
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Syslog> lists = syslogMapper.selectBySyslog();
		List<SyslogVo> syslogVos=Lists.newArrayList();
		for (Syslog syslog : lists) {
			SyslogVo syslogVo= assembleSyslogVo(syslog);
			syslogVos.add(syslogVo);
		}
		PageInfo pageInfo = new PageInfo(lists);
		pageInfo.setList(syslogVos);
		
		return new PageResponseBean<SyslogVo>(pageInfo);
	}
	
	public ResponseEntity<List<SyslogVo>> selectLogbyKey(SyslogSearchReqeustBean bean){
		String account= bean.getAccount();
		String email= bean.getEmail();

		if(StringUtils.isNotBlank(email)) {
			email=new StringBuilder().append("%").append(email).append("%").toString();
		}
		
		User user=  userMapper.selectByAccountEmail(StringUtils.isBlank(account)?null:account, StringUtils.isBlank(email)?null:email);

		if(user==null) {
			  return ResponseEntityUtil.fail("找不到相关用户");
		}
		List<Syslog> lists = syslogMapper.selectLogbyKey(user.getAccount());
		List<SyslogVo> syslogVos=Lists.newArrayList();
		for (Syslog syslog : lists) {
			SyslogVo syslogVo= assembleSyslogVo(syslog);
			syslogVos.add(syslogVo);
		}
		
		if(CollectionUtils.isEmpty(syslogVos)) {
			return ResponseEntityUtil.fail("没有该用户的操作记录");
		}
		return ResponseEntityUtil.success(syslogVos);
		
	}
	
	private SyslogVo assembleSyslogVo(Syslog syslog) {
		SyslogVo syslogVo=new SyslogVo();
		syslogVo.setId(syslog.getId());
		
		User user= userMapper.selectByName(syslog.getCreateBy());
		if(user!=null) {
			syslogVo.setAccount(user.getAccount());
			syslogVo.setName(user.getTruename());
		}
		
		syslogVo.setCreatetime(syslog.getCreateDate());
		syslogVo.setRemoteIP(syslog.getRemoteAddr());
		syslogVo.setUrl(syslog.getRequestUri());
		
		if(StringUtils.isNotEmpty(syslog.getRequestUri())) {
			System.out.println(syslog.getRequestUri());
			Sysinterface sysinterface= sysinterfaceMapper.selectByValue(syslog.getRequestUri());
			if(sysinterface!=null) {
				syslogVo.setRequesturl(sysinterface.getName());
			}else {
				syslogVo.setRequesturl(syslog.getRequestUri());
			}
		}
		
		
		
		return syslogVo;

	}

}
