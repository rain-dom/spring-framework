package com.dzp.springframework.thread.model;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.AllArgsConstructor;

/**
 * 线程池拒绝策略.
 *
 * @classname: com.sunshine.buddhistlife.component.thread.model.ThreadPoolRejectStrategy.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-07-13 16:13:09
 * @version: V1.0
 */
@AllArgsConstructor
public enum ThreadPoolRejectStrategy {

    /**
     * 拒绝任务，并抛出异常.
     */
    ABORT(new ThreadPoolExecutor.AbortPolicy()),

    /**
     * 由调用自身执行线程执行.
     */
    CALLRUNS(new ThreadPoolExecutor.CallerRunsPolicy()),


    /**
     * 直接丢失任务.
     */
    DISCARD(new ThreadPoolExecutor.DiscardPolicy()),

    /**
     * 移除队列中旧任务.
     */
    DISCARD_OLD(new ThreadPoolExecutor.DiscardOldestPolicy())
    ;

    private RejectedExecutionHandler handler;

    /**
     * 根据策略名称获取策略执行器(默认拒绝抛出异常).
     *
     * @param strategyName  策略名
     * @return {@link RejectedExecutionHandler}
     */
    public static RejectedExecutionHandler getRejectStrategy(String strategyName) {
        if (StringUtils.isBlank(strategyName)) {
            return ABORT.handler;
        }
        for (ThreadPoolRejectStrategy strategy: values()) {
            if (strategy.name().equals(strategyName)) {
                return strategy.handler;
            }
        }
        return ABORT.handler;
    }
}
