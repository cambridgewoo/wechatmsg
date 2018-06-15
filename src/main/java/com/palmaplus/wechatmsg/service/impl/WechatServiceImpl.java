package com.palmaplus.wechatmsg.service.impl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.util.HttpsUtil;
import com.palmaplus.wechatmsg.util.WechatTokenUtil;
import lombok.extern.log4j.Log4j2;

/**
 * Created by Cambridgew on 2018/6/14.
 */
@Log4j2
public class WechatServiceImpl {
    String sendMsgToUser(TemplateMsg templateMsg){

        //http请求，方式POST
        String tempurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        //获取API凭证
        String accessToken = WechatTokenUtil.getAccessTokenHttp().toString();
        String url = tempurl.replace("ACCESS_TOKEN",accessToken);

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("touser",templateMsg.getTouser());
            jsonObject.put("template_id",templateMsg.getTemplate_id());
            if (templateMsg.getUrl()!=null)
                jsonObject.put("url",templateMsg.getUrl());
            if(templateMsg.getMiniprogram()!=null){
                jsonObject.put("miniprogram",templateMsg.getMiniprogram());
                jsonObject.put("appid",templateMsg.getAppid());
            }
            if(templateMsg.getColor()!=null)
                jsonObject.put("color",templateMsg.getColor());
            jsonObject.put("data",templateMsg.getData());
        }catch (JSONException je){
            je.printStackTrace();
        }
        String res = HttpsUtil.httpsRequest(url,"POST",jsonObject.toString());
        try{
            //返回的JSON数据包
            String errmsg = JSONObject.parseObject(res,String.class);
            if(!"ok".equals(errmsg))
                return "error";
        }catch (JSONException je){
            je.printStackTrace();
        }
        return "success";
    }
}
