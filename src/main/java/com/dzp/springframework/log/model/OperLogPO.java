package com.dzp.springframework.log.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunshine.buddhistlife.core.base.BaseModel;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OperLogPO 实体Bean.
 *
 * @classname: com.sunshine.buddhistlife.component.log.model.OperLogPO.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-06-29 17:08:51
 * @version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bl_oper_log")
public class OperLogPO extends BaseModel<OperLogPO> {

    /**
     * 功能模块.
     */
    private String operModule;

    /**
     * 功能子模块.
     */
    private String operSubModule;

    /**
     * 操作类型.
     */
    private String operAction;

    /**
     * 操作描述.
     */
    private String operDesc;

    /**
     * 请求唯一标识ID.
     */
    private String operReqId;

    /**
     * 请求头.
     */
    private String operReqHeader;

    /**
     * 请求参数.
     */
    private String operReqParam;

    /**
     * 响应参数.
     */
    private String operRespParam;

    /**
     * 操作员ID.
     */
    private String operUserId;

    /**
     * 操作员名称.
     */
    private String operUserName;

    /**
     * 操作方法.
     */
    private String operMethod;

    /**
     * 请求URI.
     */
    private String operUri;

    /**
     * 请求IP.
     */
    private String operIp;

    /**
     * 操作API版本.
     */
    private String operVersion;

    /**
     * 是否操作成功（0-操作成功，1-操作异常）.
     */
    private Integer operSuccess;

    /**
     * 操作时间.
     */
    private LocalDateTime operTime;

    /**
     * 耗费时间（ms）.
     */
    private Long operCost;


}
