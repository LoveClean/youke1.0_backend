package com.media.ops.backend.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.media.ops.backend.cache.TokenCache;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.dao.mapper.UserMapper;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.MD5Util;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;

@Service
public class UserSericeImpl implements UserService {

    @Resource
    private UserMapper userMapper;

	@Override
	public ResponseEntity<User> login(String account, String password) {
		// TODO Auto-generated method stub
		int resultCount=userMapper.checkAccount(account);
		if(resultCount==0) {
			return ResponseEntityUtil.fail("用户不存在");
		}
		//todo MD5加密 
		String MD5Password= MD5Util.MD5(password);
		User user= userMapper.selectLogin(account, MD5Password);
		if(user==null) {
			return ResponseEntityUtil.fail("密码错误");
		}
		user.setPassword(StringUtils.EMPTY);
		return ResponseEntityUtil.success(user);
	}

	@Override
	public ResponseEntity<String> register(User user) {
		ResponseEntity<String> validResponse= this.checkValid(user.getAccount(), Const.USERNAME);
		if(! validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse= this.checkValid(user.getPassword(), Const.EMAIL);
		if(!validResponse.isSuccess()) {
			return validResponse;
		}
		user.setType(Const.Role.ROLE_HOST);
		user.setPassword(MD5Util.MD5(user.getPassword()));
		
		int resultCount= userMapper.insert(user);
		if(resultCount==0) {
			return ResponseEntityUtil.fail("注册失败");
		}
		
		return ResponseEntityUtil.success("注册成功");
	}

	@Override
	public ResponseEntity<String> checkValid(String str, String type) {
       if(StringUtils.isNotBlank(type)) {
    	   if(Const.USERNAME.equals(type)) {
    		   int resultCount= userMapper.checkAccount(str);
    		   if(resultCount>0) {
    				return ResponseEntityUtil.fail("用户已存在");
    			}
    	   }
    	   
    	   if(Const.EMAIL.equals(type)) {
    		   int resultCount= userMapper.checkEmail(str);
    			if(resultCount>0) {
    				return ResponseEntityUtil.fail("email已存在");
    			}    		   
    	   }
    	   
       }else {
    	   return ResponseEntityUtil.fail("参数错误");
       }

		return ResponseEntityUtil.success("校验成功");
	}

	@Override
	public ResponseEntity<String> selectQuestion(String account) {
		ResponseEntity<String> validResponse= this.checkValid(account, Const.USERNAME);
		if( validResponse.isSuccess()) {
			return ResponseEntityUtil.fail("用户名不存在");
		}
		String question= userMapper.selectQuestionByAccount(account);
		if(StringUtils.isNotBlank(question)) {
			return ResponseEntityUtil.success(question);
		}
		return ResponseEntityUtil.fail("找回密码的问题是空的");

	}

	@Override
	public ResponseEntity<String> checkAnswer(String account, String question, String answer) {
		int resultCount= userMapper.checkAnswer(account, question, answer);
		if(resultCount>0) {
			String forgetToken= UUID.randomUUID().toString();
			TokenCache.setKey(TokenCache.TOKEN_PREFIX+account, forgetToken);
			return ResponseEntityUtil.success(forgetToken);
			
		}
		return ResponseEntityUtil.fail("问题的答案错误");
	}

	@Override
	public ResponseEntity<String> forgetResetPassword(String account, String newPassword, String forgetToken) {
		if(StringUtils.isBlank(forgetToken)) {
			return ResponseEntityUtil.fail("参数错误，没有传递Token");
		}
		
		ResponseEntity validResponse= this.checkValid(account, Const.USERNAME);
		if(validResponse.isSuccess()) {
			return ResponseEntityUtil.fail("用户不存在");
		}
		
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+account);
		if(StringUtils.isBlank(token)) {
			return ResponseEntityUtil.fail("Token无效或已过期");
		}
		if(StringUtils.equals(forgetToken, token)) {
			String MD5Password= MD5Util.MD5(newPassword);
			int resultCount= userMapper.updatePasswordByAccount(account, MD5Password);
			if(resultCount>0) {
				return ResponseEntityUtil.success("修改密码成功");
			}
		}else {
			return ResponseEntityUtil.fail("Token错误，请重新获取Token");
		}
		return ResponseEntityUtil.fail("密码修改错误");
	}

	@Override
	public ResponseEntity<String> resetPassword(String passwordOld, String passwordNew, User user) {
		//防止横向越权
		int resultCount= userMapper.checkPassword(MD5Util.MD5(passwordOld), user.getId());
		if(resultCount==0) {
			return ResponseEntityUtil.fail("旧密码错误");
		}
		user.setPassword(MD5Util.MD5(passwordNew));
		int updateCount= userMapper.updateByPrimaryKeySelective(user);
		if(updateCount>0) {
			return ResponseEntityUtil.success("密码更新成功");
		}
		return ResponseEntityUtil.fail("密码更新失败");
	}

	
	@Override
	public ResponseEntity<User> updateInformation(User user) {
		int resultCount= userMapper.checkEmailByUserId(user.getEmail(), user.getId());
		if(resultCount>0) {
			return ResponseEntityUtil.fail("Email已存在，请修改");
		}
		User updateUser= new User();
		updateUser.setId(user.getId());
		updateUser.setEmail(user.getEmail());
		updateUser.setPhone(user.getPhone());
		updateUser.setQuestion(user.getQuestion());
		updateUser.setAnswer(user.getAnswer());
		
		int updateCount= userMapper.updateByPrimaryKeySelective(updateUser);
		if(updateCount>0) {
			return ResponseEntityUtil.success(updateUser);
		}
		return ResponseEntityUtil.fail("更新个人信息失败");
	}


	@Override
	public ResponseEntity<User> getInformation(Integer id) {
		User user= userMapper.selectByPrimaryKey(id);
		if(user==null) {
			return ResponseEntityUtil.fail("找不到该用户");
		}
		user.setPassword(StringUtils.EMPTY);
		return ResponseEntityUtil.success(user);
	}

	@Override
	public ResponseEntity<String> checkAdminRole(User user) {
		if(user !=null && user.getType().intValue()== Const.Role.ROLE_ADMIN) {
			return ResponseEntityUtil.success();
		}
		return ResponseEntityUtil.fail("不是管理员");
	}



}
