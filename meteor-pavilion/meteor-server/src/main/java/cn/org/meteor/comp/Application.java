package cn.org.meteor.comp;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: Application.java
 * @包 路 径： cn.org.meteor.comp
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/11/19 15:48
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "cn.org.meteor.comp")
//@ImportResource("classpath:dubbo-provider.xml")
@EnableCaching
public class Application {
    /**
     * 核心启动类
     * @param args
     */
    public static void main(String[] args) {
        properties();
        SpringApplication.run(Application.class,args);
    }

    @Bean
    private static PropertyPlaceholderConfigurer properties(){
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        final List<Resource> resourceList = new ArrayList<Resource>();
        ppc.setIgnoreResourceNotFound(true);
        resourceList.add(new ClassPathResource("config/jdbc.properties"));
        resourceList.add(new ClassPathResource("application.properties"));
        resourceList.add(new ClassPathResource("redis.properties"));
        ppc.setLocations(resourceList.toArray(new Resource[]{}));
        return ppc;
    }
}
