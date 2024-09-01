package com.liangyonghui.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liangyonghui.rhineapicommon.model.entity.InterfaceInfo;

/**
* @author 梁永辉
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-03-15 17:59:41
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
