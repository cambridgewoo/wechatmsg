package com.palmaplus.wechatmsg.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

/**
 * Created by Cambridgew on 2018/6/15.
 */

/**
 * 打包msg数据
 * @Param first抬头
 * @Param patientName患者姓名
 * @Param department科室
 * @Param date日期
 * @Param remark说明
 */
public class TemplateJsonPackageUtil {
    public static JSONObject pacJSONmsg(String first, String patientName,String gender, String department, String doctorName,String time,String remark){

        JSONObject json = new JSONObject(true);
        try {
            //first头
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            json.put("first", jsonFirst);

            //患者姓名
            JSONObject jsonPatientName = new JSONObject();
            jsonPatientName.put("value", patientName);
            jsonPatientName.put("color", "#173177");
            json.put("keyword1", jsonPatientName);

            //患者性别
            JSONObject jsonGender = new JSONObject();
            jsonGender.put("value", gender);
            jsonGender.put("color", "#173177");
            json.put("keyword2", jsonGender);

            //科室
            JSONObject jsonDepartment = new JSONObject();
            jsonDepartment.put("value", department);
            jsonDepartment.put("color", "#173177");
            json.put("keyword3", jsonDepartment);

            //医生姓名
            JSONObject jsonDoctorName = new JSONObject();
            jsonDoctorName.put("value", doctorName);
            jsonDoctorName.put("color", "#173177");
            json.put("keyword4", jsonDoctorName);

            //挂号时间
            JSONObject jsonTime = new JSONObject();
            jsonTime.put("value", time);
            jsonTime.put("color", "#173177");
            json.put("keyword5", jsonTime);

            //remark
            JSONObject jsonRemark = new JSONObject();
            jsonRemark.put("value", remark);
            jsonRemark.put("color", "#173177");
            json.put("remark", jsonRemark);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
