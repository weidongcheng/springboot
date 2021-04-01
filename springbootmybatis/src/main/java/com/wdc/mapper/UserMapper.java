package com.wdc.mapper;

import com.wdc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wdc on 2021/3/10 10:16
 */
@Repository
public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
