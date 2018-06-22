package com.palmaplus.wechatmsg.model;

/**
 * Created by Cambridgew on 2018/6/14.
 */

import lombok.Data;


/**
 * 模板详细信息
 */
@Data
public class TemplateData {
    private String first;
    private String keyword1;    //患者姓名
    private String keyword2;    //性别
    private String keyword3;    //科室
    private String keyword4;    //挂号医生
    private String keyword5;    //挂号时间
    private String remark;

    public TemplateData(){

    }
    public TemplateData(String first, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark) {
       /* Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);*/
        this.first = first;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
      //this.date = dateNowStr;
        this.keyword3 = keyword3;
        this.keyword4 = keyword4;
        this.keyword5 = keyword5;
        this.remark = remark;
    }
}
