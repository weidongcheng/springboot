package com.wdc.repository.secondary;

import com.wdc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wdc on 2021/3/29 14:13
 */
public interface SecondaryRepository extends MongoRepository<User,String>{
}
