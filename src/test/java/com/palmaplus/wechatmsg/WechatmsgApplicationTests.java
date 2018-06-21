package com.palmaplus.wechatmsg;

import com.palmaplus.wechatmsg.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class WechatmsgApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        redisUtil.exists("");
    }

}
