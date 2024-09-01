package com.liangyonghui.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangyonghui.rhineapicommon.model.entity.UserInterfaceInfo;

/**
* @author 梁永辉
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-05-22 14:10:31
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
