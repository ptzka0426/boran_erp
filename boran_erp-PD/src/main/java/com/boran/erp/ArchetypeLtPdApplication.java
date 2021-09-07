package com.boran.erp;

import cn.dev33.satoken.SaManager;
import com.boran.erp.Config.Seata.SeataDataSoucreConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自带创建
/*@Import({SeataDataSoucreConfigure.class})*///导入seata代理数据源
public class ArchetypeLtPdApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArchetypeLtPdApplication.class);

    public static void main(String[] args) {
        logger.info("\n===============项目启动了===============");
        SpringApplication.run(ArchetypeLtPdApplication.class, args);
        System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
        logger.info("\n===============启动成功了===============");
    }

}
