package com.palmaplus.wechatmsg.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;


/**
 * Created by Cambridgew on 2018/6/19.
 */


/**
 * 通过RedisTemplate调用ValueOperations和ListOperations等方法
 */

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key,String value){
        boolean res = false;
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return res;
        }
        try{
            redisTemplate.opsForValue().set(key, value);
            res = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean setValue(String key,String value,Long expireTime){
        boolean res = false;
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return res;
        }
        try{
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key,expireTime,TimeUnit.SECONDS);    //设置过期时间
            res = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object getValue(String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断是否存在key
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }
}
