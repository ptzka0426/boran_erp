package com.boran.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BoranErpZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoranErpZuulApplication.class, args);
    }

}
