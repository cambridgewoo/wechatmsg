package com.palmaplus.wechatmsg.service;

/**
 * Created by Cambridgew on 2018/6/20.
 */
public interface RedisService {
    //写入缓存
    boolean setValue(String key,String value);
    //写入缓存，标注失效时间
    boolean setValue(String key, String value, Long expireTime);
    //读取缓存
    Object getValue(String key);
    //判断是否存在key
    boolean exists(String key);
    //获取过期时间
    Long getExpireTime(String key);
}
