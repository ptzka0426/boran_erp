package com.boran.erp.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author LT
 * @create 2021-06-15 15:24
 */
@NoArgsConstructor//给类添加一个无参数的构造方法。
/*@AllArgsConstructor//给类添加一个全参的构造方法。*/
@Accessors(chain=true)//让该类的实体类支持链式访问呢。
@Data
@TableName("userinfo")//注解实体类名
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String user_yhm;
    private String user_pwd;
    private String user_name;
    private Date user_time;
}
/*
@TableField(exist = false)：表示该属性不为数据库表字段，但又是必须使用的。

@TableField(exist = true)：表示该属性为数据库表字段。*/
