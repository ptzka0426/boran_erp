package com.boran.erp.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.boran.erp.Client.userClient;
import com.boran.erp.Handler.SentinelHandlersClass;
import com.boran.erp.Util.AjaxJson;
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
public class UserController {
    @Autowired
    private com.boran.erp.Client.userClient userClient;

    /*熔断器的value和资源名都指向方法名（与访问接口value无关）*/
    /*@SentinelResource(value = "login", fallback = "handleException", blockHandler = "handleError")*/
    @SentinelResource(value = "login",
            blockHandlerClass = SentinelHandlersClass.class, //限流兜底方法所在类
            blockHandler = "handleException",  //限流时兜底方法
            fallbackClass = SentinelHandlersClass.class, //异常兜底放方法所在类
            fallback = "handleError")  //异常时兜底
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxJson login(@RequestParam Map<String, Object> userInfo) {
        return userClient.login(userInfo);
    }

   /* public AjaxJson fallbackHandler(Map<String, Object> userInfo, BlockException exception) {
        System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下：" + exception);
        return AjaxJson.getError("服务限流处理-托底数据");
    }

    public AjaxJson selectProductByIdFallback(Map<String, Object> userInfo, BlockException exception) {
        System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下：" + exception);
        return AjaxJson.getError("服务熔断降级处理-托底数据");
    }*/
}
