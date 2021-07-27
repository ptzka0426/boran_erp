package com.boran.erp.Entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author LT
 * @create 2021-04-27 10:29
 * 自定义注入测试
 */
@Component
@Data
//扫描yml的自定义值
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
    //拿取yml下的未定义sss
    @Value("sss")
    private String lt;
}
