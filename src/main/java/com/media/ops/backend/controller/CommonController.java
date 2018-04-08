package com.media.ops.backend.controller;

import com.media.ops.backend.annotation.ACS;
import com.media.ops.backend.contants.Const;
import com.media.ops.backend.contants.Errors;
import com.media.ops.backend.contants.SmsConstants.SmsCaptchaType;
import com.media.ops.backend.controller.request.CaptchaRequestBean;
import com.media.ops.backend.controller.request.CaptchaVerifyRequestBean;
import com.media.ops.backend.controller.response.CaptchaResponseBean;
import com.media.ops.backend.exception.BusinessException;
import com.media.ops.backend.service.MobileCaptchaService;
import com.media.ops.backend.util.ExceptionUtil;
import com.media.ops.backend.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description: 工具controler
 * @date 2016年11月19日
 * @version V1.0
 */
@Api(description = "公共模块", produces = "application/json")
@RestController
@RequestMapping("/common")
public class CommonController {

	@Resource
	private MobileCaptchaService mobileCaptchaService;

	/**
	 * 发送验证码
	 * 
	 * @param bean
	 * @return
	 */
	@ApiOperation(value = "发送短信验证码", notes = "type类型：1注册,2修改密码,3重置密码,4注册+登陆,5绑定卡<br/>有效时间5分钟，相同类型发送冷却时间1分钟<br/>返回：code=0成功；code=1手机号有误;code=2未超过发送冷却时间，exception=剩余发送冷却时间(单位秒)；code=3送失败请稍后再试")
	@RequestMapping(value = "/sms/sendCaptcha", method = RequestMethod.POST)
	public ResponseEntity<CaptchaResponseBean> sendCaptcha(@Valid @RequestBody CaptchaRequestBean bean) {
		CaptchaResponseBean result = null;
		try {
			result = mobileCaptchaService.send(bean);
		} catch (BusinessException e) {
			ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, e.toString());
		}
		return ResponseEntity.ok(result);
	}

	/**
	 * 验证验证码
	 * 
	 * @param bean
	 * @return
	 */
	@ApiOperation(value = "验证短信验证码", notes = "校验验证码，手机号、验证码与type类型：1注册,2修改密码,3重置密码")
	@RequestMapping(value = "/sms/verifyCaptcha", method = RequestMethod.POST)
	public ResponseEntity<Boolean> verifyCaptcha(@Valid @RequestBody CaptchaVerifyRequestBean bean) {
		boolean result = false;
		try {
			result = mobileCaptchaService.verify(bean.getMobile(), bean.getCaptcha(),
					SmsCaptchaType.getSmsCaptchaType((byte) bean.getType()));
		} catch (BusinessException e) {
			ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, e.toString());
		}
		return ResponseEntity.ok(result);
	}
	
	@ACS(allowAnonymous = true)	
	@ApiOperation(value = "随机验证码接口", notes = "随机验证码")
	@GetMapping(value = "get_verify_code.do")
	public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1定义bufferedImage对象
		BufferedImage bImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		// 2获得Graphics对象
		Graphics graphics = bImage.getGraphics();
		Color color = new Color(200, 150, 255);
		graphics.setColor(color);
		graphics.fillRect(0, 0, 68, 22);
		// 3通过Random产生随机验证码信息
		// 4使用Graphics绘制图片
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		Random r = new Random();
		int len = ch.length, index;
		String str = "";
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			graphics.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			graphics.drawString(ch[index] + "", (i * 15) + 5, 16);

			str += ch[index];
		}
		
		//5将验证码信息保存到Session中
        request.getSession().setAttribute(Const.VERIFY_CODE, str);
        System.out.println("verifyCode:---------------"+str);
		//6使用ImageIO输出图片
       ImageIO.write(bImage, "JPG", response.getOutputStream());
	}
	
	@ACS(allowAnonymous = true)	
	@ApiOperation(value = "校验随机验证码接口", notes = "校验随机验证码")
	@PostMapping(value = "valid_verify_code.do")	
	public ResponseEntity<Boolean> validVerifyCode(@RequestBody String code, HttpServletRequest request){
		Boolean flag=false;
		String randomCode= (String)request.getSession().getAttribute(Const.VERIFY_CODE);
		if(code.toUpperCase().equals(randomCode.toUpperCase())) {
			flag=true;
			request.getSession().removeAttribute(Const.VERIFY_CODE);
		}

		return ResponseEntity.ok(flag);
	}
}
