package com.liangyonghui.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liangyonghui.project.common.ErrorCode;
import com.liangyonghui.project.exception.BusinessException;
import com.liangyonghui.project.mapper.UserMapper;
import com.liangyonghui.rhineapicommon.model.entity.User;
import com.liangyonghui.rhineapicommon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(timeout = 10000)
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        //参数校验
        if (StringUtils.isAnyBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //创建查询条件包装器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);

        //使用UserMapper的selectOne方法查询用户信息
        User user = userMapper.selectOne(queryWrapper);
        return userMapper.selectOne(queryWrapper);
    }
}
