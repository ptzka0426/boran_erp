package com.boran.erp.Controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boran.erp.Entity.UserInfo;
import com.boran.erp.Util.AjaxJson;
import com.boran.erp.service.UserInfoService;
import com.boran.erp.service.UserRoleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author LT
 * @create 2021-06-16 15:40
 */
@RestController
@RequestMapping("/userinfo")
@CrossOrigin()
@Api(tags = "登陆模块")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    //private UserInfoService userv;
    private UserRoleService userrole;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_yhm", value = "用户名", required = true),
            @ApiImplicitParam(name = "user_pwd", value = "密码", required = true)
    })
    @ApiOperation(value = "登陆")
    @ApiOperationSupport(order = 1)
    public AjaxJson login(@RequestParam Map<String, Object> userInfo) {
/*        userInfo.forEach((key, value) -> {
            System.out.println(key);
        });*/
        if (userInfo.containsKey("user_yhm") && userInfo.containsKey("user_pwd")) {
            List<UserInfo> userInfos = userInfoService.listByMap(userInfo);
            if (userInfos.size() <= 0) {
                return AjaxJson.getError("用户名或密码错误！");
            } else {
                //判断是否已登录，
                System.out.println(StpUtil.isLogin());
                if (!StpUtil.isLogin()) {
                    StpUtil.setLoginId(userInfos.get(0).getId(), new SaLoginModel()
                            .setDevice("PC")                // 此次登录的客户端设备标识, 用于[同端互斥登录]时指定此次登录的设备名称
                            .setIsLastingCookie(true)        // 是否为持久Cookie（临时Cookie在浏览器关闭时会自动删除，持久Cookie在重新打开后依然存在）
                            .setTimeout(60 * 60)    // 指定此次登录token的有效期, 单位:（60*60）秒 （如未指定，自动取全局配置的timeout值）
                    );
                }
                /*System.out.println(StpUtil.hasRole("超级管理员"));
                System.out.println(StpUtil.hasPermission("超级管理员:Insert"));
                System.out.println(StpUtil.hasPermission("普通用户:Delete"));*/
                /*satoken令牌认证*/
                // return AjaxJson.getSuccess("登陆成功", "{token:" + StpUtil.getTokenName() + ",tokenvalue:" + StpUtil.getTokenValue() + "}");
                return AjaxJson.getSuccess("登陆成功", StpUtil.getTokenInfo());
            }
        }
        return AjaxJson.getError("未包含相应字段");
    }

    @ApiOperation(value = "测试分页")
    @RequestMapping(value = "lists", method = RequestMethod.POST)
    @ApiOperationSupport(order = 100)
    @SaCheckLogin//判断是否登陆
    public AjaxJson selectAndlist(int id, int pageNo, int pageSize, int desc) {
        IPage<UserInfo> page = new Page<>(pageNo, pageSize);

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        //做条件筛选
        if (id != 1) {
            UserInfo l = new UserInfo();
            l.setId(id);
            wrapper.setEntity(l);
        }
        if (desc == 1) {
            wrapper.orderByAsc("user_time");
        } else {
            wrapper.orderByDesc("user_time");
        }
        return AjaxJson.getSuccess("分页", userInfoService.page(page, wrapper).getRecords().toArray());
    }
}
