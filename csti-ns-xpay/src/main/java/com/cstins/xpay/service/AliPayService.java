package com.cstins.xpay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cstins.xpay.conf.AliPayConfig;
import org.springframework.stereotype.Service;

/**
 * @program: csti-ns
 * @description: 阿里支付接口相关服务
 * @author: 杨云龙
 **/

@Service
public class AliPayService {

    /**
     *
     * @param out_trade_no //商户订单号，商户网站订单系统中唯一订单号，必填
     * @param total_amount //付款金额，必填
     * @return
     */
    public String pay(String out_trade_no, String total_amount) {

        String result = null;

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json", AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayConfig.return_url);
        alipayRequest.setNotifyUrl(AliPayConfig.notify_url);

        //订单名称，必填
        String subject = "商品测试";

        //商品描述，可空
        String body = "积分购买";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

}
