package com.cstins.sso.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.security.SecureRandom;

import static software.amazon.ion.SystemSymbols.SYMBOLS;

/**
 * @program: csti-ns
 * @description: 腾讯短信服务
 * @author: 杨云龙
 **/

@Service
public class TXMessageService {

    private static final Logger logger = LogManager.getLogger(TXMessageService.class);

    // 短信应用SDK AppID
    private int appid = 1400193574; // 1400开头

    // 短信应用SDK AppKey
    private String appkey = "56bd591e65d47f7e4dcf7b42d3613da0";

    // 短信模板ID，需要在短信应用中申请
    private int templateId = 297184; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    //templateId7839对应的内容是"您的验证码是: {1}"
    // 签名
    private String smsSign = "计软科技"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    public String send(String phone) {
        try {
            String vcode = Generate_verification_code();
            String[] params = {vcode};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
            return vcode;
        } catch (HTTPException e) {
            // HTTP响应码错误
            logger.error(e);
        } catch (JSONException e) {
            // json解析错误
            logger.error(e);
        } catch (IOException e) {
            // 网络IO错误
            logger.error(e);
        }
        return "send failed!";
    }

    /**
     * 生成验证码
     * @return
     */
    public String Generate_verification_code() {
        return (int)((Math.random()*9+1)*100000) + "";
    }
}
