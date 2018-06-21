package com.palmaplus.wechatmsg.config;

/**
 * Created by Cambridgew on 2018/6/20.
 */
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Configuration;


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
}
