package com.wdc.repository;

import com.wdc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by
 *
 * @author wdc
 * @date 2021/3/9.
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName,String email);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String userName,Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    @Override
    void deleteById(Long id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u")
    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findByNickName(String nickName,Pageable pageable);

    Slice<User> findByNickNameAndEmail(String nickName,String email,Pageable pageable);
}
