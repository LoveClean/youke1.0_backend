package com.media.ops.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.media.ops.backend.annotation.ACS;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.controller.request.BuildingSearchRequestBean;
import com.media.ops.backend.controller.request.InfoCheckRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PasswordQARequestBean;
import com.media.ops.backend.controller.request.PasswordQAResetRequestBean;
import com.media.ops.backend.controller.request.PasswordResetRequestBean;
import com.media.ops.backend.controller.request.SyslogSearchReqeustBean;
import com.media.ops.backend.controller.request.UserAccountStatusRequestBean;
import com.media.ops.backend.controller.request.UserAddRequestBean;
import com.media.ops.backend.controller.request.UserInfoUptRequestBean;
import com.media.ops.backend.controller.request.UserLoginRequestBean;
import com.media.ops.backend.controller.request.UserSearchRequestBean;
import com.media.ops.backend.controller.request.UserUptRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Syslog;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.SmsService;
import com.media.ops.backend.service.SysLogService;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.SyslogVo;
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
	@Autowired
	private SmsService smsService;


	
	@ACS(allowAnonymous = true)
	@ApiOperation(value = "登录接口",notes = "用户登录，username可为账号、手机或邮箱")
	@PostMapping(value="login.do")
	public ResponseEntity<User> login(@Valid @RequestBody UserLoginRequestBean bean, 
			HttpServletRequest request) {
		
		Boolean flag=false;
		String randomCode= (String)request.getSession().getAttribute(Const.VERIFY_CODE);
		if(bean.getVerifyCode().toUpperCase().equals(randomCode.toUpperCase())) {
			flag=true;
		}
		
		if(!flag) {
			return ResponseEntityUtil.fail("验证码错误");
		}
		
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
	public ResponseEntity add(@Valid @RequestBody UserAddRequestBean bean, 
			HttpServletRequest request){
		User user= new User(bean.getAccount(), bean.getTrueName(), bean.getPassword(),
				bean.getEmail(), bean.getPhone(), bean.getQuestion(), bean.getAnswer(), 
				bean.getType(), (byte) 1, super.getSessionUser(request).getAccount(), super.getSessionUser(request).getAccount());
		
		ResponseEntity response= userService.add(user);
		if(response.isSuccess()) {
			//注册成功，发送短信，告知初始密码
			smsService.send(bean.getPhone(), "您已成为系统的"+ (bean.getType()==0?"管理员":"直播员")+",初始密码为："+bean.getPassword());
		}
		return response;
	}
	
	@ACS(allowAnonymous = true)
	@ApiOperation(value = "有效性校验接口,type可为account或email",notes = "信息校验")
	@PostMapping(value="check_valid.do")
	public ResponseEntity<String> checkValid(@Valid @RequestBody InfoCheckRequestBean bean){
		return userService.checkValid(bean.getValue(), bean.getType());
	}
	
	
	@ApiOperation(value = "获取管理员信息接口",notes = "获取管理员信息")
	@GetMapping(value="get_user_info.do")
	public ResponseEntity<User> getUserInfo(HttpServletRequest request){
		User user= super.getSessionUser(request);
		if(user !=null) {
			return ResponseEntityUtil.success(user);
		}
		return ResponseEntityUtil.fail("用户未登录，无法获取当前用户信息");
	}
	
//	@ACS(allowAnonymous = true)
//	@ApiOperation(value = "密保提示问题接口",notes = "获取密保提示问题")
//	@PostMapping(value="forget_get_question.do")
//	public ResponseEntity<String> selectQuestion(@RequestBody String username) {
//		return userService.selectQuestion(username);
//	}
//	
//	@ACS(allowAnonymous = true)
//	@ApiOperation(value = "确认密保问题答案接口",notes = "确认密保问题答案")
//	@PostMapping(value="forget_check_answer.do")
//	public ResponseEntity<String> forgetCheckAnswer(@RequestBody PasswordQARequestBean bean){
//		return userService.checkAnswer(bean.getUsername(), bean.getQuestion(), bean.getAnswer());
//	}
//	
//	@ACS(allowAnonymous = true)
//	@ApiOperation(value = "重置密码接口",notes = "重置密码")
//	@PostMapping(value="forget_reset_password.do")
//	public ResponseEntity<String> forgetResetPassword(@RequestBody PasswordQAResetRequestBean bean){
//		return userService.forgetResetPassword(bean.getUsername(), bean.getPasswordNew(), bean.getForgetToken());
//	}
	
	@ApiOperation(value = "登录状态下重置密码接口",notes = "登录状态下重置密码")
	@PostMapping(value="reset_password.do")	
	public ResponseEntity<String> resetPassword(HttpServletRequest request,@Valid @RequestBody PasswordResetRequestBean bean){
	       User user= super.getSessionUser(request);
	       if(user==null) {
	    	   return ResponseEntityUtil.fail("用户未登录");
	       }
	       System.out.println(bean.getPasswordNew()+"------"+bean.getPasswordOld());
	       return userService.resetPassword(bean.getPasswordOld(), bean.getPasswordNew(), user);
	}
	
	@ApiOperation(value = "管理员搜索接口",notes = "管理员搜索")
	@PostMapping(value="search_user.do")		
	public ResponseEntity<User> searchUser(@RequestBody UserSearchRequestBean bean){
		return userService.getUserByAccountEmail(bean.getAccount(), bean.getEmail());
	}
	
	@ApiOperation(value = "修改个人信息接口",notes = "修改自身信息")
	@PostMapping(value="update_information.do")
	public ResponseEntity<User> update_information(HttpServletRequest request, 
			@Valid @RequestBody UserUptRequestBean bean){
		User user= super.getSessionUser(request);
		
		user.setTruename(bean.getTrueName());
		user.setEmail(bean.getEmail());
		user.setPhone(bean.getPhone());
		user.setQuestion(bean.getQuestion());
		user.setAnswer(bean.getAnswer());
		
		user.setUpdateBy(user.getAccount());

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
		
	@ApiOperation(value = "修改他人信息接口",notes = "修改自身信息")
	@PostMapping(value="update_others_infor.do")
	public ResponseEntity<User> updateOthersInfor(HttpServletRequest request, 
			@Valid @RequestBody UserInfoUptRequestBean bean){
		User user= new User();
		
		user.setId(bean.getId());
		user.setTruename(bean.getTrueName());
		user.setEmail(bean.getEmail());
		user.setPhone(bean.getPhone());
		user.setType(bean.getType());
		user.setStatus(bean.getStatus());

		user.setUpdateBy(super.getSessionUser(request).getAccount());

		return userService.updateInformation(user);
	}
	@ApiOperation(value = "获取管理员列表",notes = "管理员列表")
	@PostMapping(value="get_list.do")
	public ResponseEntity<PageResponseBean<UserVo>> getList(@RequestBody PageRequestBean bean){
	       return ResponseEntityUtil.success(userService.getUserList(bean.getPageNum(),bean.getPageSize()));
	}
	
    @ApiOperation(value = "操作记录", notes = "")
    @PostMapping(value = "/records")
    public ResponseEntity<PageResponseBean<SyslogVo>> records(@RequestBody PageRequestBean bean) {
        return ResponseEntityUtil.success(sysLogService.sysLog(bean));
    }
    
	@ApiOperation(value = "根据工号或邮箱查询管理员操作日志接口", notes = "根据工号或邮箱查询管理员操作日志")
	@PostMapping(value = "search_syslog.do")
	public ResponseEntity searchSyslog(@RequestBody SyslogSearchReqeustBean bean){
		return sysLogService.selectLogbyKey(bean);
	}
    
    
	@ApiOperation(value = "登录禁用接口",notes = "登录禁用")
	@PostMapping(value="login_forbidden.do")
	public ResponseEntity<String> loginForbidden(@RequestBody UserAccountStatusRequestBean bean){
		return userService.updateStatusById(bean.getAccount(), bean.getStatus());
	}
	
	@ApiOperation(value = "根据id查询管理员信息接口",notes = "根据id查询管理员信息接口")
	@PostMapping(value="get_info.do")	
	public ResponseEntity<UserVo> getInformation(@RequestBody Integer id){
		return userService.getInformation(id);
	}
	

}
