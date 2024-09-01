package com.liangyonghui.project.service.impl.inner;

import com.liangyonghui.project.service.UserInterfaceInfoService;
import com.liangyonghui.rhineapicommon.model.entity.UserInterfaceInfo;
import com.liangyonghui.rhineapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(timeout = 10000)
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {

    }

    @Override
    public boolean getInvokeUser(long interfaceInfoId, long userId) {
        return false;
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 调用注入的UserInterfaceInfoService的invokeCount方法
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
