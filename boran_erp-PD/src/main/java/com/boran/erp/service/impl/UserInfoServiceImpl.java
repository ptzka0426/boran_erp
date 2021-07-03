package com.boran.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boran.erp.Entity.UserInfo;
import com.boran.erp.mapper.UserInfoMapper;
import com.boran.erp.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Author LT
 * @create 2021-06-16 15:36
 * 线程池的配置
 */
@Service
@AutoConfigureAfter({UserInfoMapper.class})
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoMapper.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");

        logger.info("end executeAsync");
    }

    @Override
    @Async("asyncServiceExecutor")
    public ListenableFuture<String> sayHello(String name) {
        String res = name + ":Hello World!";
        logger.info(res);
        return new AsyncResult<>(res);
    }
}
