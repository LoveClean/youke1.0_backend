package com.media.ops.backend.service;


import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.vo.SyslogVo;

public interface SysLogService {
	
	public int add(Syslog sysLog, User user);
	
	public PageResponseBean<SyslogVo> sysLog(Integer pageNum, Integer pageSize);

	public PageResponseBean<SyslogVo> selectLogbyKey(String account, String email, Integer pageNum, Integer pageSize);

}
