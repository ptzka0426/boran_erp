package com.boran.erp.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName user_role
 */
@TableName(value ="user_role")
@Data
public class UserRole implements Serializable {
    /**
     * 用户角色表
     */
    @TableId
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 角色id
     */
    private Integer roleid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
