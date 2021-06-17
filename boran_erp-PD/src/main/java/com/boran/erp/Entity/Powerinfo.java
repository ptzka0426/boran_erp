package com.boran.erp.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName powerinfo
 */
@TableName(value ="powerinfo")
@Data
public class Powerinfo implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限
     */
    private String powername;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
