package com.wdc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wdc on 2021/3/30 10:23
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
