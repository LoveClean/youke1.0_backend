package com.media.ops.backend.service;


import java.util.List;

import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.SyslogSearchReqeustBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.SyslogVo;

public interface SysLogService {
	
	public int add(Syslog sysLog,User user);
	
	public PageResponseBean<SyslogVo> sysLog(PageRequestBean bean);
	
	public ResponseEntity<List<SyslogVo>> selectLogbyKey(SyslogSearchReqeustBean bean);
}
