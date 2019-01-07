package cn.smile;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smile on 2018/12/9.
 */
@Configuration
public class MyConfiguration {
    @Configuration
    public class ShiroConfiguration {

        //将自己的验证方式加入容器
        @Bean
        public MyRealm MyRealm() {
            MyRealm MyRealm = new MyRealm();
            return MyRealm;
        }

        //权限管理，配置主要是Realm的管理认证
        @Bean
        public SecurityManager securityManager() {
            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            securityManager.setRealm(MyRealm());
            return securityManager;
        }

        //Filter工厂，设置对应的过滤条件和跳转条件
        @Bean
        public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            shiroFilterFactoryBean.setSecurityManager(securityManager);

            shiroFilterFactoryBean.setLoginUrl("/login");
            shiroFilterFactoryBean.setSuccessUrl("/index");
            shiroFilterFactoryBean.setUnauthorizedUrl("/error");

            Map<String,String> map = new HashMap<String, String>();
            map.put("/logout","anon");
            map.put("/**","authc");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
            return shiroFilterFactoryBean;
        }

        /*加入注解的使用，不加入这个注解不生效
        @Bean
        public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
            AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
            authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
            return authorizationAttributeSourceAdvisor;
        }*/
    }
}
