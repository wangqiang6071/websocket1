package com.wayne.websocket.RabbitMQ.DelayMessage;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyRabbitMQConfig {
    //实现思路是一个交换机器可以绑定一个延时队列和多个普通队列 但是在延时队列生成之后 延时的时间就不可以更改了  除非重新生成 还有就是当前账号下才能看见生成的信息
    //延时队列
    public static final String delayQueue = "delayQueue";

    //正常队列
    public static final String normalQueue = "normalQueue";

    //交换机
    public static final String delayExchange = "delayExchange";

    //延时队列路由键
    public static final String DELAY_ROUTING_KEY = "delay-routing-key";

    //正常队列路由键
    public static final String NORMAL_ROUTING_KEY = "normal-routing-key";
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //创建一个延时队列
    @Bean
    public Queue getDelayQueue() {
        Map args = new HashMap();
        /**
         * 消息发送给延时队列
         * 设置延时队列的过期时间为5秒钟
         * 20秒之后，延时队列将消息发送给普通队列
         */
        //args.put("x-dead-letter-exchange", normalExchange);
        args.put("x-dead-letter-exchange", delayExchange);
        args.put("x-dead-letter-routing-key", NORMAL_ROUTING_KEY);
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(delayQueue).withArguments(args).build();
    }

    //创建交换机
    @Bean
    public Exchange getDelayExchange() {
        return ExchangeBuilder.directExchange(delayExchange).durable(true).build();
    }

    //延时队列与交换机进行绑定
    @Bean
    public Binding bindDelay() {
        return BindingBuilder.bind(getDelayQueue()).to(getDelayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }

    //创建普通队列
    @Bean
    public Queue getNormalQueue() {
        return new Queue(normalQueue);
    }


    //普通队列与交换机进行绑定
    @Bean
    public Binding bindNormal() {
        return BindingBuilder.bind(getNormalQueue()).to(getDelayExchange()).with(NORMAL_ROUTING_KEY).noargs();
    }

}
