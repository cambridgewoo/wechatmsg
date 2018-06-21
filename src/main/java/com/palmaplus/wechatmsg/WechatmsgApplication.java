package com.palmaplus.wechatmsg;

import com.palmaplus.wechatmsg.model.TemplateData;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.service.RedisService;
import com.palmaplus.wechatmsg.service.impl.RedisServiceImpl;

import com.palmaplus.wechatmsg.service.impl.WechatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan("com.palmaplus.wechatmsg")
public class WechatmsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatmsgApplication.class, args);

       String touser = "oLPT50m5fE1ySEJRs8z1EpvsRCRQ";  //openID
        String template_id = "MZVBHeMIdE4SZblhKyWfd7_btlpnhVNkk96tbwKQ9-Q"; //模板id
        String url = null; // 模板跳转链接
        String miniprogram = null; //
        String appid = null;   //小程序ID
        String pagePath = null;    //小程序具体路径（r
        String color = null;   //模板字体颜色，默认为黑色


        TemplateData templateData = new TemplateData("挂号成功","张三","男","骨科","李医生","2018-6-21","请按照导航指引尽快就诊");
        TemplateMsg templateMsg = new TemplateMsg(touser,template_id,url,miniprogram,appid,pagePath,templateData,color);
        WechatServiceImpl wechatService = new WechatServiceImpl();
       wechatService.sendMsgToUser(templateMsg);

  //      wechatService.getTemplateList();

        /*RedisService redisService = new RedisServiceImpl();
        redisService.setValue("redis", "I love you redis!");
        System.out.println(redisService.getValue("redis"));
        System.out.println(redisService.getValue("redis"));
        System.out.println(redisService.getValue("redis"));
        System.out.println(redisService.getValue("redis"));*/
    }

}
