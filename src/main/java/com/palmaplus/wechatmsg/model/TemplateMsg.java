package com.palmaplus.wechatmsg.model;

/**
 * Created by Cambridgew on 2018/6/14.
 */


import com.alibaba.fastjson.JSONObject;
import com.palmaplus.wechatmsg.util.TemplateJsonPackageUtil;
import lombok.Data;


/**
 * 模板消息实体类
 */
@Data
public class TemplateMsg {
    private String touser;  //openID
    private String template_id; //模板id
    private String url; // 模板跳转链接（required = false），置空
    private String miniprogram; // 小程序跳转（required = false），置空
    private String appid;   //小程序ID
    private String pagePath;    //小程序具体路径（required = false）
    private JSONObject data; // 模板详细信息[value+data]
    private String color;   //模板字体颜色，默认为黑色

    public TemplateMsg(){

    }
    public TemplateMsg(String touser,TemplateData templateData){
        this.touser = touser;
        this.data = TemplateJsonPackageUtil.pacJSONmsg(templateData.getFirst(),
                templateData.getKeyword1(), //患者姓名
                templateData.getKeyword2(), //患者性别
                templateData.getKeyword3(), //科室
                templateData.getKeyword4(), //医生姓名
                templateData.getKeyword5(), //挂号时间
                templateData.getRemark());
    }
    public TemplateMsg(String touser, String template_id, String url, String miniprogram, String appid, String pagePath, TemplateData templateData, String color) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url==null?null:url;
        this.miniprogram = miniprogram==null?null:miniprogram;
        this.appid = appid;
        this.pagePath = pagePath==null?null:pagePath;
        this.data = TemplateJsonPackageUtil.pacJSONmsg(templateData.getFirst(),
                templateData.getKeyword1(),
                templateData.getKeyword2(),
                templateData.getKeyword3(),
                templateData.getKeyword4(),
                templateData.getKeyword5(),
                templateData.getRemark());
        this.color = color==null?null:color;
    }

    public void setTemplateData(TemplateData templateData) {
        this.data = TemplateJsonPackageUtil.pacJSONmsg(templateData.getFirst(),
                templateData.getKeyword1(),
                templateData.getKeyword2(),
                templateData.getKeyword3(),
                templateData.getKeyword4(),
                templateData.getKeyword5(),
                templateData.getRemark());
    }
}
