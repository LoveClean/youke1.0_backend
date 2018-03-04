package com.media.ops.backend.service;

import com.github.pagehelper.PageInfo;
import com.media.ops.backend.controller.response.PageResponseBean;
import  com.media.ops.backend.dao.entity.User;
import  com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.UserVo;

public interface UserServiceXX {

	ResponseEntity<User> login(String username, String password);

	ResponseEntity<String> add(User user);

	ResponseEntity<String> checkValid(String str, String type);

	ResponseEntity<String> selectQuestion(String username);

	ResponseEntity<String> checkAnswer(String username, String question, String answer);

	ResponseEntity<String> forgetResetPassword(String username, String newPassword, String forgetToken);

	ResponseEntity<String> resetPassword(String passwordOld, String passwordNew, User user);

	ResponseEntity<User> updateInformation(User user);

	ResponseEntity<User> getInformation(Integer id);

	ResponseEntity<String> checkAdminRole(User user);
	
	PageResponseBean<UserVo>  getUserList(int pageNum, int pageSize);

}
