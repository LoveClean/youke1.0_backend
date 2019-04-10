package com.media.ops.backend.controller;

import com.media.ops.backend.controller.request.PlayAddRequestBean;
import com.media.ops.backend.controller.request.PlayUpdateRequestBean;
import com.media.ops.backend.controller.response.PageResponseBean;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.service.PlayService;
import com.media.ops.backend.service.SmsService;
import com.media.ops.backend.service.UserService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.vo.PlayVo;
import com.media.ops.backend.vo.PlayerVo;
import com.media.ops.backend.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(description="直播操作接口描述",produces = "application/json")
@RestController
@RequestMapping("/play/")
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
    public ResponseEntity addPlay(@Valid @RequestBody PlayAddRequestBean bean, HttpServletRequest request) {
        ResponseEntity response= playService.add(super.getSessionUser(request).getAccount(),bean);
        if(response.isSuccess()) {
        	ResponseEntity respUser= userService.getInformation(bean.getPlayerid());
        	if(respUser.isSuccess()) {
        		UserVo userVo=(UserVo)respUser.getData();
        		String mobile= userVo.getPhone();
        		String account=userVo.getAccount();
        		String trueName= userVo.getTruename();
        		String time=bean.getBegintime();
        		String content=trueName+"(工号："+account+"),您在"+time+"有直播任务，请登录APP查看";
        		smsService.send(mobile, content);
        	}
        }
        return response;
    }
	//删除直播
	@ApiOperation(value = "删除直播记录",notes = "删除直播记录")
    @PostMapping(value="deletePlay.do")
    public ResponseEntity<String> deletePlay(@RequestParam Integer id, HttpServletRequest request) {
        return playService.delete(super.getSessionUser(request).getAccount(),id);
    }
	
    @ApiOperation(value = "更新直播记录",notes = "更新直播记录")
    @PostMapping(value="updatePlay.do")
    public ResponseEntity<String> updatePlay(@Valid @RequestBody PlayUpdateRequestBean bean, HttpServletRequest request) {
        return playService.update(super.getSessionUser(request).getAccount(),bean);
    }
    
    @ApiOperation(value = "根据id获取直播记录",notes = "根据id获取直播记录")
    @PostMapping(value="getPlay.do")
    public ResponseEntity<PlayVo> getPlay(@RequestBody Integer id) {
        return playService.selectByKey(id);
    }
    
    @ApiOperation(value = "获取所有直播记录",notes = "获取所有直播记录")
    @PostMapping(value="selectLists.do")
    public PageResponseBean<PlayVo> selectPlayList(@RequestParam(defaultValue = "1") int pageNum,
                                                   @RequestParam(defaultValue = "999") int pageSize) {
        PageResponseBean page= playService.selectPlayList(pageNum,pageSize);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
    
    @ApiOperation(value = "获取所有未结束的直播记录",notes = "获取所有未结束的直播记录")
    @PostMapping(value="selectUnfinishedLists.do")
    public ResponseEntity<List<PlayVo>> selectUnfiniPlayList() {
        return playService.selectUnfinishedPlayList();
    }
 
    @ApiOperation(value = "获取所有直播员列表",notes = "获取所有直播员")
    @PostMapping(value="selectPlayerList.do")
    public ResponseEntity<List<PlayerVo>> selectPlayerList() {
        return playService.selectPlayerList();
    }
    
    @ApiOperation(value = "通过playerId获取直播记录",notes = "通过playerId获取直播记录")
    @PostMapping(value="selectPlayListByPlayerId.do")
    public PageResponseBean<PlayVo> selectPlayListByPlayerId(@RequestParam(defaultValue = "1") int pageNum,
                                                             @RequestParam(defaultValue = "999") int pageSize,
                                                             @RequestParam(required = true) @ApiParam("直播员id") Integer playerId) {
        PageResponseBean page=playService.selectPlayListByPlayerId(pageNum, pageSize, playerId);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
    
    @ApiOperation(value = "通过playerid,status,title,time获取直播记录",notes = "通过playerid,status,title,time获取直播记录")
    @PostMapping(value="selectPlayListByKeys.do")
    public PageResponseBean<PlayVo> selectPlayListByKeys(@RequestParam(required = false) @ApiParam("直播员ID") Integer playerId,
                                                         @RequestParam(required = false) @ApiParam("状态(0,未开放；1,预告中；2,直播中；3,直播结束)") Integer status ,
                                                         @RequestParam(required = false) @ApiParam("直播标题") String playTitle,
                                                         @RequestParam(required = false) @ApiParam("开始时间") String beginTime,
                                                         @RequestParam(required = false) @ApiParam("结束时间") String endTime,
                                                         @RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "999") int pageSize) {
        PageResponseBean page = playService.selectPlayListByKeys(playerId,status,playTitle,beginTime,endTime,pageNum,pageSize);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
	
	
	
	
	
}
