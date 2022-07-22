package com.dzp.springframework.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    /**
     * 交换机名称
     */
    public  final static String PUSH_EXCHANGE = "test_sout";

    /**
     * 队列名称
     */
    public   final static String PUSH_QUEUE = "delay_queue_push";

    /**
     * routingKey
     */
    public  final static String PUSH_ROUTING_KEY = "delay_key_push";

    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     *
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        return new CustomExchange(PUSH_EXCHANGE, "x-delayed-message", true, false, args);
    }


    /**
     * 延时队列
     */
    @Bean
    public Queue queue() {
        Map<String, Object> map = new HashMap<>();
        // 生存时间5min
        map.put("x-message-ttl", 10*60*1000);
        // 设置队列关联的死信交换器(消息到期没消费进死信队列)
        map.put("x-dead-letter-exchange", "ex.go.dlx");
        // 设置队列关联的死信交换器routingkey
        map.put("x-dead-letter-routing-key", "go.glx");
        Queue queue = new Queue("q.go", true, false, false, map);
        return queue;
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue queueDlx() {
        Queue queue = new Queue("q.go.dlx", true, false, false);
        return queue;
    }

    /**
     * 交换器
     * @return
     */
    @Bean
    public Exchange exchange() {
        DirectExchange exchange = new DirectExchange("ex.go", true, false, null);
        return exchange;
    }
    /**
     * 死信交换器
     * @return
     */
    @Bean
    public Exchange exchangeDlx() {
        DirectExchange exchange = new DirectExchange("ex.go.dlx", true, false, null);
        return exchange;
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("go").noargs();
    }

    /**
     * 绑定死信
     * @return
     */
    @Bean
    public Binding bindingDlx() {
        return BindingBuilder.bind(queueDlx()).to(exchangeDlx()).with("go.dlx").noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate();
    }

}
