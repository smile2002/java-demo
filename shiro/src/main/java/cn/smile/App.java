package cn.smile;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authorizer.ini");

        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        System.out.println(SecurityUtils.class);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("auth result : " + subject.isAuthenticated());
        System.out.println("has role \"role1\": " + subject.hasRole("role1"));
        System.out.println("has role \"role3\": " + subject.hasRole("role3"));

        System.out.println("check perm \"user:create\": " + subject.isPermitted("user:delete"));
        System.out.println("check perm \"user:delete\": " + subject.isPermitted("user:list"));


        subject.logout();
    }
}
