package com.boran_erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients// 开启 Feign 功能
@EnableDiscoveryClient
@SpringBootApplication
public class BoranErpCsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoranErpCsApplication.class, args);
    }

}
