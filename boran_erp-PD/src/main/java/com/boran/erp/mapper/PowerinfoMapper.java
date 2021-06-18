package com.boran.erp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boran.erp.Entity.Powerinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.boran.boran.Powerinfo
 */
public interface PowerinfoMapper extends BaseMapper<Powerinfo> {
    /**
     *
     * @param RoleName 传入的角色
     * @return 返回角色的权限
     */
    @Select("select p.PowerName from userinfo u INNER JOIN user_role ur on u.id=ur.UserId " +
            " INNER JOIN roleinfo r on ur.RoleId=u.id " +
            " INNER JOIN role_power rp on rp.RoleId =r.id " +
            " INNER JOIN powerinfo p on p.id=rp.PowerId " +
            " where r.RoleName=#{RoleName}")
    List<String> PowerName(String RoleName);

}




