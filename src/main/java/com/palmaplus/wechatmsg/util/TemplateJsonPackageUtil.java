package com.palmaplus.wechatmsg.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

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
    public static JSONObject pacJSONmsg(String first, String patientName, String department,String date, String remark){

        JSONObject json = new JSONObject();
        try {
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            json.put("first", jsonFirst);

            JSONObject jsonPatientName = new JSONObject();
            jsonPatientName.put("value", patientName);
            jsonPatientName.put("color", "#173177");
            json.put("patientName", jsonPatientName);

            JSONObject jsonDepartment = new JSONObject();
            jsonDepartment.put("value", department);
            jsonDepartment.put("color", "#173177");
            json.put("department", jsonDepartment);

            JSONObject jsonDate = new JSONObject();
            jsonDate.put("value", date);
            jsonDate.put("color", "#173177");
            json.put("date", jsonDate);

            JSONObject jsonRemark = new JSONObject();
            jsonRemark.put("value", remark);
            jsonRemark.put("color", "#173177");
            json.put("Remark", jsonRemark);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
