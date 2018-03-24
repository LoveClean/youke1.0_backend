package com.media.ops.backend.service;

import java.util.List;

import com.media.ops.backend.controller.request.SysparaAddRequestBean;
import com.media.ops.backend.controller.request.SysparaUptRequestBean;
import com.media.ops.backend.dao.entity.Syspara;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.SysparaVo;

public interface SysparaService {
	
	public ResponseEntity addSyspara(String createby,SysparaAddRequestBean bean);
	
	public ResponseEntity<String> delSyspara(String updateby,Integer id);
	
	public ResponseEntity<String> uptSyspara(String updateby,SysparaUptRequestBean bean);
	
	public ResponseEntity<List<SysparaVo>> selectAll();
	

}
