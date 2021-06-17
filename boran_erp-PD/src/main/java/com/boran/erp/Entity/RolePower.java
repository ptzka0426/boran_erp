package com.boran.erp.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName role_power
 */
@TableName(value ="role_power")
@Data
public class RolePower implements Serializable {
    /**
     * 角色权限表
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色
     */
    private Integer roleid;

    /**
     * 权限
     */
    private Integer powerid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
