package com.wdc.service;

import com.wdc.model.UserInfo;

/**
 * Created by wdc on 2021/3/29 17:06
 */
public interface UserInfoService {
    /**
     * 通过 username 查找用户信息
     * @param username
     * @return
     */
    public UserInfo findByUsername(String username);
}
