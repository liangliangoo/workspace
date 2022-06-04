package com.xiaoxiong.redisdemo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/17 11:23
 */
@Configuration
@EnableCaching  // case2启动缓存
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // 使用Jackson2JsonRedisSerializer 替换默认的
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 设置redis序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        stringRedisTemplate.setKeySerializer(stringRedisSerializer);
        stringRedisTemplate.setValueSerializer(stringRedisSerializer);
        stringRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        stringRedisTemplate.setHashValueSerializer(stringRedisSerializer);
        return stringRedisTemplate;
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig()
                //默认缓存过期时间5分钟
                .entryTtl(Duration.ofSeconds(60*5))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));
        return new MyRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory), conf);
    }

    // 自定义缓存key的生成类实现如下：
    @Bean
    public KeyGenerator myKeyGenerator() {
        return (target, method, params) -> {
            System.out.println("自定义缓存，使用第一参数作为缓存key. params = " + Arrays.toString(params));
            // 仅仅用于测试，实际不可能这么写
            return params[0] + "0";
        };
    }

}

/**
 * 自定义redis Key的过期时间
 */
class MyRedisCacheManager extends RedisCacheManager {

    public MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if (!StringUtils.isEmpty(name) && name.contains("#")) {
            String numStr = name.split("#")[1];
            if (StringUtils.isNumeric(numStr)) {
                //默认单位为秒，可根据需求拓展
                return super.createRedisCache(name, cacheConfig.entryTtl(Duration.ofSeconds(Integer.parseInt(numStr))));
            }
        }
        //不包含则走默认时间
        return super.createRedisCache(name, cacheConfig);
    }
}
