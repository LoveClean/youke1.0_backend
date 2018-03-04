package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.annotation.ACS;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.UserLoginRequestBean;
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

@Api(description="管理员操作接口",produces = "application/json")
@RestController
@RequestMapping("/manager/")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private SysLogService sysLogService;


	
	@ACS(allowAnonymous = true)
	@ApiOperation(value = "登录接口",notes = "用户登录，username可为账号、手机或邮箱")
	@PostMapping(value="login.do")
	public ResponseEntity<User> login(@Valid @RequestBody UserLoginRequestBean bean, 
			HttpServletRequest request) {
		ResponseEntity<User> response=userService.adminLogin(bean.getLoginName(), bean.getLoginPwd());
		if(response.isSuccess()) {
			User user= response.getData();
			//session.setAttribute(Const.CURRENT_USER, response.getData());
			// 创建访问token
	        String accessToken = super.generateAccessToken(request);
	        user.setAccessToken(accessToken);

	        super.setAccessTokenAttribute(request, accessToken);
	        super.sessionUser(request, user);

	        return ResponseEntityUtil.success(user);
		}
		return response;
		
        
	}
	
    /**
	 * 退出登录
	 */
	@ApiOperation(value = "退出登录", notes = "退出登录")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest request) {
		deleteSessionUser(request);
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
	public ResponseEntity<User> getUserInfo(HttpServletRequest request){
		User user= super.getSessionUser(request);
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
	public ResponseEntity<String> resetPassword(HttpServletRequest request, String passwordOld, String passwordNew){
	       User user= super.getSessionUser(request);
	       if(user==null) {
	    	   return ResponseEntityUtil.fail("用户未登录");
	       }
	       return userService.resetPassword(passwordOld, passwordNew, user);
	}
	
	@ApiOperation(value = "更新管理员信息接口",notes = "更新管理员信息")
	@PostMapping(value="update_information.do")
	public ResponseEntity<User> update_information(HttpServletRequest request, User user){
		User currentUser= super.getSessionUser(request);
		user.setId(currentUser.getId());
		user.setAccount(currentUser.getAccount());
		ResponseEntity<User> response= userService.updateInformation(user);
		if(response.isSuccess()) {			
			// 创建访问token
	        String accessToken = super.generateAccessToken(request);
	        user.setAccessToken(accessToken);

	        super.setAccessTokenAttribute(request, accessToken);
	        super.sessionUser(request, user);

	        return ResponseEntityUtil.success(user);
		}
		return response;
	}
	
	@ApiOperation(value = "获取管理员列表",notes = "管理员列表")
	@PostMapping(value="get_list.do")
	public ResponseEntity<PageResponseBean<UserVo>> getList(PageRequestBean bean){
	       return ResponseEntityUtil.success(userService.getUserList(bean.getPageNum(),bean.getPageSize()));
	}
	
    @ApiOperation(value = "操作记录", notes = "")
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public ResponseEntity<PageResponseBean<Syslog>> records(@Valid @RequestBody PageRequestBean bean) {
        return ResponseEntityUtil.success(sysLogService.sysLog(bean));
    }
}
