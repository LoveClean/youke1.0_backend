package com.media.ops.backend.service;

import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.UserVo;


public interface UserService {
	ResponseEntity<User> adminLogin(String username, String password);

	ResponseEntity add(User user);

	ResponseEntity<String> checkValid(String str, String field, int type);

	ResponseEntity<String> resetPassword(String passwordOld, String passwordNew, User user);

	ResponseEntity<User> updateInformation(User user);
	
	public ResponseEntity<User> updateSelfInfo(User user);

	ResponseEntity<UserVo> getInformation(Integer id);
	
	ResponseEntity<User> getUserByAccountEmail(String account, String email);

	ResponseEntity<String> checkAdminRole(User user);
	
	PageResponseBean<UserVo>  getUserList(int pageNum, int pageSize);
	
	ResponseEntity<String>  updateStatusById(String account, Integer status);
	
	
	
	///////////////////////////通过密保问题修改密码的方法////////////////////////////////////////
	ResponseEntity<String> selectQuestion(String username, int type);

	ResponseEntity<String> checkAnswer(String username, String question, String answer);

	ResponseEntity<String> forgetResetPassword(String username, String newPassword, String forgetToken,int type);

}
