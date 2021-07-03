package com.boran.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boran.erp.Entity.UserInfo;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Author LT
 * @create 2021-06-16 15:35
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 执行异步任务
     * 可以根据需求，自己加参数拟定，我这里就做个测试演示
     */
    void executeAsync();

    ListenableFuture<String> sayHello(String name);
}
