package com.boran.erp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boran.erp.Entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     *
     * @param uid 用户id
     * @return
     * 返回角色
     */
    List<String> RoleName(int uid);
}
