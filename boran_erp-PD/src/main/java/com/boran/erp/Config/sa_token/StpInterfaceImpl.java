package com.boran.erp.Config.sa_token;

/**
 * @Author LT
 * @create 2021-06-17 11:16
 */

import cn.dev33.satoken.stp.StpInterface;
import com.boran.erp.Util.RedisUtil;
import com.boran.erp.mapper.PowerinfoMapper;
import com.boran.erp.service.PowerinfoService;
import com.boran.erp.service.UserInfoService;
import com.boran.erp.service.UserRoleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    RedisUtil redisUtil;

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
                if (redisUtil.hasKey("sa_token_role" + loginId)) {//判断角色用户是否有缓存
                    RoleName = redisUtil.lRange("sa_token_role" + loginId, 0, -1);
                } else {
                    RoleName = userrole.RoleName(Integer.parseInt(loginId.toString()));
                    RoleName.forEach(Role -> {
                        redisUtil.lLeftPushAll("sa_token_role" + loginId, Role);
                    });
                    //设置过期时间
                    redisUtil.expire("sa_token_power" + loginId, 790, TimeUnit.SECONDS);
                }
                //角色——》权限
                List<String> pwr = new ArrayList<>();
                if (redisUtil.hasKey("sa_token_power" + loginId)) {//判断是否有缓存
                    pwr = redisUtil.lRange("sa_token_power" + loginId, 0, -1);
                } else {
                    List<String> finalPwr = pwr;
                    RoleName.forEach(role -> {
                        powerService.PowerName(role).forEach(power -> {
                            //写入redis
                            redisUtil.lLeftPush("sa_token_power" + loginId, role + ":" + power);
                            finalPwr.add(role + ":" + power);
                        });
                    });
                    //设置过期时间
                    redisUtil.expire("sa_token_power" + loginId, 790, TimeUnit.SECONDS);
                }
                return pwr;
            }
        } catch (Exception e) {
            System.out.println("权限认证异常......");
        }
        /*HashMap<String, List<String>> listHashMap = new HashMap<>();
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("article-get");
        listHashMap.put("10001", list);
        list = new ArrayList<>();
        list.add("user-add");
        listHashMap.put("10002", list);
        // list模拟数据库权限
        if (loginKey.equals("login")) {
            return listHashMap.get(loginId);
        }*/
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        try {
            if (loginKey.equals("login")) {
                return userrole.RoleName(Integer.parseInt(loginId.toString()));
            }
        } catch (Exception e) {
            return null;
        }
        /*List<String> lists = new ArrayList<String>();
        lists.add("10001");
        lists.add("10002");
        HashMap<String, List<String>> listHashMap = new HashMap<>();
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("user");
        list.add("super-admin");
        listHashMap.put("10001", list);
        list = new ArrayList<>();
        list.add("user");
        listHashMap.put("10002", list);
        // list模拟数据库角色
        System.out.println(loginKey);
        if (loginKey.equals("login")) {
            return listHashMap.get(loginId);
        }*/
        return null;
    }

}

