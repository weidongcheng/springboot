package com.wdc.service;

import com.wdc.model.UserDetail;
import com.wdc.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by
 *
 * @author wdc
 * @date 2021/3/9.
 */
public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
