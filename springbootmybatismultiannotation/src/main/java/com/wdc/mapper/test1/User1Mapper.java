package com.wdc.mapper.test1;

import com.wdc.enums.UserSexEnum;
import com.wdc.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wdc on 2021/3/11 9:41
 */
@Repository
public interface User1Mapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(column = "user_sex",property = "userSex",javaType = UserSexEnum.class),
            @Result(column = "nick_name",property = "nickName")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id=#{id}")
    @Results({
            @Result(column = "user_sex",property = "userSex",javaType = UserSexEnum.class),
            @Result(column = "nick_name",property = "nickName")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName},#{passWord},#{userSex})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(Long id);
}
