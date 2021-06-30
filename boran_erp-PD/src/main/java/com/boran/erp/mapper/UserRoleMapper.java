package com.boran.erp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boran.erp.Entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.boran.boran.UserRole
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("select r.RoleName from userinfo u INNER JOIN user_role ur on u.id=ur.UserId " +
            "INNER JOIN roleinfo r on ur.RoleId=r.id " +
            " where u.id=#{uid}")
    List<String> RoleName(int uid);

}




