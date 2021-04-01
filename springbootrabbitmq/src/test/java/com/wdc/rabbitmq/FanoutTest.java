package com.wdc.rabbitmq;

import com.wdc.rabbit.fanout.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wdc on 2021/3/11 11:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {


    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() throws Exception {
        sender.send();
    }
}
