package com.media.ops.backend.controller;

import com.media.ops.backend.contants.Const;
import com.media.ops.backend.controller.request.PagePlayRequestBean;
import com.media.ops.backend.controller.request.PageRequestBean;
import com.media.ops.backend.controller.request.PlayAddRequestBean;
import com.media.ops.backend.controller.request.PlaySearchRequestBean;
import com.media.ops.backend.controller.request.PlayUpdateRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.entity.User;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.service.SmsService;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.DateUtil;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.PlayVo;
import com.media.ops.backend.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by linfs on 2018-03-02.
 * 说明：
 *  getPlaysByTime为电视设备接口用到的，其它为网站或手机APP用到的
 */
@Api(description="直播接口",produces = "application/json")
@RestController
@RequestMapping("play")
public class PlayController extends BaseController {
	
    @Autowired
    private PlayService playService;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsService smsService;
    
    
    @ApiOperation(value = "获取时间段范围内的直播记录",notes = "获取时间段范围内的直播记录")
    @PostMapping(value="getPlaysByTime.do")
    public ResponseEntity<List<Play>> getPlaysByTime(String begintime, String endtime) {
        return playService.GetPlays(begintime,endtime);
    }
    
    @ApiOperation(value = "增加直播记录",notes = "增加直播记录")
    @PostMapping(value="addPlay.do")
    public ResponseEntity addPlay(@Valid @RequestBody PlayAddRequestBean bean,HttpServletRequest request) {
        ResponseEntity response= playService.add(super.getSessionUser(request).getAccount(),bean);
        if(response.isSuccess()) {
        	ResponseEntity respUser= userService.getInformation(bean.getPlayerid());
        	if(respUser.isSuccess()) {
        		UserVo userVo=(UserVo)respUser.getData();
        		String mobile= userVo.getPhone();
        		String account=userVo.getAccount();
        		String trueName= userVo.getTruename();
        		String time=DateUtil.format(bean.getBegintime(), DateUtil.DEFAULT_PATTERN);
        		String content=trueName+"(工号："+account+"),您在"+time+"有直播任务，请登录APP查看";
        		smsService.send(mobile, content);
                                //test
        	}
        }
        return response;
    }
    
    @ApiOperation(value = "删除直播记录",notes = "删除直播记录")
    @PostMapping(value="deletePlay.do")
    public ResponseEntity<String> deletePlay(@RequestBody Integer id,HttpServletRequest request) {
        return playService.delete(super.getSessionUser(request).getAccount(),id);
    }
    
    @ApiOperation(value = "更新直播记录",notes = "更新直播记录")
    @PostMapping(value="updatePlay.do")
    public ResponseEntity<String> updatePlay(@Valid @RequestBody PlayUpdateRequestBean bean,HttpServletRequest request) {
        return playService.update(super.getSessionUser(request).getAccount(),bean);
    }
    
    @ApiOperation(value = "根据id获取直播记录",notes = "根据id获取直播记录")
    @PostMapping(value="getPlay.do")
    public ResponseEntity<PlayVo> getPlay(@RequestBody Integer id) {
        return playService.selectByKey(id);
    }
    
    
    @ApiOperation(value = "获取所有直播记录",notes = "获取所有直播记录")
    @PostMapping(value="selectLists.do")
    public ResponseEntity<List<PlayVo>> selectPlayList() {
        return playService.selectPlayList();
    }
    
    
    @ApiOperation(value = "通过playerId获取直播记录",notes = "通过playerId获取直播记录")
    @PostMapping(value="selectPlayListByPlayerId.do")
    public ResponseEntity<PageResponseBean<PlayVo>> selectPlayListByPlayerId(@RequestBody PagePlayRequestBean bean) {
        return ResponseEntityUtil.success(playService.selectPlayListByPlayerId(bean.getPageNum(), bean.getPageSize(), bean.getPlayerId()));
    }
    
    @ApiOperation(value = "通过playerid和status获取直播记录",notes = "通过playerid和status获取直播记录")
    @PostMapping(value="selectPlayListWithStatusAndPlayerId.do")
    public ResponseEntity<List<PlayVo>> selectPlayListWithStatusAndPlayerId(@RequestBody PlaySearchRequestBean bean) {
        return playService.selectPlayListWithStatusAndPlayerId(bean.getPlayerId(),bean.getStatus());
    }

}
