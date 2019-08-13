package com.cn.jxf.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching // 标注启动缓存
public class CacheConfiguration {
	/**
     * ehcache 主要的管理器
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }
    
    @Bean(name="userCache")
	public EhCacheFactoryBean userCache() {
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("user");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
    
    @Bean(name="resCache")
	public EhCacheFactoryBean resCache() {
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("res");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
    
    @Bean(name="roleCache")
	public EhCacheFactoryBean roleCache() {
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("role");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
    
	@Bean(name = "dicCache")
	public EhCacheFactoryBean dicCache() {
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("dicCache");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean()
				.getObject());
		return ehCacheFactoryBean;
	}
	

}
