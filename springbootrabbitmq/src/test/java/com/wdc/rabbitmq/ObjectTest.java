package com.wdc.rabbitmq;

import com.wdc.model.User;
import com.wdc.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wdc on 2021/3/11 11:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    @Test
    public void sendOject() throws Exception {
        User user=new User();
        user.setName("wdc");
        user.setPass("123456");
        sender.send(user);
    }
}
