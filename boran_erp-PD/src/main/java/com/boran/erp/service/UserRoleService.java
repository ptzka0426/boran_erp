package com.boran.erp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boran.erp.Entity.UserRole;

import java.util.Map;

/**
 *
 */
public interface UserRoleService extends IService<UserRole> {
    Map<String, Object> RoleName(int uid);
}
