package com.media.ops.backend;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.service.CityService;
import com.media.ops.backend.service.SmsService;
import com.media.ops.backend.service.SysLogService;
import com.media.ops.backend.util.MD5Util;
import com.media.ops.backend.vo.SyslogVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private SmsService  smsService;
//	@Autowired
//	private CityService cityService;
	
	@Autowired
	private SysLogService sysLogService;
	
	@Test
	public void test() {
		System.out.println(smsService.sign("短信测试", "utf-8"));
	}
	@Test
	public void test1() {
		System.out.println(MD5Util.MD5("111111"));
	}
	@Test
	public void test2() {
		System.out.println("-----------start------------");
		System.out.println(smsService.send("13588774342", "短信发送测试"));
		System.out.println("-----------end---------------");
	}
	
	@Test
	public void test3() {
		List<String> mobiles=Lists.newArrayList();
		mobiles.add("13588774342");
		mobiles.add("13588774342");
		
		System.out.println(smsService.sendMass(mobiles,"群发测试"));
	}
	
	@Test
	public void test4() {
		PageRequestBean bean= new PageRequestBean();
		bean.setPageNum(0);
		bean.setPageSize(0);
		PageResponseBean<SyslogVo>  result =sysLogService.sysLog(bean);

	}
	
	@Test
	public void test5() throws Exception {
		URL url=new URL("http://test45.bj.bcebos.com/2018041915bb6a87fc.mp4");
		HttpURLConnection urlcon= (HttpURLConnection)url.openConnection();
		//urlcon.get
	}
}
