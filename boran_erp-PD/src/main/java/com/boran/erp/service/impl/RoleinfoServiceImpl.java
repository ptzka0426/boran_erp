package com.boran.erp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.Roleinfo;
import com.boran.erp.mapper.RoleinfoMapper;
import com.boran.erp.service.RoleinfoService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@AutoConfigureAfter({RoleinfoMapper.class})
public class RoleinfoServiceImpl extends ServiceImpl<RoleinfoMapper, Roleinfo> implements RoleinfoService {

}




