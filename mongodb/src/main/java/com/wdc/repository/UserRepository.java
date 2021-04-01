package com.wdc.repository;

import com.wdc.model.User;

/**
 * Created by wdc on 2021/3/29 10:58
 */
public interface UserRepository {
    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserByUserId(Long id);
}
