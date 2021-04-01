package com.wdc.repository;

import com.wdc.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wdc on 2021/3/29 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        userRepository.saveUser(user);
    }

    @Test
    public void testFindByUserName() throws Exception{
      User user = userRepository.findUserByUserName("小明");
        System.out.println("user is: "+user);
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userRepository.updateUser(user);
    }

    @Test
    public void testDeleteUserById() throws Exception{
        userRepository.deleteUserByUserId(2l);
    }
}
