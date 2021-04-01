package com.wdc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wdc on 2021/3/29 15:56
 */
@Entity
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue
    private Integer uid;

    /**
     * 账号。
     */
    @Column(unique = true)
    private String username;

    /**
     * 名称。（昵称或真实姓名）
     */
    private String name;

    /**
     * 密码。
     */
    private String password;

    /**
     * 加密密码的盐。
     */
    private String salt;

    /**
     * 用户状态 0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.。
     */
    private byte state;

    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name = "uid")}
        ,inverseJoinColumns = {@JoinColumn(name = "roleId")})
    @ManyToMany(fetch = FetchType.EAGER) //立即从数据库中加载数据
    private List<SysRole> roleList;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐  重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解。
     * @return
     */
    public String getCredenialsSalt(){
        return this.username+this.salt;
    }
}
