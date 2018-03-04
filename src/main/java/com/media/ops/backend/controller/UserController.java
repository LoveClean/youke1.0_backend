package com.media.ops.backend.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.contants.Const;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.SysLogService;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="管理员操作接口",produces = "application/json")
@RestController
@RequestMapping("/manager/")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SysLogService sysLogService;
	
	@ApiOperation(value = "登录接口",notes = "用户登录，username可为账号、手机或邮箱")
	@PostMapping(value="login.do")
	@ResponseBody
	public ResponseEntity<User> login(String username, String password, 
			HttpSession session ) {
		ResponseEntity<User> response=userService.login(username, password);
		if(response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}
	
	@ApiOperation(value = "登出接口",notes = "用户登出")
	@PostMapping(value="logout.do")	
	public ResponseEntity<String> logout(HttpSession session){
		session.removeAttribute(Const.CURRENT_USER);
		return ResponseEntityUtil.success();
	}
	
	@ApiOperation(value = "添加管理员接口",notes = "添加管理员")
	@PostMapping(value="add.do")	
	public ResponseEntity<String> add(User user){
		return userService.add(user);
	}
	
	@ApiOperation(value = "用户校验接口",notes = "信息校验")
	@PostMapping(value="check_valid.do")
	public ResponseEntity<String> checkValid(String str, String type){
		return userService.checkValid(str, type);
	}
	
	@ApiOperation(value = "获取管理员信息接口",notes = "获取管理员信息")
	@PostMapping(value="get_user_info.do")
	public ResponseEntity<User> getUserInfo(HttpSession session){
		User user= (User) session.getAttribute(Const.CURRENT_USER);
		if(user !=null) {
			return ResponseEntityUtil.success(user);
		}
		return ResponseEntityUtil.fail("用户未登录，无法获取当前用户信息");
	}
	
	@ApiOperation(value = "密保提示问题接口",notes = "获取密保提示问题")
	@PostMapping(value="forget_get_question.do")
	public ResponseEntity<String> selectQuestion(String username) {
		return userService.selectQuestion(username);
	}
	
	@ApiOperation(value = "确认密保问题答案接口",notes = "确认密保问题答案")
	@PostMapping(value="forget_check_answer.do")
	public ResponseEntity<String> forgetCheckAnswer(String username, String question, String answer){
		return userService.checkAnswer(username, question, answer);
	}
	
	@ApiOperation(value = "重置密码接口",notes = "重置密码")
	@PostMapping(value="forget_reset_password.do")
	public ResponseEntity<String> forgetResetPassword(String username, String newPassword, String forgetToken){
		return userService.forgetResetPassword(username, newPassword, forgetToken);
	}
	
	@ApiOperation(value = "登录状态下重置密码接口",notes = "登录状态下重置密码")
	@PostMapping(value="reset_password.do")	
	public ResponseEntity<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
	       User user= (User)session.getAttribute(Const.CURRENT_USER);
	       if(user==null) {
	    	   return ResponseEntityUtil.fail("用户未登录");
	       }
	       return userService.resetPassword(passwordOld, passwordNew, user);
	}
	@ApiOperation(value = "更新管理员信息接口",notes = "更新管理员信息")
	@PostMapping(value="update_information.do")
	public ResponseEntity<User> update_information(HttpSession session, User user){
		User currentUser= (User)session.getAttribute(Const.CURRENT_USER);
		if(currentUser==null) {
			return ResponseEntityUtil.fail("用户未登录");
		}
		user.setId(currentUser.getId());
		user.setAccount(currentUser.getAccount());
		ResponseEntity<User> response= userService.updateInformation(user);
		if(response.isSuccess()) {
			response.getData().setAccount(currentUser.getAccount());
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}
	
	@ApiOperation(value = "获取管理员列表",notes = "管理员列表")
	@PostMapping(value="get_list.do")
	public ResponseEntity<PageResponseBean<UserVo>> getList(
			HttpSession session,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum, 
            @RequestParam(value="pageSize", defaultValue="10") int pageSize
            ){
		User user= (User)session.getAttribute(Const.CURRENT_USER);
	       if(user==null) {
	    	   return ResponseEntityUtil.fail("用户未登录");
	       }
	       return ResponseEntityUtil.success(userService.getUserList(pageNum, pageSize));
	}
	
    @ApiOperation(value = "操作记录", notes = "")
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public ResponseEntity<PageResponseBean<Syslog>> records(@Valid @RequestBody PageRequestBean bean) {
        return ResponseEntityUtil.success(sysLogService.sysLog(bean));
    }
}
