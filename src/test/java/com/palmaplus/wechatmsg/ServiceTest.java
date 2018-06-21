package com.palmaplus.wechatmsg;

import com.palmaplus.wechatmsg.model.TemplateData;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.service.RedisService;
import com.palmaplus.wechatmsg.service.WechatService;
import com.palmaplus.wechatmsg.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cambridgew on 2018/6/20.
 */
@SpringBootTest(classes = WechatmsgApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    @Autowired
    private WechatService wechatService;
    @Autowired
    private RedisService redisService;
    @Test
    public void test() {
//               redisUtil.setValue("user","1234");
        redisService.setValue("user2","123124",12000L);
    if(redisService.exists("user2")){
            System.out.println("OK");
        }else{
            System.out.println("Wrong");
        }
       String touser = "oLPT50m5fE1ySEJRs8z1EpvsRCRQ";  //openID
        String template_id = "MZVBHeMIdE4SZblhKyWfd7_btlpnhVNkk96tbwKQ9-Q"; //模板id
        String url = null; // 模板跳转链接
        String miniprogram = null; //
        String appid = null;   //小程序ID
        String pagePath = null;    //小程序具体路径（r
        String color = null;   //模板字体颜色，默认为黑色


        TemplateData templateData = new TemplateData("挂号成功","张三","男","骨科","李医生","2018-6-21","请按照导航指引尽快就诊");
        TemplateMsg templateMsg = new TemplateMsg(touser,template_id,url,miniprogram,appid,pagePath,templateData,color);
        wechatService.sendMsgToUser(templateMsg);

    }
}
