package com.palmaplus.wechatmsg.service;

/**
 * Created by Cambridgew on 2018/6/22.
 */
public interface MQService {
    String produceMsg(String msgBody);
    void consumeMsg();
}
