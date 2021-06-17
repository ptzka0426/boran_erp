package com.boran.erp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.RolePower;
import com.boran.erp.mapper.RolePowerMapper;
import com.boran.erp.mapper.UserRoleMapper;
import com.boran.erp.service.RolePowerService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@AutoConfigureAfter({RolePowerMapper.class})
public class RolePowerServiceImpl extends ServiceImpl<RolePowerMapper, RolePower>
implements RolePowerService {

}




