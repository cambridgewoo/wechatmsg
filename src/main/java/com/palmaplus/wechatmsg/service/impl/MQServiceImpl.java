package com.palmaplus.wechatmsg.service.impl;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.palmaplus.wechatmsg.config.MqConfig;
import com.palmaplus.wechatmsg.service.MQService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Created by Cambridgew on 2018/6/22.
 */
@Service
@Log4j2
public class MQServiceImpl implements MQService {

    private MqConfig mqConfig;


    public MQServiceImpl(@Qualifier("mqConfig")MqConfig mqConfig){
        this.mqConfig = mqConfig;
    }

    @Override
    public String produceMsg(String msgBody){
        ProducerBean producerBean = mqConfig.producerBean();
        producerBean.start();
        log.info("producer started!");
        Message message = new Message(mqConfig.getTopic(),"testTag", msgBody.getBytes());
        try {
            SendResult sendResult = producerBean.send(message);
            assert sendResult != null;
            return (new Date() + " Send mq message success! Topic is:" + mqConfig.getTopic() + " msgId is: " + sendResult.getMessageId());
        } catch (ONSClientException e) {
            return "发送失败";
            //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
        }
    }

    @Override
    public void consumeMsg(){
        ConsumerBean consumerBean = mqConfig.consumerBean();
        try{
            consumerBean.start();
            log.info("success");
        }catch (ONSClientException oe){
            oe.printStackTrace();
            log.info("failed");
        }

    }


}
