package com.palmaplus.wechatmsg.model;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.palmaplus.wechatmsg.config.MqConfig;
import com.palmaplus.wechatmsg.listener.MapMessageListener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Cambridgew on 2018/6/20.
 */
@Component
@EnableConfigurationProperties(MqConfig.class)
public class MQConsumer {

    /**
     * 配置消费者(无序)
     * @param mqConfig
     * @return
     */
    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public ConsumerBean consumerBean(@Qualifier("mqConfig") MqConfig mqConfig, MapMessageListener mapMessageListener){

        ConsumerBean consumerBean = new ConsumerBean();

        /**
         * 配置Consumer的属性
         */
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId,mqConfig.getConsumerId());
        properties.put(PropertyKeyConst.AccessKey,mqConfig.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey,mqConfig.getSecretKey());
        properties.put(PropertyKeyConst.ONSAddr,mqConfig.getONSAddr());
        properties.put(PropertyKeyConst.MessageModel,PropertyValueConst.BROADCASTING);  //广播形式
        consumerBean.setProperties(properties);


        Map subscriptionTable = new HashMap();

        Subscription subscription = new Subscription();
        subscription.setTopic(mqConfig.getTopic());
        subscription.setExpression(mqConfig.getExpression());

        subscriptionTable.put(subscription,mapMessageListener);
        consumerBean.setSubscriptionTable(subscriptionTable);

        return consumerBean;
    }
}
