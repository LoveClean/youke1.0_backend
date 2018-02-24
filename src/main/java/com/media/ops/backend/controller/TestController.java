package com.media.ops.backend.controller;

import com.media.ops.backend.dao.entity.Member;
import com.media.ops.backend.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**    
 * @author LJH
 * @version 1.0
 * @Date 2018/1/16 16:55
 */
@Api(description="第一个接口描述",produces = "application/json")
@RestController
@RequestMapping("first")
public class TestController {

    @Resource
    private MemberService memberService;

    @ApiOperation(value = "hello接口",notes = "hello笔记")
    @GetMapping("hello")
    public ResponseEntity first(){

        return ResponseEntity.ok("okok");
    }

    @ApiOperation(value = "会员信息")
    @GetMapping("member")
    public ResponseEntity<Member> member(){

        return ResponseEntity.ok(memberService.byMemberId("1"));
    }



}
