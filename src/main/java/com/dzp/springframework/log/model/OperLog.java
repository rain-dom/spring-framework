package com.dzp.springframework.log.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志注解.
 *
 * @classname: com.sunshine.buddhistlife.component.log.model.OperLog.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-06-23 22:35:33
 * @version: V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperLog {

    /**
     * 功能模块.
     *
     * @return
     */
    String operModule();

    /**
     * 功能子模块.
     *
     * @return
     */
    String operSubModule();

    /**
     * 操作类型.
     *
     * @return
     */
    String operAction();

    /**
     * 操作描述.
     *
     * @return
     */
    String operDesc();
}