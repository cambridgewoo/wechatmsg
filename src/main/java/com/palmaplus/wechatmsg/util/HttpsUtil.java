package com.palmaplus.wechatmsg.util;

/**
 * Created by Cambridgew on 2018/6/14.
 */

import lombok.extern.log4j.Log4j2;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;


/**
 * 发送Https请求
 * @Param reqURL: 请求地址
 * @Param reqMethod：请求方式
 * @Param outputStr：返回的数据
 * @Return jsonObject
 */

@Log4j2
public class HttpsUtil {

    public static String httpsRequest(String reqURL,String reqMethod,String outputStr){
        try{
            URL url = new URL(reqURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);      //开启下载
            conn.setDoOutput(true);     //开启上传
            conn.setUseCaches(false);   //禁用cache

            //设置请求方式
            conn.setRequestMethod(reqMethod);
            //设置请求头
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            //向输出流写数据
            if(null!=outputStr){
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));//编码格式UTF-8
                outputStream.close();
            }
            //从输入流获取数据并返回
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer stringBuffer = new StringBuffer();
            while((str = bufferedReader.readLine())!=null){
                stringBuffer.append(str);
            }
            //释放
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            conn.disconnect();
            return stringBuffer.toString();

        }catch (ConnectException ce){
            log.info("连接超时");
        }catch (Exception e){
            log.info("https请求异常");
        }
        return null;
    }
}
