package com.media.ops.backend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.mapper.SyslogMapper;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.SysLogService;
import com.media.ops.backend.util.IdGen;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SyslogMapper syslogMapper;
	
	@Override
	public int add(Syslog sysLog,User user) {
		sysLog.setId(IdGen.uuid());
		sysLog.setCreateBy(user.getAccount());
		sysLog.setCreateDate(new Date());
		syslogMapper.insertSelective(sysLog);
		return 0;
	}

	public PageResponseBean<Syslog> sysLog(PageRequestBean bean){
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		List<Syslog> lists = syslogMapper.selectBySyslog();
		PageInfo<Syslog> pageInfo = new PageInfo<Syslog>(lists);
		return new PageResponseBean<Syslog>(pageInfo);
	}

}
