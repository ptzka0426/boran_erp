package com.boran.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boran.erp.Entity.Powerinfo;

import java.util.List;

/**
 *
 */
public interface PowerinfoService extends IService<Powerinfo> {
    List<String> PowerName(String RoleName);
}
