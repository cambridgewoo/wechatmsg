package com.palmaplus.wechatmsg.model;

/**
 * Created by Cambridgew on 2018/6/14.
 */

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 模板详细信息
 */
@Data
public class TemplateData {
    private String first;
    private String patientName;
    private String department;
    private String date;
    private String remark;

    public TemplateData(String first, String patientName, String department, String remark) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        this.first = first;
        this.patientName = patientName;
        this.department = department;
        this.date = dateNowStr;
        this.remark = remark;
    }
}
