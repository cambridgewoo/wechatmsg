package com.palmaplus.wechatmsg.service;

import com.palmaplus.wechatmsg.model.TemplateMsg;

/**
 * Created by Cambridgew on 2018/6/14.
 */

public interface WechatService {
    String sendMsgToUser(TemplateMsg templateMsg);
    String getTemplateList();
}
