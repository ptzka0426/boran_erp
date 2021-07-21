package com.boran.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ArchetypeLtPdApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArchetypeLtPdApplication.class);

    public static void main(String[] args) {
        logger.info("\n===============项目启动了===============");
        SpringApplication.run(ArchetypeLtPdApplication.class, args);
        logger.info("\n===============启动成功了===============");
    }

}
