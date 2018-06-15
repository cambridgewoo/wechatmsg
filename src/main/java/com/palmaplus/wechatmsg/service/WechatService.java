package com.palmaplus.wechatmsg.service;

import com.palmaplus.wechatmsg.model.TemplateMsg;
import org.springframework.stereotype.Service;

/**
 * Created by Cambridgew on 2018/6/14.
 */
@Service
public interface WechatService {
    public String sendMsgToUser(TemplateMsg templateMsg);
}
