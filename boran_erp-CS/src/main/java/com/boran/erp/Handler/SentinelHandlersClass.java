package com.boran.erp.Handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.boran.erp.Util.AjaxJson;

import java.util.Map;

/**
 * @Author LT
 * @create 2021-07-21 16:59
 */
public class SentinelHandlersClass {
    public static AjaxJson handleException(Map<String, Object> userInfo, BlockException exception) {
        System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下：" + exception);
        return AjaxJson.getError("服务限流处理-托底数据");
    }

    public static AjaxJson handleError(Map<String, Object> userInfo, BlockException exception) {
        System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下：" + exception);
        return AjaxJson.getError("服务熔断降级处理-托底数据");
    }
}
