package cn.org.meteor.comp;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
 * @创建时间：2018/11/19 18:37
 */
@SpringBootApplication
@ImportResource("classpath:dubbo-consumer.xml")
public class Application {
    /**
     * 核心启动类
     * @param args
     */
    public static void main(String[] args) {
        properties();
        SpringApplication.run(Application.class);
    }
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);
        final List<Resource> resourceList = new ArrayList<Resource>();
        resourceList.add(new ClassPathResource("config/jdbc.properties"));
        resourceList.add(new ClassPathResource("application.properties"));
        ppc.setLocations(resourceList.toArray(new Resource[]{}));
        return ppc;
    }
}
