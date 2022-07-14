package com.sunshine.buddhistlife.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 实体类基类.
 *
 * @version: V1.0
 * @classname: com.sunshine.buddhistlife.core.base.BaseModel.java
 * @copyright: Powered By sunshine
 * @author: somnus
 * @date: 2020-11-03 18:38:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel<T extends BaseModel<T>> {

    /** 唯一标识. */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建者. */
    private String creater;

    /** 创建时间. */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新者. */
    private String updater;

    /** 更新时间. */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 是否删除. */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;
}
