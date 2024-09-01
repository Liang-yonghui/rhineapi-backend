package com.liangyonghui.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangyonghui.project.common.ErrorCode;
import com.liangyonghui.project.exception.BusinessException;
import com.liangyonghui.project.mapper.UserInterfaceInfoMapper;
import com.liangyonghui.project.service.UserInterfaceInfoService;
import com.liangyonghui.rhineapicommon.model.entity.UserInterfaceInfo;
import org.springframework.stereotype.Service;

/**
* @author 梁永辉
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-03-15 17:59:41
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {


        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        // 创建时，参数不能为空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }

    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId < 0 || userId < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //使用updateWrapper对象来构建更新条件
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        //在updateWrapper中设置了两个条件：interfaceInfoId等于给定的interfaceInfoId， userId等于给定的userId
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");

        return this.update(updateWrapper);
    }
}




