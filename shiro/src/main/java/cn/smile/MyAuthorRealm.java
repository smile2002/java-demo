package cn.smile;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by Smile on 2018/12/7.
 */
public class MyAuthorRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();
        authorInfo.addRole("role1");
        authorInfo.addRole("role2");
        authorInfo.addObjectPermission(new WildcardPermission("user:*"));
        System.out.println("doGetAuthorizationInfo invoked!");
        return authorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String)token.getPrincipal();
        String pwd = new String((char[])token.getCredentials());
        if (!"wang".equals(name)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(pwd)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(name, pwd, getName());
    }
}
