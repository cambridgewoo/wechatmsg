package com.palmaplus.wechatmsg.model;

/**
 * Created by Cambridgew on 2018/6/14.
 */

import lombok.Data;

/**
 * AccessToken
 */
@Data
public class AccessToken {
    private String accessToken;    //取到的令牌
    private static int expression = 3600; //令牌有效时间，单位:s
}
