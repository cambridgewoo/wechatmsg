package com.palmaplus.wechatmsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan("com.palmaplus.wechatmsg")
public class WechatmsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatmsgApplication.class, args);
    }

}
