package com.liangyonghui.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangyonghui.project.common.ErrorCode;
import com.liangyonghui.project.exception.BusinessException;
import com.liangyonghui.project.exception.ThrowUtils;
import com.liangyonghui.project.mapper.InterfaceInfoMapper;
import com.liangyonghui.project.service.InterfaceInfoService;
import com.liangyonghui.rhineapicommon.model.entity.InterfaceInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 梁永辉
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-03-15 17:59:41
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {


        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = interfaceInfo.getName();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(name) && name.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }
}




