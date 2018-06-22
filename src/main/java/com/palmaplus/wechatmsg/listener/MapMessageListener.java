package com.palmaplus.wechatmsg.listener;

/**
 * Created by Cambridgew on 2018/6/20.
 */

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.palmaplus.wechatmsg.model.TemplateData;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.service.WechatService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MQ消息处理类
 */
@Log4j2
@Component
public class MapMessageListener implements MessageListener {

    private TemplateMsg templateMsg = new TemplateMsg();

    private TemplateData templateData = new TemplateData();

    @Autowired
    private WechatService wechatService;

    @Override
    public Action consume(Message message, ConsumeContext consumeContext){
        try{
            String topic = new String (message.getTopic());
            String msgID = new String(message.getMsgID());
            String body = new String (message.getBody());

            log.info("Topic is "+topic);
            log.info("msgID is "+msgID);
            log.info("Received："+body);

            //获取模板消息
            JSONObject jsonObject = JSONObject.parseObject(body);
            String bdId = jsonObject.getString("bdId");    //Building ID
            String bdName = jsonObject.getString("bdName");  //Building Name
            String messageName = jsonObject.getString("messageName"); //Message的类型（例如挂号类型）
            String patientName = jsonObject.getString("patientName"); //病人姓名
            String patientSex = jsonObject.getString("patientSex"); //病人性别
            String doctorName = jsonObject.getString("doctorName");  //医生姓名
            String departmentName = jsonObject.getString("departmentName");  //科室名称
            String time = jsonObject.getString("time");    //时间戳
            String openId = jsonObject.getString("openId");  //openID
            String templateId = jsonObject.getString("templateId");  //模板ID*/

            templateData.setFirst("您已挂号成功！");
            templateData.setKeyword1(patientName);
            templateData.setKeyword2(patientSex);
            templateData.setKeyword3(departmentName);
            templateData.setKeyword4(doctorName);
            templateData.setKeyword5(time);
            templateData.setRemark("请按照导航指引尽快就诊！");

            templateMsg.setTouser(openId);
            templateMsg.setTemplate_id(templateId);
            templateMsg.setTemplateData(templateData);

            wechatService.sendMsgToUser(templateMsg);

        }catch (Exception e){
            return Action.ReconsumeLater;
        }
        return Action.CommitMessage;
    }

    @Bean
    public MapMessageListener msgListener(){

        return new MapMessageListener();
    }
}

