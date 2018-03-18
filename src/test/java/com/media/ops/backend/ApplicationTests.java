package com.media.ops.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.media.ops.backend.service.CityService;
import com.media.ops.backend.service.SmsService;
import com.media.ops.backend.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private SmsService  smsService;
//	@Autowired
//	private CityService cityService;
	
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

}
