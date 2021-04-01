package com.wdc.service;

import com.wdc.model.User;

import java.util.List;

/**
 * Created by wdc on 2021/3/30 10:28
 */
public interface UserService {

    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);
}
