package com.palmaplus.wechatmsg.util;

import com.alibaba.fastjson.JSONObject;
import com.palmaplus.wechatmsg.model.AccessToken;
import com.palmaplus.wechatmsg.service.RedisService;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 * Created by Cambridgew on 2018/6/14.
 */
@Component
@Log4j2
/**
 * 获取访问公众号的Access_Token
 * 每3600s(一小时刷新一次)，token存放在Redis中
 */
public class WechatTokenUtil {

    @Autowired
    private RedisService redisService;

    private static String appID = "wxf1818ee8e3897d5b";

    private static String appSecret = "a9077c047b147a653ae39987bfbaab82";
    //accessToken刷新时间：3600s
    private static final long ACCESS_TOKEN_SCHEDULE = 3600L;

    //获取Access_Token,synchronized关键字防止同时被多实例化（从Redis获取常驻的AccessToken）
    public synchronized  AccessToken getAccessToken(){
        AccessToken resToken = new AccessToken();

        if(!redisService.exists("accessToken")){    // redis中不存在accessToken
            //发送http请求得到json流
            JSONObject jobject = getAccessTokenHttp();
            //从json流中获取access_token
            String  j_access_token = (String) jobject.get("access_token");
            redisService.setValue("accessToken",j_access_token,ACCESS_TOKEN_SCHEDULE);
     //       redisService.setValue("accessToken",j_access_token);
            boolean flag = redisService.exists("accessToken");
            resToken.setAccessToken(j_access_token);
        }else{
            resToken.setAccessToken(redisService.getValue("accessToken").toString());
        }
        return resToken;


        /**
         * 将accessToken存在文件中
         */

        /*//保存access_token文件名字
        String fileName = "wechatTokenUtil.properties";
        try {
            // 从文件中获取token值及时间
            Properties prop = new Properties();// 属性集合对象
            //获取文件流
            InputStream fis = WechatTokenUtil.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(fis);// 将属性文件流装载到Properties对象中
            fis.close();// 关闭流
            //获取Appid，APPsecret
            String APPID = prop.getProperty("APPID");
            String APPSECRET = prop.getProperty("APPSECRET");
            // 获取accesstoken，初始值为空，第一次调用之后会把值写入文件
            String access_token = prop.getProperty("access_token");
            String expires_in = prop.getProperty("ACCESS_TOKEN_SCHEDULE");
            String last_time = prop.getProperty("last_time");

        //    int int_expires_in = 0;     //失效时间
            long long_last_time = 0;    //上一次获取accesstoken的时间

            try {
     //           int_expires_in = Integer.parseInt(expires_in);
                long_last_time = Long.parseLong(last_time);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //得到当前时间
            long current_time = System.currentTimeMillis();

            // 每次调用，判断expires_in是否过期，如果token时间超时，重新获取，expires_in有效期为7200
            if ((current_time - long_last_time) / 1000 >= ACCESS_TOKEN_SCHEDULE) {
                //发送http请求得到json流
                JSONObject jobject = getAccessTokenHttp();
                //从json流中获取access_token
                String  j_access_token = (String) jobject.get("access_token");
                String  j_expires_in = jobject.get("expires_in").toString();

                //保存access_token
                if (j_access_token != null && j_expires_in != null) {
                    prop.setProperty("access_token", j_access_token);
                    prop.setProperty("ACCESS_TOKEN_SCHEDULE", j_expires_in);
                    prop.setProperty("last_time", System.currentTimeMillis() + "");

                    URL url_ = WechatTokenUtil.class.getClassLoader().getResource(fileName);
                    FileOutputStream fos = new FileOutputStream(new File(url_.toURI()));
                    prop.store(fos, null);
                    fos.close();// 关闭流
                }
                //如果已经过期返回获取到的access_token
                resToken.setAccessToken(j_access_token);
                return resToken;
            } else {
                //如果没有过期，返回从文件中读取的access_token
                resToken.setAccessToken(access_token);
                return resToken;
            }
        } catch (Exception e) {
            return null;
        }*/
    }

    //https请求获取AccessToken（定时获取AccessToken）
    public synchronized static JSONObject getAccessTokenHttp(){
        //https请求，方式为GET
        String tempURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

        String url = tempURL.replace("APPID",appID);
        url = url.replace("APPSECRET",appSecret);

        JSONObject jsonObject = null;
        //调用HttpsUtil接口
        jsonObject = JSONObject.parseObject(HttpsUtil.httpsRequest(url,"GET",null));
        return jsonObject;
    }
}
