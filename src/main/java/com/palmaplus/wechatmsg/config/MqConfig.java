package com.palmaplus.wechatmsg.config;

/**
 * Created by Cambridgew on 2018/6/20.
 */
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.palmaplus.wechatmsg.listener.MapMessageListener;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * MQ配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MqConfig {
    private String producerId;
    private String consumerId;
    private String accessKey;
    private String secretKey;
    private String topic;
    private String expression;
    private String ONSAddr;


    /**
     * 配置Consumer
     * @param mapMessageListener
     * @return
     */
    private MapMessageListener mapMessageListener;
    public MqConfig(@Qualifier("mapMessageListener") MapMessageListener mapMessageListener){
        this.mapMessageListener = mapMessageListener;
    }
    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public ConsumerBean consumerBean(){
        ConsumerBean consumerBean = new ConsumerBean();

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId,consumerId);
        properties.put(PropertyKeyConst.AccessKey,accessKey);
        properties.put(PropertyKeyConst.SecretKey,secretKey);
        properties.put(PropertyKeyConst.ONSAddr,ONSAddr);
        properties.put(PropertyKeyConst.MessageModel,PropertyValueConst.BROADCASTING);  //广播形式
        consumerBean.setProperties(properties);

        Map subscriptionTable = new HashMap();

        Subscription subscription = new Subscription();
        subscription.setTopic(topic);
        subscription.setExpression(expression);

        subscriptionTable.put(subscription,mapMessageListener);
        consumerBean.setSubscriptionTable(subscriptionTable);

        return consumerBean;
    }


    /**
     * 配置Producer的属性
     * @return
     */
    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public ProducerBean producerBean (){

        ProducerBean producerBean = new ProducerBean();
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId,producerId);
        properties.put(PropertyKeyConst.AccessKey,accessKey);
        properties.put(PropertyKeyConst.SecretKey,secretKey);
        properties.put(PropertyKeyConst.ONSAddr,ONSAddr);
        producerBean.setProperties(properties);
        return producerBean;
    }
}
