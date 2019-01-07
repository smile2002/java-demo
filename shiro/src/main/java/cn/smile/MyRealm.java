package cn.smile;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Smile on 2018/12/7.
 */
public class MyRealm implements Realm{
    @Override
    public String getName() {
        return "myRealm!!";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String)token.getPrincipal();
        String pwd = new String((char[])token.getCredentials());
        if (!"wangx".equals(name)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(pwd)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(name, pwd, getName());
    }
}
