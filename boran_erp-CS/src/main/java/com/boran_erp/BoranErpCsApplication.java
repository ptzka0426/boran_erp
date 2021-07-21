package com.boran_erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BoranErpCsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoranErpCsApplication.class, args);
    }

}
