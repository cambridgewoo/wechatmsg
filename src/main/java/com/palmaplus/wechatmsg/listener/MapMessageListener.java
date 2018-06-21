package com.palmaplus.wechatmsg.listener;

/**
 * Created by Cambridgew on 2018/6/20.
 */

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MQ消息处理类
 */
@Log4j2
@Component
public class MapMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext consumeContext){
        try{
            String topic = new String (message.getTopic());
            String msgID = new String(message.getMsgID());
            String body = new String (message.getBody());
            log.info("Topic is "+topic);
            log.info("msgID is "+msgID);
            log.info("Received："+body);


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

