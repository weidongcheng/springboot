package com.wdc.springbootmultijpa.repository.test1;

import com.wdc.springbootmultijpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wdc on 2021/3/9 16:48
 */
public interface UserTest1Repository extends JpaRepository<User,Long> {
    User findById(long id);
    User findByUserName(String userName);
    User findByUserNameOrEmail(String userName,String email);
}
