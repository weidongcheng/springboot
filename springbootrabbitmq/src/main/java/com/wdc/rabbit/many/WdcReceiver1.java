package com.wdc.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by wdc on 2021/3/11 11:27
 */
@Component
@RabbitListener(queues = "wdc")
public class WdcReceiver1 {

    @RabbitHandler
    public void process(String wdc){
        System.out.println("Receiver 1: " + wdc);
    }
}
