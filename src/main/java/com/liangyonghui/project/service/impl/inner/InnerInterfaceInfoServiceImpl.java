package com.liangyonghui.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liangyonghui.project.common.ErrorCode;
import com.liangyonghui.project.exception.BusinessException;
import com.liangyonghui.project.mapper.InterfaceInfoMapper;
import com.liangyonghui.rhineapicommon.model.entity.InterfaceInfo;
import com.liangyonghui.rhineapicommon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


@DubboService(timeout = 10000)
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        //参数校验
        if (StringUtils.isAnyBlank(url, method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //创建查询条件包装器
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);

        // 使用InterfaceInfoMapper的selectOne方法查询接口信息
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
