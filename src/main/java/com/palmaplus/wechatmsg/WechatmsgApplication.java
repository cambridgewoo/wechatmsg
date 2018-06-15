package com.palmaplus.wechatmsg;

import com.alibaba.fastjson.JSONObject;
import com.palmaplus.wechatmsg.model.TemplateData;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.service.WechatService;
import com.palmaplus.wechatmsg.service.impl.WechatServiceImpl;
import com.palmaplus.wechatmsg.util.WechatTokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WechatmsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatmsgApplication.class, args);

        String touser = "oLPT50m5fE1ySEJRs8z1EpvsRCRQ";  //openID
        String template_id = "ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY"; //模板id
        String url = null; // 模板跳转链接
        String miniprogram = null; //
        String appid = null;   //小程序ID
        String pagePath = null;    //小程序具体路径（r
        String color = null;   //模板字体颜色，默认为黑色


        TemplateData templateData = new TemplateData("挂号成功","张三","骨科","请按照导航指引尽快就诊");
        TemplateMsg templateMsg = new TemplateMsg(touser,template_id,url,miniprogram,appid,pagePath,templateData,color);
        WechatServiceImpl wechatService = new WechatServiceImpl();
        wechatService.sendMsgToUser(templateMsg);
    }

}
