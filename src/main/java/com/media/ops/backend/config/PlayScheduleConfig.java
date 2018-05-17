package com.media.ops.backend.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.media.ops.backend.controller.request.MaterialAddRequestBean;
import com.media.ops.backend.dao.entity.Material;
import com.media.ops.backend.dao.entity.Play;
import com.media.ops.backend.dao.mapper.PlayMapper;
import com.media.ops.backend.dao.mapper.SysparaMapper;
import com.media.ops.backend.service.MaterialService;
import com.media.ops.backend.service.SysparaService;

@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class PlayScheduleConfig {
	
    @Autowired
    @SuppressWarnings("all")
    private PlayMapper playMapper;
    
    @Autowired
    @SuppressWarnings("all")
    private MaterialService materialService;
    
    @Autowired
    @SuppressWarnings("all")
    private SysparaMapper sysparaMapper;

    //3.添加定时任务
    @Scheduled(cron = "0 */1 * * * ?")
    private void configureTasks() {
        try {
           // System.err.println("定时查询未结束的直播，当前时间为：" + DateUtil.getCurrentTime());
            Thread.sleep(1000);

            Date CURRENT_TIME= new Date();
            List<Play> plays= playMapper.selectUnfinishedPlay();
            for (Play play : plays) {
			   Date ENDTIME =play.getEndtime();
			   if(ENDTIME.before(CURRENT_TIME)) {
				    if(play.getStatus()==1) {
				    	play.setStatus(0);
				    }else {
				    	play.setStatus(3);
				    }
					int result = playMapper.updateByPrimaryKeySelective(play);
					Thread.sleep(1000);
					String strResult = "ID为" + play.getId() + "的直播状态修改为" + play.getStatus();
					System.out.println(result > 0 ? strResult : "修改失败!");   
			   }
			}

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //3.添加定时任务
    @Scheduled(cron = "0 */5 * * * ?")
    private void playToMaterial() {
        try {
           // System.err.println("定时查询未结束的直播，当前时间为：" + DateUtil.getCurrentTime());
            Thread.sleep(1000);

            List<Play> plays= playMapper.selectByStatusAndRemarks();
            for (Play play : plays) {

            	MaterialAddRequestBean bean= new MaterialAddRequestBean();
            	bean.setName(play.getTitle());
            	String preUrl= sysparaMapper.selectByName("PLAYSERVER").getValue();
            	bean.setPath("http://"+preUrl+"/"+ play.getReplayaddress());
            	bean.setType("直播");
            	bean.setGroupid(1);
            	
            	materialService.addMaterial(play.getCreateBy(), bean);
			}
            
            for (Play play : plays) {
				playMapper.updateRemarksById(play.getId());
			}

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
