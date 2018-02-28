package com.media.ops.backend.service;


import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;

public interface SysLogService {
	
	public int add(Syslog sysLog,User user);
	
	public PageResponseBean<Syslog> sysLog(PageRequestBean bean);
}
