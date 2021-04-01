package com.wdc.config;

import com.wdc.model.SysPermission;
import com.wdc.model.SysRole;
import com.wdc.model.UserInfo;
import com.wdc.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by wdc on 2021/3/29 16:56
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authenticationInfo = null;
        try {
            System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
            authenticationInfo = new SimpleAuthorizationInfo();
            UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
            for(SysRole role:userInfo.getRoleList()){
                authenticationInfo.addRole(role.getRole());
                for(SysPermission p: role.getPermissions()){
                    authenticationInfo.addStringPermission(p.getPermission());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("userInfo---->>>"+userInfo);
        if(userInfo==null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,//用户名
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getCredenialsSalt()),
                getName());
        return authenticationInfo;
    }
}
