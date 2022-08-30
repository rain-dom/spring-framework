package com.dzp.springframework.thread.model;

import lombok.Data;

/**
 * 线程池基本配置属性Bean.
 *
 * @classname: com.sunshine.buddhistlife.component.thread.model.BaseThreadPoolProperties.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-06-23 22:13:38
 * @version: V1.0
 */
@Data
public abstract class BaseThreadPoolProperties {

    /**
     * 核心线程数.
     */
    private Integer coreSize;

    /**
     * 最大线程数.
     */
    private Integer maxSize;

    /**
     * 线程名.
     */
    private String threadName;

    /**
     * 线程空闲时间.
     */
    private Integer keepliveTime;

    /**
     * 缓存队列大小.
     */
    private Integer queueCapacity;

    /**
     * 线程池拒绝策略.
     */
    private String rejectStrategy;
}
