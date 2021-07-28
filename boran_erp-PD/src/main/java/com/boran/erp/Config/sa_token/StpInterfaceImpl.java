package com.boran.erp.Config.sa_token;

/**
 * @Author LT
 * @create 2021-06-17 11:16
 */

import cn.dev33.satoken.stp.StpInterface;
import com.boran.erp.Util.RedisUtils;
import com.boran.erp.service.PowerinfoService;
import com.boran.erp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    //private UserInfoService userv;
    private UserRoleService userrole;
    @Autowired
    private PowerinfoService powerService;
    @Autowired
    RedisUtils redisUtils;

    /**
     * @param loginId  存入的id
     * @param loginKey loginkey 角色
     *                 返回一个账号所拥有的权限码集合,数据库角色和权限
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        try {
            if (loginKey.equals("login")) {
                //拿到角色
                List<String> RoleName = new ArrayList<>();//userrole.RoleName(Integer.parseInt(loginId.toString()));
                if (redisUtils.hasKey("sa_token_role" + loginId)) {//判断角色用户是否有缓存
                    RoleName = redisUtils.lRange("sa_token_role" + loginId, 0, -1);
                } else {
                    RoleName = userrole.RoleName(Integer.parseInt(loginId.toString()));
                    RoleName.forEach(Role -> {
                        redisUtils.lLeftPushAll("sa_token_role" + loginId, Role);
                    });
                    //设置过期时间
                    redisUtils.expire("sa_token_role" + loginId, 790, TimeUnit.SECONDS);
                }
                //角色——》权限
                List<String> pwr = new ArrayList<>();
                if (redisUtils.hasKey("sa_token_power" + loginId)) {//判断是否有缓存
                    pwr = redisUtils.lRange("sa_token_power" + loginId, 0, -1);
                } else {
                    List<String> finalPwr = pwr;
                    RoleName.forEach(role -> {
                        powerService.PowerName(role).forEach(power -> {
                            //写入redis
                            redisUtils.lLeftPush("sa_token_power" + loginId, role + ":" + power);
                            finalPwr.add(role + ":" + power);
                        });
                    });
                    //设置过期时间
                    redisUtils.expire("sa_token_power" + loginId, 790, TimeUnit.SECONDS);
                }
                return pwr;
            }
        } catch (Exception e) {
            System.out.println("角色权限认证异常......");
        }
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        try {
            //拿到角色
            List<String> RoleName = new ArrayList<>();//userrole.RoleName(Integer.parseInt(loginId.toString()));
            if (redisUtils.hasKey("sa_token_role" + loginId)) {//判断角色用户是否有缓存
                RoleName = redisUtils.lRange("sa_token_role" + loginId, 0, -1);
            } else {
                RoleName = userrole.RoleName(Integer.parseInt(loginId.toString()));
                RoleName.forEach(Role -> {
                    redisUtils.lLeftPushAll("sa_token_role" + loginId, Role);
                });
                //设置过期时间
                redisUtils.expire("sa_token_role" + loginId, 790, TimeUnit.SECONDS);
            }
            return RoleName;
        } catch (Exception e) {
            System.out.println("角色认证异常......");
        }
        return null;
    }

}


