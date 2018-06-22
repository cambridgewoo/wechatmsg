package com.palmaplus.wechatmsg.controller;

import com.palmaplus.wechatmsg.service.MQService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cambridgew on 2018/6/22.
 */
@Api(tags = "微信消息消息队列服务")
@RestController
public class MqController {
    @Autowired
    private MQService mqService;

    public
}
