package com.boran.erp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.UserRole;
import com.boran.erp.mapper.UserRoleMapper;
import com.boran.erp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 */
@Service
@AutoConfigureAfter({UserRoleMapper.class})
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Map<String, Object> RoleName(int uid) {
        return userRoleMapper.RoleName(uid);
    }
}




