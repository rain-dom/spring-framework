package com.dzp.springframework.thread;

import com.dzp.springframework.thread.model.BaseThreadPoolProperties;
import com.dzp.springframework.thread.model.FileThreadPoolProperties;
import com.dzp.springframework.thread.model.LogThreadPoolProperties;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import com.dzp.springframework.thread.model.ThreadPoolRejectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置类.
 *
 * @classname: com.sunshine.buddhistlife.component.thread.ThreadPoolConfiguration.java
 * @copyright: Powered By Sunshine
 * @author: Chris
 * @date: 2022-06-23 22:29:32
 * @version: V1.0
 */
@Configuration
public class ThreadPoolConfiguration {

    @Autowired
    private LogThreadPoolProperties logThreadPoolProperties;

    @Autowired
    private FileThreadPoolProperties fileThreadPoolProperties;

    @Bean("logPool")
    public Executor logExecutor() {
        return init(logThreadPoolProperties);
    }

    @Bean("filePool")
    public Executor fileExecutor() {
        return init(fileThreadPoolProperties);
    }

    private Executor init(BaseThreadPoolProperties poolProperties) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(poolProperties.getCoreSize());
        taskExecutor.setMaxPoolSize(poolProperties.getMaxSize());
        taskExecutor.setKeepAliveSeconds(poolProperties.getKeepliveTime());
        taskExecutor.setThreadNamePrefix(poolProperties.getThreadName());
        taskExecutor.setQueueCapacity(poolProperties.getQueueCapacity());
        taskExecutor.setRejectedExecutionHandler(
                ThreadPoolRejectStrategy.getRejectStrategy(poolProperties.getRejectStrategy()));
        taskExecutor.initialize();
        return taskExecutor;
    }
}