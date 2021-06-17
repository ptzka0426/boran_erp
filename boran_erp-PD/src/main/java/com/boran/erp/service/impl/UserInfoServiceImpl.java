package com.boran.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.UserInfo;
import com.boran.erp.mapper.UserInfoMapper;
import com.boran.erp.service.UserInfoService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

/**
 * @Author LT
 * @create 2021-06-16 15:36
 */
@Service
@AutoConfigureAfter({UserInfoMapper.class})
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
