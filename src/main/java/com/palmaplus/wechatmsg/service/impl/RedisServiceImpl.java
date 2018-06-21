package com.palmaplus.wechatmsg.service.impl;

import com.palmaplus.wechatmsg.service.RedisService;
import com.palmaplus.wechatmsg.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Cambridgew on 2018/6/20.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;


    //写入缓存
    public boolean setValue(String key,String value){
        return redisUtil.setValue(key,value);
    }
    //写入缓存，同时设置缓存过期时间
    public boolean setValue(String key,String value,Long expireTime){
        return redisUtil.setValue(key,value,expireTime);
    }
    //读取缓存
    public Object getValue(String key){
        return redisUtil.getValue(key);
    }
    //判断是否存在key
    public boolean exists(String key){
        return redisUtil.exists(key);
    }
    //获取过期时间
    public Long getExpireTime(String key){
        return redisUtil.getExpire(key);
    }
}
