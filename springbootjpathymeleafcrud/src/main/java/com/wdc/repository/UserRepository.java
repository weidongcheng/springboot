package com.wdc.repository;

import com.wdc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wdc on 2021/3/30 10:25
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User findById(long id);

    void deleteById(long id);
}
