package com.palmaplus.wechatmsg.config;




/**
 *  Created by Cambridgew on 2018/6/19.
 */


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 *Redis缓存配置类
 */
@Log4j2
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.password}")
    private String password;

    //自动注入文件配置的连接工厂
    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    /*
    @Bean
    RedisTemplate<String,String> objectRedisTemplate(){
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }*/

    /**
     * 采用RedisCacheManager作为缓存管理器
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        //access_token的缓存
        RedisCacheConfiguration tokenCacheconfiguration = RedisCacheConfiguration.defaultCacheConfig()//生成一个默认配置
        .entryTtl(Duration.ofSeconds(7200))//设置缓存过期时间，使用Duration设置
        .disableCachingNullValues();//缓存值不为空

        //对access_token的缓存进行配置
        Map<String,RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("token",tokenCacheconfiguration);

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        /**
         * 设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,
         * 但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，J
         * dkSerializationRedisSerializer序列化value,
         * 所以以下注释代码为默认实现
         */
        //        ClassLoader loader = this.getClass().getClassLoader();
//        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
//        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

        //设置默认超过期时间
        defaultCacheConfig.entryTtl(Duration.ofSeconds(7200));
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter,defaultCacheConfig,redisCacheConfigurationMap);
        return cacheManager;
    }

    /**
     * 重写缓存key生成策略，可根据自身业务需要进行自己的配置生成条件
     * @return
     */
   /* @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }*/
}
