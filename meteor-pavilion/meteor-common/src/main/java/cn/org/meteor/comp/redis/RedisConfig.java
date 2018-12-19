package cn.org.meteor.comp.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: RedisConfig.java
 * @包 路 径： cn.org.meteor.comp
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: Redis配置类
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/11 10:22
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.maxTotal}")
    private String maxTotal;
    @Value("${redis.maxWaitMills}")
    private String maxWaitMills;
    @Value("${redis.maxIdle}")
    private String maxIdle;
    @Value("${redis.minIdle}")
    private String minIdle;
    @Value("${redis.timeout}")
    private String timeout;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisConnectionFactory jedisConnectoryFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(maxTotal));
        jedisPoolConfig.setMaxWaitMillis(Long.valueOf(maxWaitMills));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));
        jedisPoolConfig.setMinIdle(Integer.valueOf(minIdle));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(Integer.valueOf(port));
        jedisConnectionFactory.setTimeout(Integer.valueOf(timeout));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPassword(password);
        return jedisConnectionFactory;
    }

    /**
     * 使用redis模板
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        this.initDomainRedisTemplate(redisTemplate);
        return redisTemplate;
    }

    public void initDomainRedisTemplate(RedisTemplate<String,Object> redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(this.jedisConnectoryFactory());
        //不开启redis事务
        redisTemplate.setEnableTransactionSupport(false);
    }
    /**
     * 使用springCache，整合redis
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(this.redisTemplate());
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
