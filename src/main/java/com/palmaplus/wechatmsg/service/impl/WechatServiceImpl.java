package com.palmaplus.wechatmsg.service.impl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.palmaplus.wechatmsg.model.TemplateMsg;
import com.palmaplus.wechatmsg.service.WechatService;
import com.palmaplus.wechatmsg.util.HttpsUtil;
import com.palmaplus.wechatmsg.util.WechatTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Cambridgew on 2018/6/14.
 */
@Log4j2
@Service
public class WechatServiceImpl implements WechatService {

    /**
     * 给指定用户推送消息
     * @param templateMsg
     * @return
     */

    @Autowired
    private WechatTokenUtil wechatTokenUtil;
    @Override
    public String sendMsgToUser(TemplateMsg templateMsg){

        //http请求，方式POST
        String tempurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        //获取API凭证
        String accessToken = wechatTokenUtil.getAccessToken().getAccessToken();
  //      String accessToken = WechatTokenUtil.getAccessTokenHttp().get("access_token").toString();

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
            //返回的JSON数据
            String errmsg = JSONObject.parseObject(res,String.class);
            if(!"ok".equals(errmsg))
                return "error";
        }catch (JSONException je){
            je.printStackTrace();
        }
        return "success";
    }

    @Override
    public String getTemplateList(){

        //http请求，方式GET
        String tempurl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
        //获取API凭证
        String accessToken = WechatTokenUtil.getAccessToken().getAccessToken();
        String url = tempurl.replace("ACCESS_TOKEN",accessToken);

        String res = HttpsUtil.httpsRequest(url,"GET","");
        try{
            String tempList = JSONObject.parseObject(res, String.class);
            if (null == tempList)
                return "error";
            //保存template_List文件名字
            String fileName = "templateList";
            File templateList = new File(fileName);
            try{
                if(!templateList.exists()){
                    templateList.createNewFile();
                }
            }catch (IOException ie){
                ie.printStackTrace();
            }
            byte bytes[];
            bytes = tempList.getBytes();
            try{
                FileOutputStream fos = new FileOutputStream(templateList);
                fos.write(bytes);
                fos.close();
            }catch (FileNotFoundException fe){
                fe.printStackTrace();
            }catch (IOException ie){
                ie.printStackTrace();
            }
        }catch (JSONException je){
            je.printStackTrace();
        }
        return "success";
    }
}
