package com.wdc.rabbitmq;

import com.wdc.rabbit.many.WdcSender;
import com.wdc.rabbit.many.WdcSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wdc on 2021/3/11 11:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {

    @Autowired
    private WdcSender wdcSender;

    @Autowired
    private WdcSender2 wdcSender2;

    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            wdcSender.send(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            wdcSender.send(i);
            wdcSender2.send(i);
        }
    }
}
