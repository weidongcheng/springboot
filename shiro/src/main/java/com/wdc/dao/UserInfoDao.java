package com.wdc.dao;

import com.wdc.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wdc on 2021/3/29 16:26
 */
public interface UserInfoDao  extends CrudRepository<UserInfo,Long>{

    /**
     * 通过username 查找用户信息
     * @param username
     * @return
     */
    public UserInfo findByUsername(String username);
}
