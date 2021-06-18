package com.boran.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.Powerinfo;
import com.boran.erp.mapper.PowerinfoMapper;
import com.boran.erp.mapper.UserRoleMapper;
import com.boran.erp.service.PowerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
@AutoConfigureAfter({PowerinfoMapper.class})
public class PowerinfoServiceImpl extends ServiceImpl<PowerinfoMapper, Powerinfo>
        implements PowerinfoService {
    @Autowired
    private PowerinfoMapper powerinfoMapper;

    @Override
    public List<String> PowerName(String RoleName) {
        return powerinfoMapper.PowerName(RoleName);
    }
}




