package com.wdc.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wdc on 2021/3/11 10:48
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue wdcQueue(){
        return new Queue("wdc");
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object");
    }
}
