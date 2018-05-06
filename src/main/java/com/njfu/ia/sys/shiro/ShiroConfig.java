package com.njfu.ia.sys.shiro;

import com.njfu.ia.sys.domain.Permission;
import com.njfu.ia.sys.service.SecurityService;
import com.njfu.ia.sys.utils.Constants;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig implements ApplicationContextAware {

    private ApplicationContext context;

    /**
     * 配置realm，用于认证、授权
     *
     * @return Realm
     */
    @Bean
    public Realm authRealm() {
        // 凭证匹配器，配置加密方式和hash次数
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Constants.HASH_CREDENTIAL_NAME);
        credentialsMatcher.setHashIterations(Constants.HASH_ITERATIONS);

        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentialsMatcher);
        return authRealm;
    }

    /**
     * 配置EhCache缓存管理器，用于授权信息缓存
     *
     * @return CacheManager
     */
    private CacheManager getEhCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
        return cacheManager;
    }

    /**
     * 配置SecurityManager
     *
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm());
        if (Constants.USE_EHCACHE) {
            securityManager.setCacheManager(getEhCacheManager());
        }
        return securityManager;
    }

    /**
     * 设置由servlet容器管理filter生命周期
     *
     * @return LifecycleBeanPostProcessor
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启aop，对类代理
     *
     * @return Proxy
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启shiro注解支持
     *
     * @return Advisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * 配置shiroFilter，beanName必须为shiroFilter
     *
     * @return ShiroFilter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        // 配置SecurityManager
        filter.setSecurityManager(securityManager());

        // 配置登录页
        filter.setLoginUrl("/");
        // 登录成功跳转链接
        filter.setSuccessUrl("/sys");
        // 未授权界面
        filter.setUnauthorizedUrl("/403");
        // 拦截器链，由上到下顺序执行
        Map<String, String> filterChain = new LinkedHashMap<>();
        if (Constants.USE_SHIRO) {
            // 配置ajax登录url匿名访问
            filterChain.put("/login", "anon");
            // 配置登出路径
            filterChain.put("/logout", "logout");
            // 静态资源处理
            filterChain.put("/js/**", "anon");
            filterChain.put("/css/**", "anon");
            filterChain.put("/img/**", "anon");
            // 账号管理
            filterChain.put("/sys/auth/user/modify", "authc");

            // 动态添加权限
            SecurityService securityService = null;
            while (securityService == null) {
                securityService = (SecurityService) context.getBean("securityServiceImpl");
            }
            List<Permission> permissions = securityService.queryPermissions();
            for (Permission permission : permissions) {
                filterChain.put(permission.getUrl(), this.adaptPerms(permission.getPerm()));
            }

            // 认证后访问
            filterChain.put("/**", "authc");
        } else {
            // 开发模式
            filterChain.put("/**", "anon");
        }

        filter.setFilterChainDefinitionMap(filterChain);
        return filter;
    }

    /**
     * 适配拦截器权限标识符
     *
     * @param perm perm
     * @return perms[]
     */
    private String adaptPerms(String perm) {
        StringBuilder sb = new StringBuilder();
        sb.append("perms[").append(perm).append("]");
        return sb.toString();
    }

    /**
     * 获取ApplicationContext
     *
     * @param applicationContext applicationContext
     * @throws BeansException BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
