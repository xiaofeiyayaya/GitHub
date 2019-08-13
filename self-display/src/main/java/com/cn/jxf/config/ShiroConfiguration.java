package com.cn.jxf.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cn.jxf.service.user.impl.MyShiroRealm;

@Configuration
public class ShiroConfiguration {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/user/logon", "anon");
		filterChainDefinitionMap.put("/user/main", "authc");
		filterChainDefinitionMap.put("/user/userList", "authc");
		filterChainDefinitionMap.put("/user/toAddUser", "authc");
		filterChainDefinitionMap.put("/user/userAdd", "authc");
		filterChainDefinitionMap.put("/user/toEditUser", "authc");
		filterChainDefinitionMap.put("/user/editUser", "authc");
		filterChainDefinitionMap.put("/user//deleteUser", "authc");
		filterChainDefinitionMap.put("/user/toResetPsd", "authc");
		filterChainDefinitionMap.put("/user/resetPsd", "authc");
		filterChainDefinitionMap.put("/pool/*", "authc");
		filterChainDefinitionMap.put("/trade/*", "authc");
		filterChainDefinitionMap.put("/grade/*", "authc");
		filterChainDefinitionMap.put("/approve/*", "authc");
		filterChainDefinitionMap.put("/balance/*", "authc");
		filterChainDefinitionMap.put("/role/*", "authc");
		filterChainDefinitionMap.put("/res/*", "authc");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/user/dologin");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/user/main");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");

		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//securityManager.setSessionManager
		//设置realm
		securityManager.setRealm(myShiroRealm());
		//注入缓存管理器;
	    securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
	    //注入记住我管理器
		//securityManager.setRememberMeManager(rememberMeManager());
	    
		return securityManager;
	}
	
	/**
	 * 身份认证realm;
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
	
	/**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
       return cacheManager;
    }
	
	
	/**
	  * cookie对象;
	  * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	  * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
	      //System.out.println("ShiroConfiguration.rememberMeCookie()");
	      //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	      SimpleCookie simpleCookie = new SimpleCookie("rememberme");
	      //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	      simpleCookie.setMaxAge(259200);
	      return simpleCookie;
	}

	/**
	  * cookie管理对象;
	  * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	  * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      //System.out.println("ShiroConfiguration.rememberMeManager()");
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
	      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
	      return cookieRememberMeManager;
	}
    
}
