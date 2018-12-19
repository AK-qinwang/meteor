package cn.org.meteor.comp.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: SessionConfiguration.java
 * @包 路 径： cn.org.meteor.comp.interceptor
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:注册拦截器
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/3 15:07
 */
@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/loginView","/static/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
