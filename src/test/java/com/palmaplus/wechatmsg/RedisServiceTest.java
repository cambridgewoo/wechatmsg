package com.palmaplus.wechatmsg;

import com.palmaplus.wechatmsg.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cambridgew on 2018/6/22.
 */
@SpringBootTest(classes = WechatmsgApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void test(){
        redisService.setValue("user2","123124",12000L);
        if(redisService.exists("user2")){
            System.out.println("OK");
        }else{
            System.out.println("Wrong");
        }
    }


}
