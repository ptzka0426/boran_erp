package com.boran_erp.Controller;

import com.boran_erp.Client.AjaxJson;
import com.boran_erp.Client.userClient;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author LT
 * @create 2021-07-20 16:05
 */
@RestController
@RequestMapping("/userinfo")
@Scope("prototype")//多例
@Api(tags = "消费者")
public class UserController {
    @Autowired
    private userClient userClient;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_yhm", value = "用户名", required = true),
            @ApiImplicitParam(name = "user_pwd", value = "密码", required = true)
    })
    @ApiOperation(value = "登陆")
    @ApiOperationSupport(order = 1)
    public AjaxJson login(@RequestParam Map<String, Object> userInfo) {
      return userClient.login(userInfo);
    }
}
