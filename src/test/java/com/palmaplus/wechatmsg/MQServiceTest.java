package com.palmaplus.wechatmsg;

import com.palmaplus.wechatmsg.service.MQService;
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
public class MQServiceTest {

    @Autowired
    private MQService mqService;

    @Test
    public void test() {
        System.out.println(mqService.produceMsg("Hi sherlock"));

    }
}
