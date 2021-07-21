package com.boran_erp.Client;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.boran_erp.AjaxJson;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author LT
 * @create 2021-07-21 10:47
 * 未完善...
 */
/*@Component*/
public class userClientImpl implements userClient {
    @Autowired
    BlockException e; //异常拿取问题
    @Override
    public AjaxJson login(Map<String, Object> userInfo) {
        //BlockException 异常接口,包含Sentinel的五个异常
        // FlowException 限流异常
        // DegradeException 降级异常
        // ParamFlowException 参数限流异常
        // AuthorityException 授权异常
        // SystemBlockException 系统负载异常
        String data = null;
        if (e instanceof FlowException) {
            data = "流控规则被触发......";
        } else if (e instanceof DegradeException) {
            data = "降级规则被触发...";
        } else if (e instanceof AuthorityException) {
            data = "授权规则被触发...";
        } else if (e instanceof ParamFlowException) {
            data = "热点规则被触发...";
        } else if (e instanceof SystemBlockException) {
            data = "系统规则被触发...";
        } else {
            data = "其他异常";
        }
        return AjaxJson.getError(data);
    }
}
