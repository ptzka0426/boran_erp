package com.boran.erp.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName roleinfo
 */
@TableName(value ="roleinfo")
@Data
public class Roleinfo implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String rolename;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
