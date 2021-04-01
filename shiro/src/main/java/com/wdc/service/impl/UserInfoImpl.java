package com.wdc.service.impl;

import com.wdc.dao.UserInfoDao;
import com.wdc.model.UserInfo;
import com.wdc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wdc on 2021/3/29 17:07
 */
@Service
public class UserInfoImpl implements UserInfoService{

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
