package com.qx.service.impl;

import com.google.gson.Gson;
import com.qx.api.INoteService;
import com.qx.pojo.NoteResponseResult;
import com.qx.utils.Config;
import com.qx.utils.HttpUtil;

import java.net.URLEncoder;

/**
 * Author: QX_He
 * DATA: 2020/8/7-10:36
 * Description:
 **/
public class NotServiceImpl implements INoteService {

    @Override
    public NoteResponseResult sendMessages(String to, String data) {
        String temBufferContent = null;
        StringBuilder stringBuilder = new StringBuilder("【梦想科技】您的短信验证码为");
        stringBuilder.append(data);
        stringBuilder.append("，请在五分钟内使用。");
        try {
            //编码格式： UTF-8
            temBufferContent = URLEncoder.encode(stringBuilder.toString(), "UTF-8");
        } catch (Exception e) {
            //一定要把异常信息打印出来
            e.printStackTrace();
        }
        String url = Config.BASE_URL + Config.OPERATION;
        String body = "accountSid" + Config.ACCOUNT_SID + "&to=" + to + "&smsContent=" + temBufferContent +
                HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);

        //提交请求，返回的是JSON对象
        String result = HttpUtil.post(url, body);

        System.out.println(result);
        //JSON.parseObject(attachString, Map.class);  这种是 fast Json 自带的。
        //我们这里需要自己引入Gson
        Gson gson = new Gson();
        NoteResponseResult noteResponseResult = gson.fromJson(result, NoteResponseResult.class);
        return noteResponseResult;
    }
}
