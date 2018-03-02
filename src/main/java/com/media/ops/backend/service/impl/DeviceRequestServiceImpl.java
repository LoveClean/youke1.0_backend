package com.media.ops.backend.service.impl;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.mapper.AdMapper;
import com.media.ops.backend.dao.mapper.DeviceMapper;
import com.media.ops.backend.dao.mapper.UserMapper;
import com.media.ops.backend.service.DeviceRequestService;
import com.media.ops.backend.util.ResponseEntity;
import com.media.ops.backend.util.ResponseEntityUtil;
import com.media.ops.backend.vo.AdMaterialListVo;
import com.media.ops.backend.vo.AdMaterialVo;
import com.media.ops.backend.vo.AddeliveryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by linfs on 2018-03-01.
 */

@Service
public class DeviceRequestServiceImpl implements DeviceRequestService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private AdMapper adMapper;

    @Override
    public ResponseEntity<Device> GetDevice(String code)
    {
        Device device = deviceMapper.selectByCode(code);
        if(device==null) {
            return ResponseEntityUtil.fail("设备编号错误");
        }
        return ResponseEntityUtil.success(device);
    }
    @Override
    public ResponseEntity<String> BindDevice(String code,String mac)
    {
        Device device = deviceMapper.selectByCode(code);
        if(device==null) {
            return ResponseEntityUtil.fail("设备编号错误");
        }
        device.setMac(mac);
        if (deviceMapper.updateByPrimaryKeySelective(device)>0)
            return ResponseEntityUtil.success("绑定设备成功");
        else
            return ResponseEntityUtil.fail("绑定设备失败");
    }
    //获取设备的当前投放广告
    @Override
    public ResponseEntity<List<AdMaterialListVo>> GetAd(String mac)
    {
        List<AdMaterialListVo> list = adMapper.selectAdVoByMac(mac);
        if (list==null)
            return ResponseEntityUtil.fail("查询广告失败");
        if (list.size()>0)
        {
            for (AdMaterialListVo ad: list) {
                ad.setAdMaterialVoList(adMapper.selectAdMaterialByAdid(ad.getId()));
            }
        }
        return ResponseEntityUtil.success(list);
    }

}
