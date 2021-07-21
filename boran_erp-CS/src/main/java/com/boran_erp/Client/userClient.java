package com.boran_erp.Client;

import com.boran_erp.AjaxJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author LT
 * @create 2021-07-20 16:06
 */

/*@FeignClient(name = "provide-nacos-service", fallback = userClientImpl.class)*///继承时写入异常
@FeignClient(name = "provide-nacos-service")//自定义配置sentinel
public interface userClient {


    @RequestMapping(value = "/userinfo/login", method = RequestMethod.POST)
    public AjaxJson login(@RequestParam Map<String, Object> userInfo);

}
